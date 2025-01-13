@UI
Feature: Login functionality
  As a user
  I want to login in to the application

  @Positive
  Scenario: Login with valid credentials
    Given User is on Login page
    When User enter the following credentials
    | UserName | test |
    | Password | Continental@1 |
    Then "Profile" page is displayed

    @Negative
    Scenario Outline: Login with invalid credentials
      Given User is on Login page
      When User enter the following credentials
      | UserName | <username> |
      | Password | <password> |
      Then "Invalid username or password!" error message is displayed
      Examples:
      | username | password      |
      | test     | Cont          |
      | te       | Continental@1 |
      |          | Continental@1 |
      | test     |               |
