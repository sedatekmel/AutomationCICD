@tag
Feature: ErrorValidations

  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order

    Given  I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then  "Incorrect email or password." message is displayed

    Examples:
      | name                        | password  |
      | sedatekmel_91xx@hotmail.com | Sedo.7534 |
