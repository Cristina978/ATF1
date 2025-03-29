
@UI
Feature: Login

  Background:
    Given User is on "Login" page

  Scenario: Check User login with valid data
    When User logs in with the following credentials:
      | userName | test          |
      | password | UserUser@1    |
    Then "Profile" page is displayed

  @Negative
  Scenario Outline: Check login with invalid data
    When User logs in with the following credentials:
      | userName | <username> |
      | password | <password> |
    Then "Invalid username or password!" message is displayed
    Examples:
      | username | password      |
      | test     | u             |
      | t        | UserUser@1    |
      | xx       | .;@=          |
      | 21$%^    | yy            |

  Scenario Outline: Check login with empty data
    When User logs in with the following credentials:
      | userName | <username> |
      | password | <password> |
    Then Fields are highlighted in red color
    Examples:
      | username | password      |
      |          |               |
      | test     |               |
      |          | UserUser@1    |

