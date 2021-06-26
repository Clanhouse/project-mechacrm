package com.crm.dto.request;

import com.crm.exception.ErrorDict;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {
    @Min(value = 0, message = ErrorDict.CARS_PAGINATED_INVALID_PAGE)
    private Integer page = 0;
    @Min(value = 1, message = ErrorDict.CARS_PAGINATED_INVALID_SIZE)
    private Integer size = 20;
}
