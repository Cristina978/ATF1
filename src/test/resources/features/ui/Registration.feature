@UI
Feature: Registration via UI

  Background:
    Given user is on Registration page


  Scenario: Check user is able to cancel registration process
    When user cancel the registration form
    Then user is on "Login" page
