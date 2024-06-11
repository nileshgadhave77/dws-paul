package com.dws.challenge.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class FundTransfer {

    private String accountFromId;
    private String accountToId;
    private BigDecimal amount;
    private String accountHolderName;
    private String transferReason;

}
