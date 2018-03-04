# README

This project was developed with the intention to show to the [Avenue Code](https://www.avenuecode.com/) recruiters my capabilities to be part of the team.

It delivers a JAX-RS API to perform CRUD operations on a Product resource using Image as a sub-resource of Product.

It implements the following concepts:

  * [Entity](#entities) - A model domain object defined by its identity.
  * [Repository](#repositories) - An object that mediates between the entities and the persistence layer.
  * [Resouce](#resources) - An object receives all requests and comunicate with the business logic layer.
  * [Service](#services) - An object that mediates the comunication between the resources and the repositories. Contains the business logic.


## Frameworks, Components and Database

  * To develop this API, was used the [Spring](https://spring.io/), using mainly Spring Boot, and Spring Data to perform the data operations.
  * To meet the technical criterias, was used the [Jersey](https://jersey.github.io/) Component to allow the JAX-RS, instead of use Spring MVC .
  * To test the REST requisitions, was used the [Rest Assured](http://rest-assured.io/) framework.
  * The database chosen was [H2](http://www.h2database.com/html/main.html) Database.

## Operations

### GET


### POST


### PUT


### DELETE

## Database access

## Running The Application

To run the application the command used is <b>mvn spring-boot:run</b>

## Problems

  * Running The Tests

    Unfortunatelly, I couldn't get the tests running by maven commands usage.
    I had problems configuring the Jersey, Rest Assured and Spring together.
    The problem I had was that while the maven test command was being executed, the tests weren't finding the web context to perform the requisitions in the API.

## Contacts

* LinkedIn: [https://www.linkedin.com/in/jonathan-de-paula-675595120/](https://www.linkedin.com/in/jonathan-de-paula-675595120/?locale=en_US)
* Email: [jonathanpaula22@gmail.com](jonathanpaula22@gmail.com)
* Phone: +55 31 98747-4093