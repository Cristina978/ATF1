@UI
Feature: Registration via UI

  Background:
    Given user is on Registration page


  Scenario: Check user registration
    When user register with valid data
    And user check the reCAPTCHA checkbox
    Then user is successful registered
#
##  Scenario: Check user can cancel registration process
##    When user cancel the registration
##    Then "Login" page is displayed