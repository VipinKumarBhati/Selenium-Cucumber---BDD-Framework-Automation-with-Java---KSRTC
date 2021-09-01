@searchBus
Feature: Search Bus for a particular route in KSRTC

Background:
	Given User navigates to KSRTC website
Scenario Outline:
	When User enters the source and destination from sheet "<SheetName>" and row number <RowNumber>
	And User enters a journey start date
	And User enters a return date
	When User clicks on the search bus button
	Then User successfully navigates to the next page with list of buses
	
	Examples:
	| SheetName  				| RowNumber     |
	| searchBus                 | 0             |