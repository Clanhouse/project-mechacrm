package com.crm.dto.request;

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

import static com.crm.exception.ErrorDict.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CarRequest {

    // Allowing only numbers, CAPITAL and small letters without O(o), I(i) and Q(q)
    private static final String VIN_PATTERN = "[A-HJ-NPR-Za-hj-npr-z0-9]*";

    // Allowing only numbers, CAPITAL and small letters
    private static final String REGISTRATION_NUMBER_PATTERN = "[A-Za-z0-9]*";

    private Long id;

    @NotBlank(message = CAR_VIN_INVALID)
    @Pattern(regexp = VIN_PATTERN, message = VIN_FORMAT_INVALID)
    @Size(min = 17, max = 17, message = CAR_VIN_INVALID)
    private String vin;

    @Size(min = 3, max = 10, message = REGISTRATION_NUMBER_LENGTH_MUST_BETWEEN)
    @Pattern(regexp = REGISTRATION_NUMBER_PATTERN, message = REGISTRATION_NUMBER_FORMAT_INVALID)
    private String registrationNumber;

    @NotBlank(message = CAR_BRAND_INVALID)
    @Size(min = 3, max = 30, message = BRAND_LENGTH_MUST_BETWEEN)
    private String brand;

    @NotBlank(message = CAR_MODEL_INVALID)
    @Size(max = 30, message = MODEL_LENGTH_NOT_GREATER_THAN)
    private String model;

    @NotNull(message = CAR_PRODUCTION_YEAR_INVALID)
    @Min(value = 1950, message = CAR_PRODUCTION_YEAR_INVALID)
    private Integer productionYear;

    @NotNull(message = CAR_MILEAGE_INVALID)
    @Min(value = 0, message = CAR_MILEAGE_INVALID)
    private Integer mileage;

    @Size(max = 250, message = DESCRIPTION_LENGTH_NOT_GREATER_THAN)
    private String description;

    @NotNull(message = CAR_TYPE_INVALID)
    private CarTypeEntity carTypeEntity;
}
