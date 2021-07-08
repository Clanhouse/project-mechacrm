package com.crm.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDict {
    public static final String PAGINATED_INVALID_PAGE = "Page has to be greater or equal to 0";
    public static final String PAGINATED_INVALID_SIZE = "Size has to be greater or equal to 1";
    public static final String USER_DISABLED = "USER DISABLED";
    public static final String INVALID_CREDENTIALS = "INVALID CREDENTIALS";
}
