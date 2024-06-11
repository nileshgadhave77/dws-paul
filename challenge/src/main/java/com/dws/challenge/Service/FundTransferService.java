package com.dws.challenge.Service;

import com.dws.challenge.model.dto.request.FundTransfer;
import com.dws.challenge.model.dto.response.FundTransferResponse;
import org.springframework.stereotype.Service;

@Service
public interface FundTransferService {

    public FundTransferResponse transferFund(final FundTransfer fundTransfer);
}
