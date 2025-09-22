@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order

    Given  Logged in with username <name> and password <password>
    When  When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples:
      | name                       | password       | productName     |
      | sedatekmel_91@hotmail.com | Sedo.7534 | ADIDAS ORIGINAL |
