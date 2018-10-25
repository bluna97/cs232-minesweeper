# Introduction
This is a project that I worked on in my Computer Programming class in the fall of 2016. It is an implementation of the Minesweeper game in Java and was created using the Gradle build tool. The professor provided us students with instructions for the project. In addition, the professor also included a few lines of code as a starting point. To ensure that the program works as intended, the professor also included unit tests to use with Gradle. The program consists of 5 classes: Location.java, Grid.java, Ticker.java, Minesweeper.java, and Main.java.
# The Program
## Location
The most basic class in the program is the Location class. It contains an enum called Type consisting of three values: COVERED, UNCOVERED, and FLAGGED. It also contains a boolean called mine that represents whether the location contains a mine or not. In addition, the class contains an integer called hint that keeps track of the amount of mines in the Location's vicinity. The constructor for the class initializes the Type enum to COVERED. It also sets the mine boolean to false and the hint integer to 0. In addition, the Location class has methods for getting and setting the Type, mine, and hint values.
## Grid
The Grid class contains a 2D array of Location objects.
