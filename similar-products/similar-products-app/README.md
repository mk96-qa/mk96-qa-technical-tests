# Similar Products API

## Description

This API returns a list of products similar to a given one based on its id

It is a proof of concept developed for the company MCA IT Solutions, which is why an attempt has been made to demonstrate in broad strokes the knowledge required for the position applied for.

## Improvements

In case of having more time, and dealing with a real task, the following improvements would be applied:

- Improved control of internal errors on service.
- Improved control of errors in calls to external services.
- Addition of specific tests for components, as well as increase the collection of tests

## How to raise API

(Note: you must have maven and java installed on you're device).
 * https://maven.apache.org/install.html
 * https://www.java.com/en/download/help/download_options.html

1º - If you haven't done already, clone the GIT repository in to a folder on your device.

 * Clone comand: "git clone https://github.com/mk96-qa/mk96-qa-technical-tests.git" and enter the "similar-products/similar-products-app" folder

2º - Pull or checkout the branch with the version of the API you want to raise.

 * if you don't have the branch downloaded:  "git checkout [<branch name>]"
 * If you already have the branch downloaded: "git pull" or "git merge [<branch-name>]"
 
3º - Open a console from the folder where the repository was downloaded.

4º - Install the proyect.

 * mvn clean install
 
5º - Run it

 * cd target
 * java -jar similar-products-1.0.0.jar

## Postman Collection
 
 * https://github.com/mk96-qa/mk96-qa-postman-collections/blob/main/similar-products.postman_collection.json
 
## Mock repository

 * ../similar-products-mock (in this same repository)





