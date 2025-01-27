@UI
Feature: Add Record in WebTable

  Background:
    Given user navigates to "WebTables" page


  Scenario: Check user can add new user in the Web Table
    When user complete the form
    Then new user is successful added