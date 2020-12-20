Original http://lambda.ee/wiki/Gomoku-eng

Gomoku-eng

The goal is to implement the artificial intelligence (AI) component for the Gomoku game. You have to implement specific parts of the program, that compute the best possible move given a game status on board. The AI has to be able to perform at "resonable" level.

Specific demands follow. Your AI must win the AI provided by the teacher in majority of cases (e.g. 6 out of 10) provided both have computation time limited to 1 second.

It is guaranteed to be solvable, if:

    your program implements minimax algorithm correctly
    your game status evaluation function is reasonably simple (checks for sets of fices, open fours, half-open fours, open threes) 

NB! The program should perform reasonably on both 10x10 and 20x20 boards.
Sisukord [peida] 

    1 Programming in pairs
    2 Program framework
    3 "auto" botton and benchmark AI
    4 Tournament and extra points
    5 Tournament results
    6 How to implement minimax and the evalutaion function
    7 Appendixes

Programming in pairs

You can program this alone or in pair with other student. Paired assignment will award both studends maximum points if:

    Both students are presenting the program. Missing student gets no points.
    Both students understand the presented program code - including the parts implemented by other. The student that seems inadequate gets less or no points. 

Program framework

Your program has to be the refinement of the following code. Other alternatives - total makeovers or partially re-used GUI elements or classes are not accepted.

The framework consist of four files provided in the follwing ZIP file:

Five.zip


The code compiles and works. But it performs poorly. The AI takes the first legal move found. You would have to re-implement the function best_move() (in file FiveLogic.java) to include minimax and a reasonable evaluation function. You can add methods and call them withing best_move(). No other parts of the code needs to be touched - including the GUI.

The provided code is based on the source code available here: http://www.leepoint.net/notes-java/examples/games/five/five.html

It is complemented with "program turn", auto-play, 20x20 board choice and a default board of 10x10 instead of 9x9.
"auto" botton and benchmark AI

Pushing the "auto" button the program will play fully automatically - competition between best_move() (in file FiveLogic.java) and best_opponent_move (in file FiveOpponent.java). The human role is thereby the best_opponent_move().

The best_opponent_move() has poor performance also - it will also play on the first legal move available starting from botton-right.

The following ZIP file gives you the pre-compiled class file FiveOpponent.class that you would have to replace your compiled FiveOpponent.class with. You delete the FiveOpponent.class and FiveOpponent.java and replace them with FiveOpponent.class found on this page.

FiveOpponent.zip: unpack the ZIP containing the FiveOpponent.class

The give FiveOpponent will compute two turns ahead (white - his - turn, then black - the opponent, your program). Evaluation function enumerates sets of five, open fours, threes and twos. Plus half-open fours and threes. Then assigns coefficients to these sets. It doesn't include alpha-beta and any optimizations. Therefore it is rather slow.

Your program would have to win this FiveOpponent.class in majority of cases. (6 out of 10 games)

If you fail to beat the benchmark opponent you can choos to use even weaker opponent:

FiveOpponent_weak.zip: unpack the zip to get the FiveOpponent.class

FiveOpponent_weak.zip will precompute one turn ahead (white's turn). If you can beat the weak opponent in majority of cases you will be awarded max. 8 points out of 10.

NB! Check that your program doesn't compute a turn on 10x10 board longer that 1 second. Else it will be considered as invalid algorithm.
Tournament and extra points

NB! All authors will get 0 points upon detected plagiarism

There will be a tournament held between the AI-s submitted by students. The first two places get extra points:

    Winning team gets 10 extra points each.
    Second team gets 5 extra points each. 

Võistlus ei ole kohustuslik. Kuidas sellest osa võtta: kolme päeva jooksul peale praktikumitähtaja möödumist tuleb teha oma programmist versioon, kus varem best_move funktsioonis sisaldunud kood kopeeritakse funktsioon best_opponent_move failis FiveOpponent.java. Viimane fail peab olema täiesti sõltumatu teistest programmi lähtekoodi-failidest (st peab ise kompileeruma).

Tournament is not compulsory. How to participate: three days after the assigment deadline you have to assemble a version of the program where best_move() function is copied into best_opponent_move() function in FiveOpponent.java. It has to be completely independant of other parts of the program code - self compiling.

Given the three days you submit the code by email to the teacher. Write GOMOKU as the subject.

Provide:

    Author names, student ID-s and e-mails.
    Source code (NB! FiveOpponent.java needs to include yout best move computation)
    precompiled class files 

Principal demands:

    The AI has to written by you. You can research articles and study other programs.
    The teacher can ask additional questions if in suspicion and can disqualify the submission. 

Technical demands:

    The progam can't compute a turn longer than 1 second.
    Should be able to play on 10x10 and 20x20 boards.
    Program should be able to make a turn if the board is empty (black starts).
    Program will be given a board and should be able to decide, whos turn it is. Then return an encoded integer representing the move for next players turn. 

The teacher will make a tournament between the submitted programs in playoff format. Losing player will fall out. The programs will play for white and black players in rotation until one program leads by 2 points (loss and tie is 0 points, 1 point for win). 10 games will be played - if winner cannot be determined, it will be chosen randomly.
Tournament results

TBD
How to implement minimax and the evalutaion function

Begin by implementing a simple minimax (without alpha-beta) and an evaluation function that determines if either player has won. You can re-use getGameStatus() function from FiveLogic.java

Debug the program by playing with it yourself. Experiment with search depths two, three, four, etc. Until search time gets too long. Explore if the program can detect one-turn win, two, etc. Can it block them.

Start improving the evaluation function with the main ideas:

    Detect sets of five (win)?
    Fully open fours for both
    Half open fours for both
    Fully open threes for both
    Maybe halfopen twos
    Maybe open twos 

Seisuhinne arvuta kokku nendest parameetritest, pannes võimsamatele ähvardustele kõvema koefitsiendi (üleni lahtine neljane on praktiliselt juba võit, lahtine kolmene aga veel mitte). Mõistlik võib olla viimati käija värvi ja järgmisena käjia värvi ähvardustele erinevate koefitsientide panemine: järgmisena käija ähvardused on hullemad (kui ta juba kaotanud pole)!

Compute the evaluation value given these parameters giving a larger coefficient for a greater threat (Open set of four is practically a win, open three is soon to be). It might be reasobable to give different coefficients for both players. The player with the next turn will be assigned a greater threat.

Try do debug by plaing against it.

Experiment with computation time and figure out an optimal search depth so it will never compute for more than 1 second. (NB! 20x20 board will probably have smaller search depth than 10x10)

Yout program should be OK now, to win the benchmark opponent.

Other ideas to make it better:

    Implement alpha-beta (modify minimax). The program should find the same move faster.
    Don't loof for alternative moves if a move is forced (closing a set of four, open three, etc.)
    Compute turns near moves already made.
    Upon forced moves, where you have a single choice - search deeper. A unique forced move doesn't explode the search space. Try to search to the end of the search tree.
    Study appendixes 

Appendixes

Lecture info and references: Progpohi12.PPT

In addition:

    http://en.wikipedia.org/wiki/Gomoku 
    http://www.leepoint.net/notes-java/examples/games/five/five.html 
    http://www.ocf.berkeley.edu/~yosenl/extras/alphabeta/alphabeta.html 
    http://web.archive.org/web/20040607185428/www.cs.vu.nl/~victor/thesis.html 
    http://www.cs.ualberta.ca/~chinook/ 
	
Tags: Five in a Row, Tic Tac Toe, TicTacToe, 5 in a Row, Go-Moku, Connect, Connect5, Connect6, Caro, Noughts and Crosses, Gomoku, Renju, Pente, Piskvork, Amoba, Kółko i Krzyżyk, Gomocup, AI, Engine, Artificial Intelligence, Brain, Pbrain, Gra, Game, Source Code Files, Program, Programming, Github, Board, Coding.