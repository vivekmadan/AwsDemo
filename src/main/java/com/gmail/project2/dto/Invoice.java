package com.gmail.project2.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@DynamoDBTable(tableName = "invoice")
public class Invoice implements Serializable {
    
    @DynamoDBAttribute(attributeName = "customerId")
    private String customerId;
    
    @DynamoDBHashKey(attributeName = "invoiceId")
    @DynamoDBAutoGeneratedKey
    private String invoiceId;
    
    @DynamoDBAttribute(attributeName = "date")
    private String date;
    
    @DynamoDBAttribute(attributeName = "from")
    private String from;
    
    @DynamoDBAttribute(attributeName = "to")
    private String to;
    
    @DynamoDBAttribute(attributeName = "amount")
    private String amount;
    
    @DynamoDBAttribute(attributeName = "sgst")
    private String sgst;
    
    @DynamoDBAttribute(attributeName = "total")
    private String total;
    
    @DynamoDBAttribute(attributeName = "inWords")
    private String inWords;
    
    private List<Item> items;
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getInvoiceId() {
        return invoiceId;
    }
    
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getTo() {
        return to;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getSgst() {
        return sgst;
    }
    
    public void setSgst(String sgst) {
        this.sgst = sgst;
    }
    
    public String getTotal() {
        return total;
    }
    
    public void setTotal(String total) {
        this.total = total;
    }
    
    public String getInWords() {
        return inWords;
    }
    
    public void setInWords(String inWords) {
        this.inWords = inWords;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "Invoice{" +
                "customerId='" + customerId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", date='" + date + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount='" + amount + '\'' +
                ", sgst='" + sgst + '\'' +
                ", total='" + total + '\'' +
                ", inWords='" + inWords + '\'' +
                ", items=" + items +
                '}';
    }
}
