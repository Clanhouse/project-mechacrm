package com.crm.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDict {
    public static final String PAGINATED_INVALID_PAGE = "Page has to be greater or equal to 0";
    public static final String PAGINATED_INVALID_SIZE = "Size has to be greater or equal to 1";
    public static final String USER_DISABLED = "USER DISABLED";
    public static final String INVALID_CREDENTIALS = "INVALID CREDENTIALS";
    public static final String ACCOUNT_IS_ACTIVE = "ACCOUNT IS ACTIVE";
    public static final String ACCOUNT_ALREADY_EXIST = "ACCOUNT ALREADY EXIST";
    public static final String ACCOUNT_DOES_NOT_EXIST = "ACCOUNT DOES NOT EXIST";
    public static final String LOGIN_CAN_NOT_BE_EMPTY = "Nazwa użytkownika nie może być pusta";
    public static final String PASSWORD_CAN_NOT_BE_EMPTY = "Hasło nie może być puste";
    public static final String LOGIN_LENGTH_CAN_NOT_BE_GREATER_THAN = "Nazwa użytkownika nie może być dłuższa niż 50 znaków";
    public static final String PASSWORD_LENGTH_MUST_BETWEEN = "Hasło powinno zawierać się w przedziale od 8 do 15 znaków";
    public static final String EMAIL_FORMAT_INVALID = "Zły format e-maila";
}
