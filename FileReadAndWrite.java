import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReadAndWrite {
    // Declaring a private variable named fileName
    private String fileName;

    // Creating a public constructor that takes fileName as a parameter
    public FileReadAndWrite(String fileName) {
        // Assigning the value of fileName to the instance variable this.fileName
        this.fileName = fileName;
    }
    // Defining a public method named findFiles that takes two string parameters
    public ArrayList<String> findFiles(String dir, String extension) {
        // Creating an ArrayList to hold file names
        ArrayList<String> fileNames = new ArrayList<>();
        // Creating a new File object named directory with the directory path as its argument
        File directory = new File(dir);
        // Checking if the File object is a directory
        if (directory.isDirectory()) {
            // Getting an array of files in the directory
            File[] files = directory.listFiles();
            for (File file : files) {
                // Checking if the file is a file and has the desired extension
                if (file.isFile() && file.getName().endsWith(extension)) {
                    // Adding the file path to the ArrayList
                    fileNames.add(file.getPath());
                }
            }
        }
        return fileNames;
    }

    public int countLines() throws IOException {
        // Initializing a variable to hold the line count
        int lineCount = 0;
        // Creating a BufferedReader object to read the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Looping through each line of the file until there are no more lines
            while (br.readLine() != null) {
                lineCount++;
            }
        }
        return lineCount;
    }
    // Defining a public method named readFile
    public String readFile() throws IOException {
        // Creating a StringBuilder object to hold the file contents
        StringBuilder sb = new StringBuilder();
        // Creating a BufferedReader object to read the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Reading the first line of the file
            String line = br.readLine();
            // Looping through each line of the file until there are no more lines
            while (line != null) {
                // Appending the line to the StringBuilder object, along with a line separator
                sb.append(line).append(System.lineSeparator());
                line = br.readLine();
            }
        }
        return sb.toString();
    }

}