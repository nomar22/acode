# Avenue Code Test Implementation

This project is made to apply a test from [Avenue Code](https://www.avenuecode.com) for Java developer role.

It was developped a Restful service using JAX-RS (as demanded) to perform CRUD operations on Products using Image as a sub-resource of Product.


## Installation
* Ensure that Java 8 and Maven 3.2 are installed
* Clone this repo:
  git clone https://github.com/nomar22/acode.git


## Usage

### Running The Application
Navigate to the directory and exexute : <b>mvn spring-boot:run</b>
Once started you can check the service on : `http://localhost:8080/v1/products`

The port number can be changed by editing the port property on `src/main/resources`

### Convert Swagger spec into HTML doc
The swagger2markup and asciidoc maven plugins are used to publish API as HTML. To use: `mvn clean test site`

The documentation will be created in `target/generated-docs/html`




## Contacts

* LinkedIn: [https://www.linkedin.com/in/rafael-ramon-684a6320](https://www.linkedin.com/in/rafael-ramon-684a6320/?locale=en_US)
* Email: [nomar22@gmail.com](nomar22@gmail.com)
* Phone: +55 31 97534-6631
