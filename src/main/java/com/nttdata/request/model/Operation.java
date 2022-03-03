package com.nttdata.request.model;

import lombok.Data;

@Data
public class Operation {
    private String id;
    private String phoneSend;
    private String phoneReceive;
    private Double amount;
}
