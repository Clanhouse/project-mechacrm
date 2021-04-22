package com.crm.dto.mapper;

import com.crm.dto.response.CarResponse;
import com.crm.model.db.CarEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    private final ModelMapper modelMapper;

    public CarMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarResponse convertToDto(CarEntity carEntity) {
        return modelMapper.map(carEntity, CarResponse.class);
    }

    public CarEntity convertToEntity(CarResponse carResponse) {
        return modelMapper.map(carResponse, CarEntity.class);
    }
}
