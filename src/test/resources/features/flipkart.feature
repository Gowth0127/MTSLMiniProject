Feature: Flipkart Product Search

  @smoke
  Scenario: Search for an iPhone
    Given I am on the Flipkart homepage
    When I search for product
    And I select the first product
    Then I should see the model name and price
