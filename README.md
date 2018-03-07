# Avenue Code Test Implementation

This project was made to apply to a role as a Java developer at  [Avenue Code](https://www.avenuecode.com) .

It was developped a Restful service using JAX-RS (as demanded) to perform CRUD operations on Products using Image as a sub-resource of it.


## Installation
* Ensure that Java 8 and Maven 3.2 are installed
* Clone this repo:
  git clone https://github.com/nomar22/acode.git


## Usage

### Run the application
Navigate to the directory and execute : <b>mvn spring-boot:run</b>
Once started you can check the service on : `http://localhost:8080/products` and `http://localhost:8080/images`

The port number can be changed by editing the port property on `src/main/resources/application.properties`

### Test application
Navigate to the directory and execute :<b> mvn test</b>

## API documentation
Once started the application the API documentation is locatted at the main page of project on `http://localhost:8080`
#### GET
/images
Method to manage GET request to get all images in the database
#### POST
/images
Save a new image
#### GET
/images/productImages/{id}
Requests to get all Images of a specific Product
#### GET
/images/{id}
Get a image with its relationships
#### PUT
/images/{id}
Update an existing image
#### DELETE
/images/{id}
Delete an existing image
#### GET
/products
Method to manage GET request to get all products in the database with all its relationships
#### POST
/products
Persist a Product validating it
#### GET
/products/child/{id}
Get all child of products
#### GET
/products/nochild
Method to manage GET request to get all products in the database without its relationships
#### GET
/products/nochild/{id}
Get product without its relationships
#### GET
/products/{id}
Get a product with its relationships
#### PUT
/products/{id}
Update an existing Product
#### DELETE
/products/{id}




## Contacts

* LinkedIn: [https://www.linkedin.com/in/rafael-ramon-684a6320](https://www.linkedin.com/in/rafael-ramon-684a6320/?locale=en_US)
* Email: [nomar22@gmail.com](nomar22@gmail.com)
* Github: [https://github.com/nomar22](https://github.com/nomar22)
* Phone: +55 31 97534-6631
