


@UI
Feature: Login

  Background:
    Given user is on Login page


  Scenario: Check login with valid data
    When user logs in with the following credentials:
      | userName | test          |
      | password | UserUser@1 |
    Then "Login" page is displayed

  @Negative
  Scenario Outline: Check login with invalid data
    When user logs in with the following credentials:
      | userName | <username> |
      | password | <password> |
    Then "Invalid username or password!" message is displayed
    Examples:
      | username | password      |
      | test     | User          |
      | te       | UserUser@1 |
      | xx       | xxghg         |
      | yyjgj    | yy            |

