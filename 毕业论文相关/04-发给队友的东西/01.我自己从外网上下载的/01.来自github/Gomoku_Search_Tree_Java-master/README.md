# Gomoku_Search_Tree_Java
Implementation of Monte Carlo Search Tree to Play Gomoku in Java.

All Java classes involved with my Gomoku game:
NewBoardGUI.java - User Interface which shows the board and player tokens. Any changes that take place on this board will be reflected onto the virtual board as well.

BoardGame.java - Creates the virtual board and rules for the game. Mimics the User Interface's board if any changes occur.

MCTSNode:

* Class which represents each node of the search tree. Contains a game state, as well as the total number of simulations and wins that occur during the simulation phase. 

* Also contains methods involved in the MCTS such as Selection, Expansion, Simulation and Back-propagation.

MCTSNode - When given parameter 'x', will run the MCTS algorithm 'x' amount of times until the best solution is found.
