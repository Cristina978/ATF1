@API
Feature: User Registration via API

  @DeleteUser
  Scenario: Validate registration user
    Given User provides the following credentials:
      | userName  | test         |
      | password  | UserUser@16   |
    When user registers an account
    Then response has status code 201
    And authorization token is generated
    And the new user is created

  @Negative
  Scenario Outline: Validate registration with invalid data
    Given User provides the following credentials:
      | userName | <userName> |
      | password  | <password>  |
    When user registers an account
    Then response status code has <expectedStatusCode>
    And <errorMessage> error message is displayed
    Examples:
      | userName  |  password   | errorMessage          | expectedStatusCode |
      |           |  12345678   | REQUIRED_FIELDS       | 400                |
      | Nick      |             | REQUIRED_FIELDS       | 400                |
      | Amanda    |  @          | WRONG_PASSWORD_LENGTH | 400                |
      | test      | UserUser@1  | USER_EXISTS           | 406                |