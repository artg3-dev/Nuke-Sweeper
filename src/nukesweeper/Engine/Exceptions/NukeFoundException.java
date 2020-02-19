/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.Engine.Exceptions;

import nukesweeper.Engine.Node;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NukeFoundException extends Exception{
    private final Node node;
    public NukeFoundException(Node node) {
        super("Checked node was a nuke!");
        this.node = node;
    }
    
    public Node getNode() {
        return this.node;
    }
}
