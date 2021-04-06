#Author: Bhaskar Joshi

Feature: JSON feature
  This feature file is for sample test

  @RegressionTest
  Scenario Outline: Run the GET method and verify the respone text
    Given I want to run a get method "<BinID>"
    Then I validate the outcomes of the return with "<TextValue>"

	Examples:
	|BinID                   |TextValue    |
	|606afba38be464182c590364|Hello        |
	|606afe767d0d5e1833cf6464|Hello        |
	|606b65967d0d5e1833cf9b2c|happy testing|
	
	@RegressionTest
  Scenario: Run the POST method and verify the resposne text
    Given I want to run a post method
    Then I validate the outcomes of the return with "Hello"
    
  @RegressionTest
  Scenario: Run the POST method with user entries and verify the respone
    Given I want to run a post method with sample value as "happy testing"
    Then I validate the outcomes of the return with "happy testing"
    
  @RegressionTest
  Scenario: Run the PUT method with updated user entries and verify the respone
    Given I want to run a put method with sample value as "hello world 123" for binid "606b66d56397691864742435"
    Then I validate the outcomes of the return with "hello world 1" 
    
  @RegressionTest
  Scenario: Run the DELETE method for bin id entered by user and verify the respone
    Given I want to run a delete method for binid "606b7b157d0d5e1833cfa668"
    Then I validate the outcomes of the return with "Bin deleted successfully" 