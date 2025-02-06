@API
Feature: User Registration via API

  @DeleteUser
  Scenario: Validate registration user
    Given User provides the following credentials:
      | userName  | test         |
      | password  | UserUser@15   |
    When user registers an account
    Then response has status code 201
    And authorization token is generated

  @Negative
  Scenario Outline: Validate registration with invalid data
    Given User provides the following credentials:
      | firstName | <userName> |
      | password  | <password>  |
    When user registers an account
    Then response has status code 400
    Examples:
      | userName  |  password |
      |           |  12345678 |
      | Nick      |  12345678 |
      | Nick      |  @ |