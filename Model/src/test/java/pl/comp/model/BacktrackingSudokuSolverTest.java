package pl.comp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void equalsTest() {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver solver2 = new BacktrackingSudokuSolver();

        assertEquals(solver1, solver2);
        assertFalse(solver1.equals(null));
        assertFalse(solver1.equals(new SudokuField(3)));
    }
}