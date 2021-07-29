package com.crm.dto.mapper;

import com.crm.dto.request.CarRequest;
import com.crm.dto.response.CarResponse;
import com.crm.model.db.CarEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarMapper {

    private final ModelMapper modelMapper;

    public CarResponse convertToDto(final CarEntity carEntity) {
        return modelMapper.map(carEntity, CarResponse.class);
    }

    public CarEntity convertToEntity(final CarRequest carRequest) {
        return modelMapper.map(carRequest, CarEntity.class);
    }
}
