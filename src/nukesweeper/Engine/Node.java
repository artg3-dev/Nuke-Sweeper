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

import nukesweeper.Engine.Exceptions.NukeFoundException;

/**
 * For individual points on a grid
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class Node {

    private final int x, y;
    private boolean isNuke, checked;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.isNuke = false;
        this.checked = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void makeNuke() {
        this.isNuke = true;
    }

    public boolean isNuke() {
        return isNuke;
    }

    public void check() throws NukeFoundException {
        if (isNuke) {
            throw new NukeFoundException(this);
        } else {
            this.checked = true;
        }
    }

    public boolean wasChecked() {
        return this.checked;
    }

    @Override
    public boolean equals(Object inQuestion) {
        if (inQuestion == null) {
            return false;
        }

        if (getClass() != inQuestion.getClass()) {
            return false;
        }

        Node compared = (Node) inQuestion;

        return this.x == compared.x && this.y == compared.y;
    }

    @Override
    public int hashCode() {
        return (super.hashCode() + x) * y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node (");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(", ");
        sb.append(isNuke);
        sb.append(")");
        return sb.toString();
    }
}
