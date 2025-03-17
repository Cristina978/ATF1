package demoqa.project.enums;

public enum LoginFields {
    USERNAME("userName"),
    PASSWORD("password"),
    FIRSTNAME("firstname"),
    LASTNAME("lastname");


    private final String fieldName;

    LoginFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
