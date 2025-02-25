@UI
Feature: Registration via UI

  Background:
    Given user is on Login page


  Scenario: Check user is able to cancel registration process
    When User clicks on "New User" button
    And User fill in the registration form
    | firstname | test       |
    | lastname  | test       |
    | userName  | user1      |
    | password  | UserUser@1 |
    And User cancel the registration form
    Then User is redirected on "Login" page
