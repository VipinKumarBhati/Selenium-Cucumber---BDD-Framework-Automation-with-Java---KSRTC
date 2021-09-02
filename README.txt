Pre - Requisites

New Software required in eclipse:
	- Maven
	- TestNG - https://testng.org/testng-eclipse-update-site
	- Natural - https://rlogiacco.github.io/Natural
	- Cucumber Plugin for Eclipse - https://cucumber.github.io/cucumber-eclipse/update-site
	
Dependencies : 
	- Please ensure that "JAVA_HOME" is added in the Environment Variable with your machine's jdk location
	- Please ensure that the jdk location and maven location installed in your system is added in the classpath(** to run using cmd)
	- If execution is done in eclipse
		- Please ensure the JRE System Library is pointed to jdk1.8
		- Please ensure that the Java compiler is set to 1.8
		
Execution Methods :

Using pom.xml :
	- Right click on the pom.xml and hover on Run As option
	- Click on Maven Install(to install all dependency) or Maven Test

Using TestNG :
	- Right click on the testng.xml and hover on Run As option
	- Click on TestNG Suite
	
Using Runner Class :
	- Right click on the MainRunner.java and hover on Run As option
	- Click on JUnit Test
	
Using Command Prompt :
	- Open cmd in the location where the project is present "cd <project_path>"
	- Once the location is set execute the pom.xml using "mvn clean compile test" command
