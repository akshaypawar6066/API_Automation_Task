Before running the project, ensure you have the following tools installed:

Java Development Kit (JDK): You can download and install JDK from the official website.

Maven: You can download and install Maven from the official website.

Allure Command Line Interface (CLI): You can install Allure CLI using Homebrew (on macOS) or Chocolatey (on Windows), or download the zip file from the official website.
Through CMD-->Follow below steps
1.Install Scoop
Install Below commands in the windows power shell.
  a)iex (new-object net.webclient).downloadstring('https://get.scoop.sh')
  b)scoop bucket add extras
  c)scoop install allure
2.Install Allure 
  a)scoop install allure
  b)allure --version   -->It should show installed allure version.
 

Project Execution
Follow these steps to execute the project:

Clone this repository:
git clone <repository_url>

Navigate to the project directory:
cd <Project-Location>

Execute the tests:
mvn clean test
This command will run the test suite using TestNG and generate Allure report.

Viewing Allure Report
After running the tests, you can view the Allure report by following these steps:

Generate and open the Allure report:Navigate to Project Location and execute below command--->
allure serve
