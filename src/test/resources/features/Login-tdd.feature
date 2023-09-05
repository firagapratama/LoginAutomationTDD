Feature: Login with TDD

  @TDD
  Scenario Outline: Login
    Given User is on login page
    When User input <username> and <password>
    And User click login button
    Then User get verify login <result>

    Examples:
    | username | password | result |
    | 90000004 | satu2tiga | Success |
    | 90000003 | satuduatiga | Failed |
    | 900000030 | satu2tiga  | Failed |

