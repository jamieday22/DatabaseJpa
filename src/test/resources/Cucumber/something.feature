Feature: Is it Spanish?
  As a person i want to know if its spanish
  Scenario: Language is not spanish
    Given i have chosen a film
    When film is not spanish
    Then i should be told "no"
