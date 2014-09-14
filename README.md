Pocket-Calculator
=================

### Requirement

Your task is to build a web application that serves as pocket calculator. The four basic operations plus, minus, multiply and divide has to be implemented. The required UI components are: 10 digit display for numbers, 10 button numpad, 4 buttons for arithmetic operators, a button for the decimal mark, a button to calculate the result and a button to reset the calculation and clear the number display.

The website may only contain the visual elements and styles. All calculations has to done on the server side. The business logic has to be written by yourself. You may choose a frame works that suits the requirements best.

### Dependency

* Apache Maven 3+
* Java 1.7

### Installation

* Checkout project
* Run maven command below
```
mvn clean jetty:run
```
It will download all dependencies on first run
* Open your browser http://localhost:8080

Project is also deployed on Heroku. 

http://guarded-ridge-7544.herokuapp.com/

### Frameworks Used
* Spring Framework
* Jersey
* AngularJS

### API
```
/api/calculator/add/:first/:second
/api/calculator/subtract/:first/:second
/api/calculator/multiply/:first/:second
/api/calculator/divide/:first/:second
```
