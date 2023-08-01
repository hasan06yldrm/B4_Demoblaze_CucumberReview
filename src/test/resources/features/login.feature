Feature:Login test- The user should be able to login with valid credentials

  Background: Got to the home page
    Given The user is on the login page

  @plt1
  Scenario: PLT 1
    When The user enters valid credentials
    Then The user verifies welcome miky

    # login test with parameter
  @plt2
  Scenario: PLT 2
    When The user enters "miky" and "Test1234" and click login button
    Then The user verifies welcome "miky"

  @plt3
  Scenario Outline: PLT 3
    When The user enters "<username>" and "<password>" and click login button
    Then The user verifies welcome "<username>"
    Examples:
      | username | password |
      | miky     | Test1234 |

  @plt4
  Scenario Outline: PLT 4
    When The user enters valid username and password
      | username | <user>     |
      | password | <password> |
    Then The user verifies welcome "<user>"
    Examples:
      | user | password |
      | miky | Test1234 |


    # negative login test
  @nlt1
  Scenario Outline: NLT 1
    When The user enters "<invalidUsername>" and "<invalidPassword>" and click login button
    Then The user verifies invalid access "<message>"
    Examples:
      | invalidUsername | invalidPassword | message                                |
      | mikyyy          | Test1234        | User does not exist.                   |
      | miky            | Test1234567     | Wrong password.                        |
      | miky            |                 | Please fill out Username and Password. |
      |                 | Test1234        | Please fill out Username and Password. |
      |                 |                 | Please fill out Username and Password. |

