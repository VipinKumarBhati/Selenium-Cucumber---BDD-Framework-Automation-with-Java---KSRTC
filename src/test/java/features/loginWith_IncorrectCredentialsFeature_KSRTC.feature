@invalidLoginTest
Feature: Login to KSRTC account with invalid credentials

Background:
	Given User navigates to KSRTC website 
	And user clicks on Sign in Link 
	
Scenario Outline: Login to account with invalid credentials
	When User enters invalid username and password from sheetname "<SheetName>" and row number <RowNumber>
	And User clicks on the login button
	Then User should not be taken to the successful login page
	
	Examples:
	| SheetName  				| RowNumber     |
	| InValid_Credentials       | 0             |
