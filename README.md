# Course Report

Course Report is a Spring Boot application designed to integrate with the MySQL database and Slack to fetch user data from Course Report's API and notify relevant Slack channels about new or updated/recreated users.

## Overview

The application runs continuously in the background, periodically triggering an API endpoint from [Course Report](https://www.coursereport.com/). This endpoint returns user data matching specified criteria such as page number, page count, start date, and end date. The retrieved data is then processed to identify new or updated users.

## Features

- Integration with [Course Report API](https://wiki.collaborator.biz/docs/api-checklist-report/)
- Database management for user records
- Slack notification for new or updated users

## Installation

To run the application locally, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/talhayaseen57/Course-Report.git
    ```

2. Navigate to the project directory:

    ```bash
    cd Course-Report
    ```

3. Build the project:

    ```bash
    ./mvnw clean package
    ```

4. Run the application:

    ```bash
    java -jar target/course-report-<version>.jar
    ```

## Configuration

Before running the application, ensure you have configured the following:

- Database connection settings in [application.properties](/src/main/resources/application.properties)
- Slack API token for sending notifications in [SlackSecrets.java](/src/main/java/com/sherenterprise/coursereport/service/SlackSecrets.java)
- Course Report API parameters in [SlackSecrets.java](/src/main/java/com/sherenterprise/coursereport/service/SlackSecrets.java)

## Usage

1. Start the application as per the installation instructions.
2. The application will automatically fetch data from Course Report API based on configured parameters.
3. New users or updated user data will be saved in the database.
4. Slack channels will receive notifications containing user contact information.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.
