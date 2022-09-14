package com.muhammedtopgul.junit.levelA.exercise;

import com.muhammedtopgul.exercise.MinimumCostPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.muhammedtopgul.exercise.MinimumCostPath.Cell;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author muhammed-topgul
 * @since 12/09/2022 01:22
 */
@DisplayName("Level A (Beginner) Minimum Cost Path Tests")
public class MinimumCostPathTest {
    private final int[][] matrix = new int[2][3];
    private MinimumCostPath minimumCostPath;

    @BeforeEach
    void setUp() {
        minimumCostPath = new MinimumCostPath();
        matrix[0] = new int[]{4, 5, 6};
        matrix[1] = new int[]{7, 8, 1};
    }

    @Test
    @DisplayName("Throws IllegalArgumentException when the start or target cell is out of matrix")
    void throwsIllegalArgumentExceptionWhenTheCellIsOutOfMatrixBound() {
        assertAll("Start cell must be in matrix",
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> minimumCostPath.find(matrix, new Cell(2, 1), new Cell(0, 2))),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> minimumCostPath.find(matrix, new Cell(-1, 1), new Cell(0, 2)))
        );

        assertAll("Target cell must be in matrix",
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> minimumCostPath.find(matrix, new Cell(0, 0), new Cell(2, 1))),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> minimumCostPath.find(matrix, new Cell(0, 0), new Cell(-1, 2)))
        );
    }

    @Test
    @DisplayName("Return the cost of start cell equals to target cell")
    void returnTheCostStartCellWhenTheStartCellIsEqualToTargetCell() {
        assertEquals(4, minimumCostPath.find(matrix, new Cell(0, 0), new Cell(0, 0)));
        assertEquals(1, minimumCostPath.find(matrix, new Cell(1, 2), new Cell(1, 2)));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException when the start or target cell is null")
    void throwsExceptionWhenCellIsNull() {
        assertThrows(IllegalArgumentException.class, () -> minimumCostPath.find(matrix, null, new Cell(0, 0)));
    }
}
