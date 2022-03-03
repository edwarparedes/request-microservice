package com.nttdata.request.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Request {
    @Id
    private String id;
    private String purchaseRate;
    private String sellingRate;
    private String status;
    private Double amount;
    private String payMode;
    private String sellerBuyer;
    private String sellerPhone;
    private String operationId;
}
