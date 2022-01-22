package com.crm.exception;

public class ErrorDict {

    public static final String PAGINATED_INVALID_PAGE = "Numer strony musi być większy bądź równy 0";
    public static final String PAGINATED_INVALID_SIZE = "Rozmiar strony musi być większy bądź równy 1";
    public static final String PAGINATED_EMPTY_PAGE = "Parametr numeru strony nie może być pusty";
    public static final String PAGINATED_EMPTY_SIZE = "Parametr rozmiaru strony nie może być pusty";

    public static final String CAR_NOT_FOUND = "Nie znaleziono samochodu o podanym ID";
    public static final String CAR_VIN_INVALID = "Niepoprawny numer VIN";
    public static final String VIN_FORMAT_INVALID = "Niedozwolone znaki w numerze VIN";
    public static final String VIN_NOT_FOUND = "Podany numer VIN nie istnieje";
    public static final String VIN_LENGTH_INVALID = "Błędna długość numeru VIN, wymagane jest 17 znaków";
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
    public static final String CAR_CREATE_VIN_EXISTS = "Podany numer VIN już istnieje w bazie";
    public static final String CAR_CREATE_REGISTRATION_NUMBER_EXISTS = "Podana tablica rejestracyjna już istnieje w bazie";

    public static final String CUSTOMER_NOT_FOUND = "Nie znaleziono klienta o podanym ID";
    public static final String CUSTOMER_NAME_INVALID = "Imię klienta nie może być puste";
    public static final String NAME_LENGTH_MUST_BETWEEN = "Imię powinno zawierać się w przedziale od 3 do 30 znaków";
    public static final String CUSTOMER_SURNAME_INVALID = "Nazwisko klienta nie może być puste";
    public static final String SURNAME_LENGTH_MUST_BETWEEN = "Nazwisko powinno zawierać się w przedziale od 3 do 30 znaków";
    public static final String CUSTOMER_PHONE_INVALID = "Numer telefonu klienta nie może być pusty";
    public static final String PHONE_NUMBER_FORMAT_INVALID = "Numer telefonu powinien być w formacie np. 600700800";
    public static final String CUSTOMER_DUPLICATE = "Użytkownik o podanych danych już istnieje w bazie";
    public static final String CUSTOMER_PHONE_EXISTS = "Podany numer telefonu już istnieje w bazie i jest przypisany do innego użytkownika";

    public static final String USER_DISABLED = "Użytkownik zablokowany";
    public static final String LOGIN_FORMAT_INVALID = "Format nazwy użytkownika jest niepoprawny";
    public static final String PASSWORD_FORMAT_INVALID = "Format hasła jest niepoprawny";
    public static final String INVALID_CREDENTIALS = "Nazwa użytkownika bądź hasło jest niepoprawne";
    public static final String ACCOUNT_IS_ACTIVE = "Konto jest aktywne";
    public static final String ACCOUNT_DOES_NOT_EXIST = "Konto nie istnieje";
    public static final String LOGIN_CAN_NOT_BE_EMPTY = "Nazwa użytkownika nie może być pusta";
    public static final String PASSWORD_CAN_NOT_BE_EMPTY = "Hasło nie może być puste";
    public static final String LOGIN_LENGTH_MUST_BETWEEN = "Nazwa użytkownika musi zawierać od 2 do 50 znaków";
    public static final String PASSWORD_LENGTH_MUST_BETWEEN = "Hasło musi zawierać od 8 do 50 znaków";
    public static final String EMAIL_FORMAT_INVALID = "Format adresu email jest niepoprawny";
    public static final String ACCOUNT_ALREADY_EXIST_BY_LOGIN = "Konto o takiej nazwie użytkownika już istnieje";
    public static final String ACCOUNT_ALREADY_EXIST_BY_EMAIL = "Konto o takim adresie email już istnieje";
    public static final String TOKEN_EXPIRED = "Token wygasł";
    public static final String ROLE_DOES_NOT_EXIST = "Rola nie istnieje";
}
