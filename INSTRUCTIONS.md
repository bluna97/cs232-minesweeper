# Minesweeper

## Introduction

In the game Minesweeper, the goal is to find where all the mines are 
located within an M x N field. The game shows a number in a square, 
which tells you how many mines are adjacent to that square. Each 
square has at most eight adjacent squares. The 4 x 4 field on the left 
contains two mines, each represented by a "#" character. If we 
represent the same field by the hint numbers described above, we end 
up with the field on the right:

    # . . .         # 1 0 0
    . . . .         2 2 1 0
    . # . .         1 # 1 0
    . . . .         1 1 1 0

## Assignment

In this assignment, you are to implement a playable version of 
Minesweeper in Java. The application must be customizable (using 
command-line arguments) so that various sizes (width and height) of 
the board can be played, along with various numbers of mines. The 
default game should be an 8 x 8 board with 10 mines. The interface
should have a button for starting a new game, a label that keeps up 
with the number of flags that are left for marking mines, and a label 
that ticks up as the seconds pass once a game begins. (A game should 
be considered to have begun once the player clicks his or her first 
square.)

The grid of locations should be left-clickable to reveal what is 
underneath (number or mine) and right-clickable to place a flag 
without revealing what is underneath. Left-clicking or right-clicking 
an already revealed location does nothing. Left-clicking a flagged 
does nothing. Right-clicking a flagged location just resets that flag 
into the player's pool. Once the player has revealed the entire board 
without triggering a mine, a popup window should state that he or she 
is the winner and report the elapsed time. If a mine is triggered, a 
popup window should be displayed that says that the game is over. 