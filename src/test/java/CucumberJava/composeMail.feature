Feature: Compose and send the mail through yahoo account

    Scenario Outline: User logs in and sends email
        Given There is a user who visits yahoo login page
        And User login with username "<UserName>" and password "<Password>"
        When User sends an email to "<ToMailId>" with subject "Incubyte" and body "Automation QA test for Incubyte"
        Then The email appears in the sent folder of yahoo with subject "Incubyte"       

        Examples:
        | UserName   		| Password          |ToMailId |                               
        | balajitpgit       | Testtest123$      |balajitpgit@gmail.com |