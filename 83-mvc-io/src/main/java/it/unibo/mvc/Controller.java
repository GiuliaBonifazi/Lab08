package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    void setNextToPrint(String next) throws IllegalArgumentException;

    String getNexToPrint();

    List<String> getHistory();

    void printString() throws IllegalStateException;
}
