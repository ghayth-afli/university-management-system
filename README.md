
# University Management System 

The "University Management System" is a comprehensive software solution designed to streamline and enhance the management of educational institutions, specifically universities. It provides a robust and user-friendly platform for administrators, faculty, and students to efficiently manage various aspects of university operations.


## Installation

1- Clone this repository:

```bash
git clone https://github.com/ghayth-afli/university-management-system.git
cd university-management-system
```
2- Install client dependencies:
```bash
npm install
```

## Client Development

To start the client development server:

```bash
  npm start
```


## Server
1- Download Database tables from http://bit.ly/3u7LH43

2- Import Database tables using mysql workbench

3- Navigate to the server directory:

```bash
  cd server
```

4- Configure the server properties in src/main/resources/application.properties, including the database connection settings.

5- run the server locally:
```bash
  ./mvnw spring-boot:run
```
The server will be accessible at http://localhost:8081.
