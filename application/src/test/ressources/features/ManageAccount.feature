@todo
Feature: A user should be able to manager his account and transactions histories
         when mean with user everyone use our api system or person

  Background:
    Given  the list of accounts is:
      | id | accountNumber | name  | creationDate        | amount |
      | 1  | BB12345       | user1 | 03/01/2019 00:00:00 | 150.00 |
      | 2  | BB12346       | user2 | 05/03/2018 00:00:00 | 50.00  |

  Scenario: A user should be able to deposit a valid amount to a specific account
    When a user request to deposit 50.00 to the account id 1
    Then the amount of account become as bellow:
      | id | accountNumber | name  | creationDate        | amount |
      | 1  | BB12345       | user1 | 03/01/2019 00:00:00 | 200.00 |

  Scenario: A user should be able to withDraw a valid amount to a specific account

    When a user request to withDraw 50.00 to the account id 1
    Then the amount of account become as bellow:
      | id | accountNumber | name  | creationDate        | amount |
      | 1  | BB12345       | user1 | 03/01/2019 00:00:00 | 100.00 |

  Scenario: A user should be able to transfer a valid amount from account to other

    When user1 with account id 1 requests to transfer 50 to user2 with account id 2
    Then the amount of account become as bellow:
      | id | accountNumber | name  | creationDate        | amount |
      | 1  | BB12345       | user1 | 03/01/2019 00:00:00 | 100.00 |
      | 2  | BB12346       | user2 | 05/03/2018 00:00:00 | 100.00 |
    And the history of transaction saved as bellow:
      | id | payer | payee | amount | status    | creationDate        |
      | 1  | 1     | 2     | 50     | COMPLETED | 03/03/2019 10:00:00 |

  Scenario: A payer should  be able to see his own transactions histories

    Given the list of histories transactions as bellow:
      | id | payer | payee | amount | status    | creationDate        |
      | 1  | 1     | 2     | 50     | COMPLETED | 03/01/2019 10:01:00 |
      | 2  | 2     | 1     | 10     | COMPLETED | 12/17/2018 00:00:00 |

    When when user1 with account ID 1 request to see his transactions histories

    Then he sees the histories as bellow:
      | id | payer | payee | amount | status    | creationDate        |
      | 1  | 1     | 2     | 50     | COMPLETED | 03/01/2019 10:01:00 |
      | 2  | 2     | 1     | 10     | COMPLETED | 12/17/2018 00:00:00 |


#    -----------------------------UNHAPPY PATH----------------------------------

  Scenario: A user should not be able to transfer a negative amount from account to other

    When user1 with account id 1 tires to transfer a negative amount 20.00 to user2 with account id 2
    Then he can't

  Scenario: A user should not be able to transfer an amount bigger than his balance from account to other

    When  user1 with account id 1 tires to transfer 200,00 to user2 with account id 2
    Then he can't