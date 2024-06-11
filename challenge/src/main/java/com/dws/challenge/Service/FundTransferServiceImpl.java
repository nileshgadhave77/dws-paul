package com.dws.challenge.Service;

import com.dws.challenge.Entity.FundTransferEntity;
import com.dws.challenge.Exception.AmountNotMatchException;
import com.dws.challenge.Repository.FundTransferRepository;
import com.dws.challenge.model.dto.request.FundTransfer;
import com.dws.challenge.model.dto.response.FundTransferResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
public class FundTransferServiceImpl implements  FundTransferService{

    @Autowired
    FundTransferRepository fundTransferRepository;


    @Override
    public FundTransferResponse transferFund(FundTransfer request) throws AmountNotMatchException {
        log.info("Sending fund transfer request {}" + request.toString());

        if(request.getAmount().compareTo(BigDecimal.ZERO)<0)
        {
            throw new AmountNotMatchException(" Amount should be greater than 0 ");
        }
        FundTransferEntity entity = new FundTransferEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setStatus(TransactionStatus.PENDING);
        FundTransferEntity optFundTransfer = fundTransferRepository.save(entity);
        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        fundTransferResponse.setTransactionId(UUID.randomUUID().toString());
        fundTransferResponse.setMessage("Success");
        return fundTransferResponse;
    }
}
