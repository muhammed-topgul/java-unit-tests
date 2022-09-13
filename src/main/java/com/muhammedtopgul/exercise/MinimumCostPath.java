package com.muhammedtopgul.exercise;

import java.util.Objects;

/**
 * @author muhammed-topgul
 * @since 12/09/2022 01:21
 */
public class MinimumCostPath {
    public int find(int[][] matrix, Cell start, Cell target) {
        controlBounds(matrix, start);
        controlBounds(matrix, target);

        if (start.equals(target)) {
            return matrix[start.row][start.column];
        }
        return 0;
    }

    private void controlBounds(int[][] matrix, Cell cell) {
        if (cell == null || (cell.row >= matrix.length || cell.row < 0)) {
            throw new IllegalArgumentException();
        }
    }

    public static class Cell {
        private final int row;
        private final int column;

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row && column == cell.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
