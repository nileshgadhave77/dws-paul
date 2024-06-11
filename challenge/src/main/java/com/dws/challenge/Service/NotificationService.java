package com.dws.challenge.Service;

import com.dws.challenge.model.dto.response.FundTransferResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface NotificationService {

    public FundTransferResponse sendNotification(String accountFromId, String accountToId, BigDecimal amount);

}
