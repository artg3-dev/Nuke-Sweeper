/*
 * Copyright (C) 2020 Art Garcia (artg3.dev@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nukesweeper.Engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import nukesweeper.Engine.Exceptions.NukeFoundException;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class Game {

    public final static String BEGINNER = "B";
    public final static String INTERMEDIATE = "I";
    public final static String EXPERT = "E";

    private final Grid grid;
    private final int nukeCount;
    private boolean started;

    public Game(String difficulty) {
        this.grid = generateGrid(difficulty);
        this.nukeCount = generateNukeCount(difficulty);
        this.started = false;
    }

    // For custom setups
    public Game(int width, int height, int nukeCount) {
        grid = new Grid(width, height);
        this.nukeCount = nukeCount;
        started = false;
    }
    
    public boolean hasStarted() {
        return started;
    }

    public boolean start(Node startingNode) {
        if (!started) { // To prevent starting the board mid-game
            started = true;
            int addedNukes = 0;
            int gridWidth = grid.width;
            int gridHeight = grid.height;
            Random r = new Random();
            while (addedNukes < nukeCount) {
                int x = r.nextInt(gridWidth);
                int y = r.nextInt(gridHeight);
                Node n = grid.getNode(x, y);
                /* 
                * Makes node a nuke only if it isn't the start and isn't one
                * already.
                 */
                if (!n.equals(startingNode) && !n.isNuke()) {
                    n.makeNuke();
                    addedNukes++;
                }
            }
            return true;
        } else {
            return false; // If the game has already been started
        }
    }

    public void checkNode(Node node) throws NukeFoundException {
        List<Node> checked = new ArrayList();
        Queue<Node> toCheck = new LinkedList();
        toCheck.add(node);
        while (toCheck.size() > 0) {
            // Pull Node from toCheck queue
            Node next = toCheck.poll();

            // Check it if it isn't in checked list & add to checked
            if (!checked.contains(next)) {
                next.check();
                checked.add(next);
            }

            // If # of neighboring nukes == 0, add neighbors to toCheck queue
            if (!next.isNuke() && grid.getNeighborNukeCount(next) == 0) {
                List<Node> neighbors = grid.getNeighbors(next);
                for (Node i : neighbors) {
                    if (!checked.contains(i)) {
                        toCheck.add(i);
                    }
                }
            }
        }
    }

    public Grid getGrid() {
        return this.grid;
    }

    private Grid generateGrid(String difficulty) {
        if (difficulty.equals(EXPERT)) {
            return new Grid(16, 30);
        }
        if (difficulty.equals(INTERMEDIATE)) {
            return new Grid(16, 16);
        } else { // BEGINNER
            return new Grid(9, 9);
        }
    }

    private int generateNukeCount(String difficulty) {
        if (difficulty.equals(EXPERT)) {
            return 99;
        }
        if (difficulty.equals(INTERMEDIATE)) {
            return 40;
        } else { // BEGINNER
            return 10;
        }
    }

    // TESTING
    public void printGrid() {
        for (int i = -1; i < grid.width; i++) {
            if (i == -1) {
                System.out.print("X ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println("");
        for (int y = 0; y < grid.height; y++) {
            for (int x = -1; x < grid.width; x++) {
                try {
                    Node current = grid.getNode(x, y);
                    if (current.wasChecked()) {
                        System.out.print(grid.getNeighborNukeCount(current) + " ");
                    } else {
                        System.out.print("* ");
                    }
                } catch (Exception e) {
                    System.out.print(y + " ");
                }
            }
            System.out.println("");
        }
    }
}
