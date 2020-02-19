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

/**
 * Grid is a 2 Dimensional array of nodes.
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class Grid {
    private final Node[][] grid;
    final int width;
    final int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Node[width][height];
        fillGrid();
    }
    
    Node getNode(int x, int y) {
        return grid[x][y];
    }
    
    ArrayList<Node> getNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList();
        int x = node.getX();
        int y = node.getY();
        for (int i = -1; i <= 1; i ++) {
            for (int j = -1; j <= 1; j ++) {
                try {
                    Node neighbor = grid[x + i][y + j];
                    neighbors.add(neighbor);
                } catch (Exception e) {
                }
            }
        }
        
        return neighbors;
    }
    
    int getNeighborNukeCount(int x, int y) {
        Node target = grid[x][y];
        int count = 0;
        for (int i = -1; i <= 1; i ++) {
            for (int j = -1; j <= 1; j ++) {
                try {
                    Node neighbor = grid[x + i][y + j];
                    if(neighbor.isNuke() && !neighbor.equals(target)) {
                        count++;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        
        return count;
    }
    
    int getNeighborNukeCount(Node node) {
        return getNeighborNukeCount(node.getX(), node.getY());
    }
    
    private void fillGrid() {
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[i].length; j ++) {
                grid[i][j] = new Node(i, j);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[i].length; j ++) {
                sb.append(grid[i][j]);
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
}
