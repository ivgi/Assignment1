# Start Example

curl  --header "Content-Type: application/json" --request POST --data '{"numIterations":2,"liveCellsPercent":60}' \
 host:port/DevAssignment-1.0/camel/push
 
# Terms

GOL - [Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

# Solution Explanation

Route 1
1. Start from Route 1 which is triggered by a rest endpoint.
curl  --header "Content-Type: application/json" --request POST --data '{"numIterations":2,"liveCellsPercent":60}' \
 <host>:<port>/DevAssignment-1.0/camel/push
2. Json is validated according to file schema.json. Json is converted to Input.java pojo.
3. Using a bean component we log that GOL (Game of Life) is started.
4. Convert Input.java back to json.
5. Send the message to gol.input queue.
6. Respond to user that GOL is started.

Route2
1. Listen and read from queue gol.input
2. Convert json to Input.java pojo
3. Trigger method Game.play(input) from a bean component. Here we play the game of life. 
   This iterates over the Game of Life board and creates the end result of the game.
4. Save Output.java to the db.

# Game of Life Implementation

This is a randomized version of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

The rest endpoint defines two parameters.
1. numIterations - the number of board changes / generation ticks. (MAX = 100)
2. liveCellsPercent - when we initialize the board this defines what is the percent of a cell to be alive. (MAX = 100)

The live and dead cells are scattered randomly across the board on each input. This means the outputs will not be consistent.
This is an expected behaviour.
The input defines the board and the number of iterations.
The output defines how many cells are alive after we iterate the board ${numIterations} times. Output is written in the db.

# Technical Details

-For validation camel-json-validator is used and the file schema.json. It is written according to http://json-schema.org  draft04

-Custom Component JsonValidationProcessor created in order to process validation errors so we can display them nicely to the user

-All properties are stored in application.properties file

-Rest endpoints are defined in /resources/camel-rest/paths.xml

-Camel routes are defined in /resources/camel/routs.xml

-Maven is used as a build tool.

-Spring boot is used as a fast way to setup the project. War file is generated from this setup using mvn package command.

-Spring, Apache Camel, AMQ, Hibernate, MySQL - All technologies used.

-Hibernate is used as a JPA implementation. Then camel-jpa component is used to store the Output.java in db.

-Unit tests are written to test the game of life.


# Authors Notes

-The implementation of the Game of Life is NOT mine (Classes: Cell.java and Board.java). I took the core classes from [here](https://github.com/inoryy/game-of-life-java/tree/master/src/main/java/gof/core)

-I added a method called countLiveCells(), which is needed for my current task to the core implementation.

-The setup can be easily switched to run in an embedded tomcat locally.

-To switch to local setup do: remove <scope>provided</scope> from tomcat dependency. Make <packaging>jar</packaging>. To start run the main method.

-Local db and queue can also be configured. The local setup is good for making integration tests.

-An integration test for each route would be nice. However as this is an interview assignement I skipped this part :) .

-Most of javadoc even in the gol core implementation are added by me.





