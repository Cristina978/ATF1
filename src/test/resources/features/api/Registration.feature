@API
Feature: User Registration via API

  @DeleteUser
  Scenario: Validate user registration with valid data
    Given the user provides the following credentials:
      | userName  | test         |
      | password  | UserUser@3   |
    When the user attempts to registers an account
    Then the response has status code SC_CREATED
    And an authorization token is generated
    And the new user is successfully created

  @Negative
  Scenario Outline: Validate user registration with invalid data
    Given the user provides the following credentials:
      | userName | <userName> |
      | password | <password> |
    When the user attempts to registers an account
    Then the response status code has <expectedStatusCode>
    And error message <errorMessage> is displayed
    Examples:
      | userName  |  password   | errorMessage          | expectedStatusCode |
      |           |  12345678   | REQUIRED_FIELDS       | 400                |
      | Nick      |             | REQUIRED_FIELDS       | 400                |
      | Amanda    |  @          | WRONG_PASSWORD_LENGTH | 400                |
      | test      | UserUser@1  | USER_EXISTS           | 406                |