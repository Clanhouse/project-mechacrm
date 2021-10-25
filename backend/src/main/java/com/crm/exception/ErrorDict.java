package com.crm.exception;

public class ErrorDict {

    public static final String PAGINATED_INVALID_PAGE = "Page has to be greater or equal to 0";
    public static final String PAGINATED_INVALID_SIZE = "Size has to be greater or equal to 1";
    public static final String PAGINATED_EMPTY_PAGE = "Page can't be empty";
    public static final String PAGINATED_EMPTY_SIZE = "Size can't be empty";

    public static final String CAR_NOT_FOUND = "Nie znaleziono samochodu o podanym ID";
    public static final String CAR_VIN_INVALID = "Niepoprawny numer VIN";
    public static final String VIN_FORMAT_INVALID = "Niedozwolone znaki w numerze VIN";
    public static final String REGISTRATION_NUMBER_NOT_FOUND = "Nie znaleziono samochodu o podanym numerze rejestracyjnym";
    public static final String REGISTRATION_NUMBER_FORMAT_INVALID = "Niedozwolone znaki w numerze rejestracyjnym";
    public static final String REGISTRATION_NUMBER_LENGTH_MUST_BETWEEN = "Numer rejestracyjny powinien zawierać się w przedziale od 3 do 10 znaków";
    public static final String CAR_BRAND_INVALID = "Nazwa marki samochodu nie może być pusta";
    public static final String BRAND_LENGTH_MUST_BETWEEN = "Nazwa marki samochodu powinna zawierać się w przedziale od 3 do 30 znaków";
    public static final String CAR_MODEL_INVALID = "Nazwa modelu samochodu nie może być pusta";
    public static final String MODEL_LENGTH_NOT_GREATER_THAN = "Nazwa modelu samochodu nie może być dłuższa niż 30 znaków";
    public static final String CAR_PRODUCTION_YEAR_INVALID = "Niepoprawny rok produkcji samochodu";
    public static final String CAR_MILEAGE_INVALID = "Niepoprawny przebieg samochodu";
    public static final String DESCRIPTION_LENGTH_NOT_GREATER_THAN = "Dodatkowy opis samochodu nie może być dłuższy niż 250 znaków";
    public static final String CAR_TYPE_INVALID = "Niepoprawny typ samochodu";

    public static final String CUSTOMER_NOT_FOUND = "Nie znaleziono klienta o podanym ID";
    public static final String CUSTOMER_NAME_INVALID = "Imię klienta nie może być puste";
    public static final String NAME_LENGTH_MUST_BETWEEN = "Imię powinno zawierać się w przedziale od 3 do 30 znaków";
    public static final String CUSTOMER_SURNAME_INVALID = "Nazwisko klienta nie może być puste";
    public static final String SURNAME_LENGTH_MUST_BETWEEN = "Nazwisko powinno zawierać się w przedziale od 3 do 30 znaków";
    public static final String CUSTOMER_PHONE_INVALID = "Numer telefonu klienta nie może być pusty";
    public static final String PHONE_NUMBER_FORMAT_INVALID = "Numer telefonu powinien być w formacie np. 600700800";
    public static final String CUSTOMER_ADDRESS_INVALID = "Adres klienta nie może być pusty";
    public static final String ADDRESS_LENGTH_MUST_BETWEEN = "Adres powinien zawierać się w przedziale od 5 do 50 znaków";

    public static final String USER_DISABLED = "USER DISABLED";
    public static final String LOGIN_FORMAT_INVALID = "LOGIN FORMAT INVALID";
    public static final String PASSWORD_FORMAT_INVALID = "PASSWORD FORMAT INVALID";
    public static final String INVALID_CREDENTIALS = "INVALID CREDENTIALS";
    public static final String ACCOUNT_IS_ACTIVE = "ACCOUNT IS ACTIVE";
    public static final String ACCOUNT_ALREADY_EXIST_BY_LOGIN = "ACCOUNT ALREADY EXIST WITH THIS LOGIN";
    public static final String ACCOUNT_DOES_NOT_EXIST = "ACCOUNT DOES NOT EXIST";
    public static final String LOGIN_CAN_NOT_BE_EMPTY = "USERNAME CAN'T BE EMPTY";
    public static final String PASSWORD_CAN_NOT_BE_EMPTY = "PASSWORD CAN'T BE EMPTY";
    public static final String LOGIN_LENGTH_MUST_BETWEEN = "PASSWORD MUST BE BETWEEN 2 TO 50 CHARACTERS";
    public static final String PASSWORD_LENGTH_MUST_BETWEEN = "PASSWORD MUS BE BETWEEN 8 TO 50 CHARACTERS";
    public static final String EMAIL_FORMAT_INVALID = "EMAIL BAD FORMAT";
    public static final String ACCOUNT_ALREADY_EXIST_BY_EMAIL = "ACCOUNT ALREADY EXIST WITH THIS EMAIL";
    public static final String TOKEN_EXPIRED = "TOKEN IS EXPIRED";
}
