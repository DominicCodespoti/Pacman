# Pacman
My attempt at recreating Pacman in Java! Pacman finds himself in a grid filled with monsters. 
Will he be able to eat all the dots on the board before the monsters eat him?

## Task

The following is a list of features my game was required to implement:

- Pacman is on a grid filled with dots that he can eat
- Pacman has a direction
- Pacman moves on each tick
- The user can rotate Pacmans direction
- Pacman can eat the dots
- Pacman can wrap around the sides of the map (for example, if he reaches the top, he'll wrap to the bottom)
- Pacman stops movement on hitting a wall
- Pacman will not rotate into a wall
- Game score including levels completed and the number of dots eaten in this level
- Monsters that will actively pursuit Pacman
- Multiple levels
- Pacmans mouth opens and closes

## Installation and Running

Due to the terminal implementation of Pacman requiring the terminal to enter into "Raw Mode" too allow for non-blocking input,
the game cannot be run in IntelliJ or similar IDEs, but instead compiled and run. 

Step 1: CD into the Java directory (PacmanComposition/src/main/java)

Step 2: Run the command "javac Main.java" to compile the game

Step 3: Run the command "java Main" too launch and play the game!

Notes: The controls for the terminal implementation are mapped by default too: 
W to move up, A to move left, S to move down, D to move right.
