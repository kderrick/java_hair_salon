# Hair Salon Web App

#### February 26, 2016

#### By Kyle Derrick

## Description

This is a web application written in Java for a hair salon that will allow a user to add stylists employed by the salon as well as the clients associated with each stylist.

## Setup/Installation Requirements

* Clone this repository.
* Make sure you have Gradle and Java installed.
* Start Psql and Postgres
* in PSQL:
* CREATE DATABASE hair_salon;
* Connect to database by using \c command: \c hair_salon;
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, stylistid int, name varchar);
* In the top level of the cloned directory, run the following command in your terminal:

`gradle run`

* Open your web browser of choice to localhost:4567

## Please Make This Better

Please fork this repository and send a pull request if something in here could be better.

## Technologies Used

Java, Spark, JUnit, FluentLenium, Velocity, Bootstrap, PSQL
