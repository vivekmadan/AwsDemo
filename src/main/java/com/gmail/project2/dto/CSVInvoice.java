package com.gmail.project2.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;

import java.util.Date;

public class CSVInvoice {
    
    @CsvBindByPosition(position = 0)
    private String invoiceId;
    
    @CsvBindByPosition(position = 1)
    private String customerId;
    
    @CsvBindByPosition(position = 2)
    @CsvDate(value = "yyyy-MM-dd")
    private Date date;
    
    @CsvBindByPosition(position = 3)
    private String from;
    
    @CsvBindByPosition(position = 4)
    private String to;
    
    @CsvBindByPosition(position = 5)
    private double amount;
    
    @CsvBindByPosition(position = 6)
    private double sgst;
    
    @CsvBindByPosition(position = 7)
    private double total;
    
    public String getInvoiceId() {
        return invoiceId;
    }
    
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
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
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getSgst() {
        return sgst;
    }
    
    public void setSgst(double sgst) {
        this.sgst = sgst;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
}
