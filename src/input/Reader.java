package input;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A utility class for reading data from files in a folder.
 */
public class Reader {
    private boolean stop;
    private int currentFileIndex;
    private BufferedReader currentReader;
    private String currentLine;
    private File folder;
    private File[] files;

    /**
     * Constructs a Reader object with the specified folder path.
     *
     * @param folderPath the path of the folder containing the files to read
     */
    public Reader(String folderPath) {
        stop = false;
        folder = new File(folderPath);
        files = folder.listFiles();
        currentFileIndex = 0;
        currentLine = null;
        currentReader = null;
    }

    /**
     * Retrieves the next line of data from the files in the folder.
     * If all files have been read, returns null.
     *
     * @return the next line of data, or null if all files have been read
     */
    public String getData() {
        if (currentLine == null && currentFileIndex == files.length) {
            stop = true;
            return null;
        }
        if (currentReader == null) {
            openNextFile();
        }
        try {
            currentLine = currentReader.readLine();
            if (currentLine == null) {
                closeCurrentFile();
                return getData();
            }
            return currentLine;
        } catch (IOException e) {
            e.printStackTrace();
            closeCurrentFile();
            return getData();
        }
    }

    private void openNextFile() {
        File currentFile = files[currentFileIndex];
        try {
            currentReader = new BufferedReader(new FileReader(currentFile));
        } catch (IOException e) {
            e.printStackTrace();
            closeCurrentFile();
            currentFileIndex++;
            openNextFile();
        }
        currentFileIndex++;
    }

    private void closeCurrentFile() {
        try {
            if (currentReader != null) {
                currentReader.close();
                currentReader = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if all files have been read and the Reader should stop.
     *
     * @return true if all files have been read, false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
