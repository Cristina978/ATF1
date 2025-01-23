#@UI
#  Feature: Registration
#    Background:
#      Given user is on Registration page
#
#
#    Scenario: Check registration with valid data
#      When user register with the following credentials:
#        | FirstName | Amanda |
#        | LastName  | Smith  |
#        | UserName  | amandasmith |
#        | Password  | pocahontas1! |
#      Then user is successful registered
