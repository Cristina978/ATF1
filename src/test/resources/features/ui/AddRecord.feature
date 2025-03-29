@UI
Feature: Add Record in WebTable

  Background:
    Given User is on "Login" page

  Scenario: Check user can add a record in Web Table
    When User navigates to "WebTables" page
    And User clicks on Add button
    And User complete the registration form
    Then New user is successful added in table