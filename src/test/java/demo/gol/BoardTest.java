package demo.gol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BoardTest {
    @Test
    public void testGetNeighbours() {
        //Board b = new Board(3, 3, 0.0);

        Cell[][] cells = {
                {new Cell(true), new Cell(true), new Cell(true)},
                {new Cell(true), new Cell(true), new Cell(true)},
                {new Cell(true), new Cell(true), new Cell(true)}
        };
        Board b = new Board(cells);

        assertEquals(3, b.neighboursCountAt(0, 0));
        assertEquals(8, b.neighboursCountAt(1, 1));
        assertEquals(5, b.neighboursCountAt(1, 0));
    }

    @Test
    public void testAllCellsDie() {
        Cell[][] cells = {
                {new Cell(true), new Cell(), new Cell(true)},
                {new Cell(), new Cell(), new Cell()},
                {new Cell(true), new Cell(), new Cell(true)}
        };

        Board b = new Board(cells);

        assertEquals(true, b.isAlive(0, 0));
        assertEquals(false, b.isAlive(1, 1));

        b.update();

        assertEquals(false, b.isAlive(0, 0));
        assertEquals(false, b.isAlive(2, 2));
        assertEquals(false, b.isAlive(2, 0));
        assertEquals(false, b.isAlive(0, 2));
        assertEquals(false, b.isAlive(1, 1));
    }

    @Test
    public void testCountLiveCells() {
        Cell[][] cells = {
                {new Cell(true), new Cell(), new Cell(true)},
                {new Cell(), new Cell(), new Cell()},
                {new Cell(true), new Cell(), new Cell(true)}
        };
        Board b = new Board(cells);
        assertEquals(Integer.valueOf(4), b.countLiveCells());

        // Here all cells die. So count should be 0.
        b.update();
        assertEquals(Integer.valueOf(0), b.countLiveCells());
    }

    @Test
    public void testGridStaysTheSame() {
        Cell[][] cells = {
                {new Cell(true), new Cell(true), new Cell()},
                {new Cell(true), new Cell(true), new Cell()},
                {new Cell(), new Cell(), new Cell()}
        };

        Board b = new Board(cells);

        b.update();

        assertSame(cells, b.getGrid());
    }

    @Test
    public void testOverpopulationAndIsBorn() {
        Cell[][] cells = {
                {new Cell(true), new Cell(true), new Cell(true)},
                {new Cell(true), new Cell(true), new Cell()},
                {new Cell(), new Cell(), new Cell()}
        };

        Board b = new Board(cells);

        b.update();

        assertEquals(true, b.isAlive(0, 0));
        assertEquals(true, b.isAlive(0, 2));
        assertEquals(true, b.isAlive(1, 0));
        assertEquals(true, b.isAlive(1, 2));

        assertEquals(false, b.isAlive(0, 1));
        assertEquals(false, b.isAlive(1, 1));
    }
}
