Feature: Flipkart Product Search

  @smoke
  Scenario: Search for an iPhone
    Given I am on the Flipkart homepage
    When I search for product
    And I select the first product
    Then I should see the model name and price from flipkart   
    And Navigate to amazon homepage
    Then Search for the same product
    And Select the product
    Then I should see the model name and price from amazon
    And Compare both model and prices and add to cart based on low price
