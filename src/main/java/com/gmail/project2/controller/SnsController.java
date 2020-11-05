package com.gmail.project2.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.project2.dto.CSVInvoice;
import com.gmail.project2.dto.Invoice;
import com.gmail.project2.dto.Item;
import com.gmail.project2.dto.S3Obj;
import com.gmail.project2.repository.InvoiceRepository;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sns/invoice")
public class SnsController {
    
    @Autowired
    private ResourceLoader resourceLoader;
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Value("${csv.upload.url}")
    private String csvUploadBucketUrl;
    
    @Value("${csv.upload.bucket}")
    private String csvUploadBucket;
    
    @Autowired
    private AmazonS3 amazonS3;
    
    @NotificationSubscriptionMapping
    public void handleSubscribeMessage(NotificationStatus notificationStatus){
        System.out.println("Notification Subscription Request Received...");
        notificationStatus.confirmSubscription();
    }
    
    
    @NotificationMessageMapping
    public void handleNotificationMessage(@NotificationSubject String subject, @NotificationMessage String message){
        System.out.println("Notification Start............");
        System.out.println("Subject -> " + subject);
        System.out.println("Message -> " + message);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            S3Obj obj = mapper.readValue(message, S3Obj.class);
            System.out.println("Bucket Name: " + obj.getRecords().get(0).getS3().getBucket().getName());
            System.out.println("Object Key: " + obj.getRecords().get(0).getS3().getObject().getKey());
            
            String bucket = obj.getRecords().get(0).getS3().getBucket().getName();
            String object = obj.getRecords().get(0).getS3().getObject().getKey();
            Resource resource = resourceLoader.getResource("s3://" + bucket + "/" + object);
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            List<String> fileContent = new ArrayList<>();
            while((line = bufferedReader.readLine()) != null){
                System.out.println(">> " + line);
                fileContent.add(line);
            }
            bufferedReader.close();
            reader.close();
            
            if(fileContent != null && !fileContent.isEmpty()){
                Invoice invoice = new Invoice();
                CSVInvoice csvInvoice = new CSVInvoice();
                for(int i = 3; i <= 10; i++){
                    String[] arr = fileContent.get(i).split(":");
                    System.out.println(arr[0] + " -> " + arr[1].trim());
                    if(arr[0].trim().equalsIgnoreCase("Customer-ID")) {
                        invoice.setCustomerId(arr[1].trim());
                        csvInvoice.setCustomerId(arr[1].trim());
                    }
                    
                    if(arr[0].trim().equalsIgnoreCase("Inv-ID")) {
                        invoice.setInvoiceId(arr[1].trim());
                        csvInvoice.setInvoiceId(arr[1].trim());
                    }
                    
                    if(arr[0].trim().equalsIgnoreCase("Dated")) {
                        invoice.setDate(arr[1].trim());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        csvInvoice.setDate(format.parse(arr[1].trim()));
                    }
                    
                    if(arr[0].trim().equalsIgnoreCase("From")) {
                        invoice.setFrom(arr[1].trim());
                        csvInvoice.setFrom(arr[1].trim());
                    }
                    
                    if(arr[0].trim().equalsIgnoreCase("To")) {
                        invoice.setTo(arr[1].trim());
                        csvInvoice.setTo(arr[1].trim());
                    }
                    
                    if(arr[0].trim().equalsIgnoreCase("Amount")) {
                        invoice.setAmount(arr[1].trim());
                        csvInvoice.setAmount(Double.parseDouble(arr[1].trim()));
                    }
                    
                    if(arr[0].trim().equalsIgnoreCase("SGST")) {
                        invoice.setSgst(arr[1].trim());
                        csvInvoice.setSgst(Double.parseDouble(arr[1].trim()));
                    }
                    
                    if(arr[0].trim().equalsIgnoreCase("Total")){
                        invoice.setTotal(arr[1].trim());
                        csvInvoice.setTotal(Double.parseDouble(arr[1].trim()));
                    }
                    
                    
                    if(arr[0].trim().equalsIgnoreCase("InWords"))
                        invoice.setInWords(arr[1].trim());
                }
                
                List<Item> invoiceItems = new ArrayList<>();
                for(int i = 14; i < fileContent.size(); i++){
                    Item item = new Item();
                    String[] itemDetails = fileContent.get(i).substring(3).split(",");
                    item.setName(itemDetails[0].trim());
                    item.setQuantity(itemDetails[1].trim());
                    item.setPrice(itemDetails[2].trim());
                    invoiceItems.add(item);
                }
                
                if(!invoiceItems.isEmpty())
                    invoice.setItems(invoiceItems);
    
                invoiceRepository.insertInvoice(invoice);
    
                File csvFile = new File("/tmp/invoices.csv");
                resource = resourceLoader.getResource(csvUploadBucketUrl);
                try {
                    
                    reader = new InputStreamReader(resource.getInputStream());
                    bufferedReader = new BufferedReader(reader);
    
                    
                    FileWriter fileWriter = new FileWriter(csvFile);
                    while ((line = bufferedReader.readLine()) != null) {
                        fileWriter.write(line + "\n");
                    }
                    fileWriter.close();
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true));
                ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
                strategy.setType(CSVInvoice.class);
                
                StatefulBeanToCsv<CSVInvoice> invoices = new StatefulBeanToCsvBuilder<CSVInvoice>(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withMappingStrategy(strategy)
                        .withApplyQuotesToAll(false)
                        .build();
                
                //String[] data = {invoice.getInvoiceId(), invoice.getCustomerId(), invoice.getDate(), invoice.getFrom(), invoice.getTo(), Integer.parseInt(invoice.getAmount()), invoice.getSgst(), invoice.getTotal()};
                //writer.writeNext(data);
                invoices.write(csvInvoice);
                writer.close();
    
                final PutObjectRequest putObjectRequest = new PutObjectRequest(csvUploadBucket, "invoices.csv", csvFile);
                amazonS3.putObject(putObjectRequest);
                
            }
    
           
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
        System.out.println("Notification Ends.............");
    }
    
    @NotificationUnsubscribeConfirmationMapping
    public void handleUnsubscribeMessage(NotificationStatus notificationStatus){
        System.out.println("Notification Unsubscribe Request Received......");
        notificationStatus.confirmSubscription();
    }
}
