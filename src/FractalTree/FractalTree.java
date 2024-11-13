package FractalTree;

/*
    Fractal Tree

    - Type of fractal, which is a complex structure built by repeating a pattern at different scales
    - Each branch splits into two smaller branches, which split into smaller branches and so on



    # How Fractal Trees Work

    1. Start with a trunk - single, vertical line

    2. Split the trunk into smaller branches - at the top of the trunk, split into two branches, which diverge
       at an angle from the main trunk

    3. Recursively split each branch - each of these branches splits into two smaller branches, and the process repeats



    # Mathematical Concepts in Fractal Trees

    1. Angles - When a branch splits, each new branch is at an angle to the previous branch. A common configuration
       is to have the two new branches split symmetrically at equal angles, say 30 degrees left and right

    2. Scaling Factor - Each time branches split, they get shorter by a scaling factor (usually between 0.5 and 0.8).
       This makes each "generation" of branches progressively smaller, giving the tree a realistic, tapered look

    3. Recursion - Fractal trees are built using recursion, where each branch's growth pattern is identical to that of
       the whole tree, just scaled down


    # Example of Math for Each Branch

    Let's assume
        - The trunk starts at point (x, y) with an angle 0 and length L
        - Each branch is shorter by a factor of s (e.g., s = 0.7)

    For a given branch
        1. Calculate End Points of the Branch (using basic trigonometry)
            - The new branch's end coordinates (x1, y1) are:
                - x1 = x + L * cos(0)
                - y1 = y - L * sin(0)

        2. Determine New Angles and Lengths for Branches
            - New length for each branch: L * s
            - New angles 0 - a for one branch and 0 + a for the other, where a is the branching angle (e.g., 30 degrees)

 */

import javax.swing.*;
import java.awt.*;

public class FractalTree extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        // Initial call for the tree trunk
        int startX = getWidth() / 2;
        int startY = getHeight() - 100;
        int trunkLength = 150;
        drawBranch(g2d, startX, startY, trunkLength, -Math.PI / 2); // -90 degrees (upward)
    }

    private void drawBranch(Graphics2D g2d, int x, int y, double length, double angle) {
        if (length < 5) return; // Stop condition

        // Calculate end point of the current branch
        int xEnd = x + (int) (length * Math.cos(angle));
        int yEnd = y + (int) (length * Math.sin(angle));

        // Draw the branch
        g2d.drawLine(x, y, xEnd, yEnd);

        // Recursive calls for the next two branches
        double newLength = length * 0.7; // Scale factor for branch length
        double angleDelta = Math.PI / 6; // Branch angle, 30 degrees

        drawBranch(g2d, xEnd, yEnd, newLength, angle - angleDelta); // Left branch
        drawBranch(g2d, xEnd, yEnd, newLength, angle + angleDelta); // Right branch
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.add(new FractalTree());
        frame.setVisible(true);
    }
}