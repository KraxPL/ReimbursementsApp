
Before you begin, ensure you have the following software installed on your system:

  Java Development Kit 11 or later
  Apache Maven
    
    

Clone the repository:

    git clone https://github.com/KraxPL/ReimbursementsApp.git
    
Open a command prompt or terminal window.

Navigate to the root directory of the cloned repository:

    cd path/to/ReimbursementsApp

    

From the command prompt or terminal, navigate to the root directory of application.

  Build the application using Maven:

    mvn clean package

  This will compile the source code, run tests, and package the application into a WAR file.

Running Tests

  After building the application, you can run tests using the following Maven command:

    mvn test

Check the test results in the console output.



Running the Application

  Deploy the built WAR file to a servlet container like Apache Tomcat.
  Start the servlet container.
  Open a web browser and navigate to the application URL:

    http://localhost:8080/

You should now see application running
To log in as an admin, use the following credentials:

    Email: admin@mail.com
    Password: admin123


