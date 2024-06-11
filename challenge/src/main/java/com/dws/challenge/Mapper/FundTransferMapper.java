package com.dws.challenge.Mapper;

import com.dws.challenge.Entity.FundTransferEntity;
import com.dws.challenge.model.dto.request.FundTransfer;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class FundTransferMapper extends BaseMapper<FundTransferEntity, FundTransfer> {
    @Override
    public FundTransferEntity convertToEntity(FundTransfer dto, Object... args) {
        FundTransferEntity entity = new FundTransferEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }
    @Override
    public FundTransfer convertToDto(FundTransferEntity entity, Object... args) {
        FundTransfer dto = new FundTransfer("1","2",new BigDecimal(0.0),"","");
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}