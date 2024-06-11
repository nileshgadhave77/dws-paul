package com.dws.challenge.controller;


import com.dws.challenge.Exception.AmountNotMatchException;
import com.dws.challenge.Service.FundTransferService;
import com.dws.challenge.Service.NotificationService;
import com.dws.challenge.model.dto.request.FundTransfer;
import com.dws.challenge.model.dto.response.FundTransferResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/v1/transfer")
public class FundTransferController {

    @Autowired
    FundTransferService fundTransferService;

    @Autowired
    NotificationService notificationService;

    @PostMapping
    public ResponseEntity<FundTransferResponse> fundTransfer(@RequestBody final FundTransfer fundTransfer) throws AmountNotMatchException {

        log.info("Got fund transfer request from API {}", fundTransfer.toString());
        if(fundTransfer.getAmount().compareTo(BigDecimal.ZERO)==-1)
        {
            throw new AmountNotMatchException(" Amount should be greater than 0 ");
        }
        FundTransferResponse fundTransferResponse= fundTransferService.transferFund(fundTransfer);
        if(fundTransferResponse.getStatus().equals("success"))
        {
            notificationService.sendNotification(fundTransfer.getAccountFromId(),fundTransfer.getAccountToId(),fundTransfer.getAmount());
        }
        return ResponseEntity.ok(fundTransferService.transferFund(fundTransfer));
    }
}
