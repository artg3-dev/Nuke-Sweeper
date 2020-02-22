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
package nukesweeper.GUI.Timer;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class TimerLogic {
    private int seconds;
    private int minutes;
    public TimerLogic() {
        seconds = 0;
        minutes = 0;
    }
    
    void increase() {
        seconds ++;
        if (seconds >= 60) {
            seconds = 0;
            minutes ++;
        }
    }
    
    void restart() {
        seconds = 0;
        minutes = 0;
    }
    
    String getTime() {
        StringBuilder sb = new StringBuilder();
        if (minutes < 10) {
            sb.append(0);
            sb.append(minutes);
        } else {
            sb.append(minutes);
        }
        
        sb.append(":");
        
        if (seconds < 10) {
            sb.append(0);
            sb.append(seconds);
        } else {
            sb.append(seconds);
        }
        return sb.toString();
    }
}
