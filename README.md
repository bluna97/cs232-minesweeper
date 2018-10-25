# Introduction
This is a project that I worked on in my Computer Programming class in the fall of 2016. It is an implementation of the Minesweeper game in Java and was created using the Gradle build tool. The professor provided us students with instructions for the project. In addition, the professor also included a few lines of code as a starting point. To ensure that the program works as intended, the professor also included unit tests to use with Gradle. The program consists of 5 classes: Location.java, Grid.java, Ticker.java, Minesweeper.java, and Main.java.
# The Program
## Location
The most basic class in the program is the Location class. It contains an enum called Type consisting of three values: COVERED, UNCOVERED, and FLAGGED. It also contains a boolean called mine that represents whether the location contains a mine or not. In addition, the class contains an integer called hint that keeps track of the amount of mines in the Location's vicinity. The constructor for the class initializes the Type enum to COVERED. It also sets the mine boolean to false and the hint integer to 0. In addition, the Location class has methods for getting and setting the Type, mine, and hint values.
## Grid
The Grid class contains a 2D array of Location objects called location. It also extends the Observable component of the Observer pattern. This allows other classes to observe the changes made to the Grid object. The class also contains an enum called Result with the values NONE, WIN, and LOSE. It also has an integer called mines that represents how many mines are on the grid. Lastly, it has a Random object called random.

The constructor receives three integers as arguments that represent the width, height, and the number of mines in the grid. It also receives a Random object as an argument. It instantiates the 2D location array with the height and width integers. It then iterates through the array and creates a new Location object in each spot. Then it calls the placeMines and placeHints methods.

The placeMines method uses a while loop nested within a for loop. The for loop iterates until the required number of mines are placed. The while loop iterates until a mine is successfully placed. By using the Random object, the method selects a square in the location array randomly. If the selected square already has a mine, then the while loop repeats.

An important method used by the placeHints method is getNeighbors. It receives two integers that represent the row and column of the Location whose neighbors will be collected. It returns a List of Location objects. The method iterates through 9 squares in the Grid, including the Location at the index argument. The squares checked include the three above the Location's row, the three on the Location's row, and the three below the Location's row. This covers all eight possible Locations in the vicinity of the given index. During the loop, the isLegalIndex method is called to check if the current index is within the bounds of the Grid. If the specified index is legal, and it is not the given index argument, then it is a neighbor. The Location at that index is added to the neighbors list.

Another method used by placeHints is called calculateHint. It receives a List of Location objects as an argument, and it returns an integer. It simply iterates through the List and counts the number of Locations that contain a mine. Then it returns that number.

The placeHints method iterates through the entire Grid. At each Location, it calls the getNeighbors method. Then it uses the returned List to call the calculateHint method. Finally, it sets the Location's hint value to the integer returned by the calculateHint method.


