package com.crm.dto.request;

import com.crm.exception.ErrorDict;
import com.crm.model.db.CarTypeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CarRequest {

    // Allowing only numbers, CAPITAL and small letters without O(o), I(i) and Q(q)
    private final String VIN_PATTERN = "[A-HJ-NPR-Za-hj-npr-z0-9]*";

    // Allowing only numbers, CAPITAL and small letters
    private final String REGISTRATION_NUMBER_PATTERN = "[A-Za-z0-9]*";

    private Long id;

    @NotBlank(message = ErrorDict.CAR_VIN_INVALID)
    @Pattern(regexp = VIN_PATTERN, message = ErrorDict.VIN_FORMAT_INVALID)
    @Size(min = 17, max = 17, message = ErrorDict.CAR_VIN_INVALID)
    private String vin;

    @Size(min = 3, max = 10, message = ErrorDict.REGISTRATION_NUMBER_LENGTH_MUST_BETWEEN)
    @Pattern(regexp = REGISTRATION_NUMBER_PATTERN, message = ErrorDict.REGISTRATION_NUMBER_FORMAT_INVALID)
    private String registrationNumber;

    @NotBlank(message = ErrorDict.CAR_BRAND_INVALID)
    @Size(min = 3, max = 30, message = ErrorDict.BRAND_LENGTH_MUST_BETWEEN)
    private String brand;

    @NotBlank(message = ErrorDict.CAR_MODEL_INVALID)
    @Size(max = 30, message = ErrorDict.MODEL_LENGTH_NOT_GREATER_THAN)
    private String model;

    @NotNull(message = ErrorDict.CAR_PRODUCTION_YEAR_INVALID)
    @Min(value = 1950, message = ErrorDict.CAR_PRODUCTION_YEAR_INVALID)
    private Integer productionYear;

    @NotNull(message = ErrorDict.CAR_MILEAGE_INVALID)
    @Min(value = 0, message = ErrorDict.CAR_MILEAGE_INVALID)
    private Integer mileage;

    @Size(max = 250, message = ErrorDict.DESCRIPTION_LENGTH_NOT_GREATER_THAN)
    private String description;

    @NotNull(message = ErrorDict.CAR_TYPE_INVALID)
    private CarTypeEntity carTypeEntity;
}
