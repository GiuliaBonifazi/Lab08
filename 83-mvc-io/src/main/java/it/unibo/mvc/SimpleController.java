package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String next;
    private List<String> history = new ArrayList<String>();

    @Override
    public void setNextToPrint(String next) throws IllegalArgumentException {
        if (next == null) {
            throw new IllegalArgumentException("No string selected");
        }
        else {
            this.next = next;
        }
    }

    @Override
    public String getNexToPrint() {
        return this.next; 
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void printString() throws IllegalStateException {
        if (this.next == null) {
            throw new IllegalStateException("No string selected");
        }
        else {
            System.out.println(this.next);
            history.add(this.next);   
        }
    }
}
