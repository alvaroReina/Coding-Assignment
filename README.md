# Intersecting Rectangles

This program is a command line application that finds intersections between rectangles in a JSON file.
Also offers a public API to integrate the intersection finding algorithm in an existing application.

## Instalation

## Prerequisites
- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
- [Maven](https://maven.apache.org/install.html)

## Build
Clone the repository:
```
git clone https://github.com/alvaroReina/Coding-Assignment.git
```
Do a clean installation:
```
mvn clean install
```
## Usage
To run it as a command line application, `cd` into the cloned directory and use the jar with dependencies with a file to read as an argument.  

Example with the json file provided:  
```
java -jar target/intersecting-rectangles-1.0-SNAPSHOT-jar-with-dependencies.jar rects.json
```
