@FeatureTag
Feature: Test Feature File

  @Sc1
  Scenario Outline: Validate the ID of a given city
    Given we make a GET call of "<city>"
    When we request the ID
    Then we should see "<id>"
    Examples:
      | city      | id      |
      | Atlanta   | 4180439 |
      | Savannah  | 4221552 |
      | Nashville | 4644585 |


  @Sc2
  Scenario Outline: Validate status code of 200 for valid zip code
    Given we make a GET call for zip code: "<zip>"
    When we request the status code
    Then we should confirm "<status>"
    Examples:
      | zip    | status |
      | 30022  | 200    |
      | 94016  | 200    |
      | 123456 | 404    |