Feature: Login with TDD

  @TDD
  Scenario Outline: Login
    Given User is on login page
    When User input <username> and <password>
    And User click login button
    Then User get verify login <result>

    Examples:
    | username | password | result |
    | {your_right_username} | {your_right_password} | Success |
    | {your_right_username} | {your_wrong_password} | Failed |
    | {your_wrong_username} | {your_right_password}  | Failed |

