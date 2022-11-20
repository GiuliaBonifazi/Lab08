package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String SEP = System.getProperty("file.separator");
    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT_FILENAME = HOME + SEP + "output.txt";

    private File currentFile = new File(DEFAULT_FILENAME);

    public void setFileAsCurrent(String filePath) {
        currentFile = new File(filePath);
    }

    public String getPath() {
        return currentFile.getAbsolutePath();
    }

    public File getFile() {
        return currentFile;
    }

    public void saveOnFile (String message) throws IOException{
        try (PrintStream out = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            out.println(message);;
        } catch (IOException e) {
            System.out.println(e.getMessage()); // NOPMD: allowed as this is just an exercise
        }
    }
}
