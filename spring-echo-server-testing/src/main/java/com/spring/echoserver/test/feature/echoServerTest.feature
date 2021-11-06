Feature: Echo Server Test

  #Basic Test
  Scenario: Send Custom Header
    When User want to do rest API with Custom header
    Then Custom Header Appear in Response
