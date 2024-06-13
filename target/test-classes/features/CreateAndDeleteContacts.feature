#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Contacts
  I want to create a contact in CONTACTS and delete the same contact in CONCEIRGE

  @tag1
  Scenario: Create and Delete Contacts
    Given Signin to "Contact Home" Page
    And Click Contacts Tab
    When Create new contact
    And Verify new contact created succesfully
    And Signin to "Conceirge Home" Page
    Then Click Contacts Tab
    And Verify new contact created succesfully
    And Delete the contact
    Then Verify the contact is deleted successfully

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
