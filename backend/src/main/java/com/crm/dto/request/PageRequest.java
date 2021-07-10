package com.crm.dto.request;

import com.crm.exception.validator.ValidParam;
import lombok.Setter;

import static com.crm.exception.ErrorDict.PAGINATED_EMPTY_PAGE;
import static com.crm.exception.ErrorDict.PAGINATED_EMPTY_SIZE;
import static com.crm.exception.ErrorDict.PAGINATED_INVALID_PAGE;
import static com.crm.exception.ErrorDict.PAGINATED_INVALID_SIZE;

@Setter
public class PageRequest {

    @ValidParam(emptyParamMessage = PAGINATED_EMPTY_PAGE, invalidParamMessage = PAGINATED_INVALID_PAGE)
    private String page = "0";
    @ValidParam(emptyParamMessage = PAGINATED_EMPTY_SIZE, invalidParamMessage = PAGINATED_INVALID_SIZE)
    private String size = "20";

    public int getPage() {
        return Integer.parseInt(page);
    }

    public int getSize() {
        return Integer.parseInt(size);
    }
}
