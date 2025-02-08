package demoqa.project.enums;

public enum ErrorMessage {
    REQUIRED_FIELDS("UserName and Password required."),
    WRONG_PASSWORD_LENGTH("Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."),
    USER_EXISTS("User exists!");

    final String errorMessageName;

    ErrorMessage(String errorMessageName) {
        this.errorMessageName = errorMessageName;
    }

    public String getErrorMessageName() {
        return errorMessageName;
    }
}
