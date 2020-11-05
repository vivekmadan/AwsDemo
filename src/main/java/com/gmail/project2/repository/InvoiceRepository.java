package com.gmail.project2.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.gmail.project2.dto.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepository {
    
    @Autowired
    private DynamoDBMapper mapper;
    
    public void insertInvoice(Invoice invoice) {
        mapper.save(invoice);
    }

}
