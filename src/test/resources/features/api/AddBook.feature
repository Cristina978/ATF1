@API
Feature: Add and remove a book in user's profile via API

  Background:
    Given the user provides the following credentials:
      | userName  | test         |
      | password  | UserUser@2   |
    When the user attempts to registers an account
    Then the response has status code 201
    And an authorization token is generated
    And the new user is successfully created

  @DeleteUser
  Scenario: Validate that user can add a book
    Given the user has retrieved the list of available books
    When the user adds a book to their profile
    And the added book is verified in the user profile
    Then the book is removed from the user's profile
