/**
 * Game of Life implementation.
 * The Board and Cell classes implementation is taken from: https://github.com/inoryy/game-of-life-java/tree/master/src/main/java/gof/core
 * I added only the method countLiveCells in Board.java.
 * <p>
 * This is a randnomized verion of Conway's Game of Life.
 * <p>
 * The rest endpoint defines two parameters.
 * <p>
 * numIterations - the number of board changes / generation ticks. (MAX = 100)
 * liveCellsPercent - when we initialize the board this defines what is the percent of a cell to be alive. (MAX = 100)
 * <p>
 * The live and dead cells are scattered randomly across the board on each input. This means the outputs will not be consistent. This is an expected behaviour. The input defines the board and the number of iterations. The output defines how many cells are alive after we iterate the board ${numIterations} times. Output is written in the db.
 */
package demo.gol;
