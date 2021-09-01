@validLoginTest
Feature: Login to KSRTC account with valid credentials

Background:
	Given User navigates to KSRTC website
	And user clicks on Sign in Link 

Scenario Outline: Login to account with valid credentials
	When User enters valid username and password from sheetname "<SheetName>" and row number <RowNumber>
	And User clicks on the login button
	Then User should be taken to the successful login page
	
	Examples:
	| SheetName  				| RowNumber     |
	| Valid_Credentials         | 0             |

	