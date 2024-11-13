package SierpinskiCarpet;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SierpinskiCarpet extends JPanel {
    private final char[][] board;
    private final int size;

    // Initialize the board with '*' characters
    public void fillCarpet(char[][] board) {
        for (char[] row : board) {
            Arrays.fill(row, '*');
        }
    }

    // Remove center part of the square
    public void removeCenter(char[][] board, int x, int y, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[x + i][y + j] = ' ';
            }
        }
    }

    // Recursive method to generate the Sierpinski carpet pattern
    public void sierpinskiCarpet(char[][] board, int x, int y, int n) {
        if (n == 3) {
            board[x + 1][y + 1] = ' ';
            return;
        }

        int num = n;
        n /= 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    removeCenter(board, x + n, y + n, n);
                } else {
                    sierpinskiCarpet(board, x + n * i, y + n * j, num / 3);
                }
            }
        }
    }

    // Override paintComponent to draw the carpet on a JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = Math.min(getWidth(), getHeight()) / size;

        int xOffset = (getWidth() - (cellSize * size)) / 2;
        int yOffset = (getHeight() - (cellSize * size)) / 2;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '*') {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);
            }
        }
    }

    public SierpinskiCarpet(int size) {
        this.size = size;
        board = new char[size][size];
        fillCarpet(board);
        sierpinskiCarpet(board, 0, 0, size);
    }

    public static void main(String[] args) {
        int size = 27;

        JFrame frame = new JFrame("Sierpinski Carpet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SierpinskiCarpet carpetPanel = new SierpinskiCarpet(size);
        frame.add(carpetPanel);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}