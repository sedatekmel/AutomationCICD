Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background: I landed on Ecommerce Page

  Scenario Outline: Positive Test of Submitting the order
    Given  Logged in with username <email> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage
    Examples:
      | email                     | password  | productName     |
      | sedatekmel_91@hotmail.com | Sedo.7534 | ADIDAS ORIGINAL |
