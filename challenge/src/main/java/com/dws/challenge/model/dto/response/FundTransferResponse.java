package com.dws.challenge.model.dto.response;

import lombok.Data;

@Data
public class FundTransferResponse {
    private String message;
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private String status;
}