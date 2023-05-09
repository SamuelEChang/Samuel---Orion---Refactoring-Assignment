import java.io.IOException;
import java.util.ArrayList;

public class FilePlagiarismChecker {
    // declare a public instance variable 'filePath' of type String
    public String filePath;
    // declare a public instance variable 'libraryFiles' of type ArrayList<String>
    public ArrayList<String> libraryFiles;

    // define a constructor that takes 'filePath' and 'libraryPath' arguments
    public FilePlagiarismChecker(String filePath, String libraryPath) throws IOException {
        // set the instance variable 'filePath' to the value of 'filePath' passed in the constructor
        this.filePath = filePath;
        // create a new instance of the FileReadAndWrite class with 'libraryPath' passed as argument
        FileReadAndWrite fileReadAndWrite = new FileReadAndWrite(libraryPath);
        // set the instance variable 'libraryFiles' to the result of invoking the 'findFiles' method on the FileReadAndWrite instance created above
        this.libraryFiles = fileReadAndWrite.findFiles(libraryPath, "");
    }

    public void checkPlagiarism() throws IOException {
        // declare a local variable 'lineCount' and set it to the result of invoking the 'countLines' method with 'filePath' passed as argument
        int lineCount = countLines(filePath);
        System.out.println("The file " + filePath + " has " + lineCount + " lines.");

        // declare a local variable 'fileContent' and set it to the result of invoking the 'readFile' method with 'filePath' passed as argument
        String fileContent = readFile(filePath);

        // iterate over each file name in the 'libraryFiles' ArrayList
        for (String fileName : libraryFiles) {
            // declare a local variable 'libraryFilePath' and set it to the value of the current file name in the loop
            String libraryFilePath = fileName;
            // declare a local variable 'libraryFileContent' and set it to the result of invoking the 'readFile' method with 'libraryFilePath' passed as argument
            String libraryFileContent = readFile(libraryFilePath);
            // declare a local variable 'plagiarizedLines' and set it to the result of invoking the 'getPlagiarizedLines' method with 'fileContent' and 'libraryFileContent' passed as arguments
            ArrayList<String> plagiarizedLines = getPlagiarizedLines(fileContent, libraryFileContent);
            if (plagiarizedLines.size() > 0) {
                // print a message indicating the file and the plagiarized lines
                System.out.println("The following lines in " + filePath + " are plagiarized from " + libraryFilePath + ":");
                // iterate over each plagiarized line number in the 'plagiarizedLines' ArrayList
                for (String lineNumber : plagiarizedLines) {
                    System.out.println("Line " + lineNumber + ": " + fileContent.split("\\r?\\n")[Integer.parseInt(lineNumber) - 1]);
                }
            }
        }
    }

    protected int countLines(String filePath) throws IOException {
        FileReadAndWrite fileReadAndWrite = new FileReadAndWrite(filePath);
        return fileReadAndWrite.countLines();
    }
    // define a protected method 'readFile' that takes 'filePath' as argument
    protected String readFile(String filePath) throws IOException {
        // create a new instance of the FileReadAndWrite class with 'filePath' as the argument, which is used to read and write files
        FileReadAndWrite fileReadAndWrite = new FileReadAndWrite(filePath);
        return fileReadAndWrite.readFile();
    }

     // define a protected method 'getPlagiarizedLines' that takes 'fileContent' and 'libraryFileContent' as arguments
    protected ArrayList<String> getPlagiarizedLines(String fileContent, String libraryFileContent) {
        // create a new ArrayList of type String to store the line numbers of the plagiarized lines
        ArrayList<String> plagiarizedLines = new ArrayList<>();
        // split the 'fileContent' into an array of strings using the regular expression "\\r?\\n" as the delimiter, which matches new line characters on different platforms
        String[] fileLines = fileContent.split("\\r?\\n");
        // split the 'libraryFileContent' into an array of strings using the regular expression "\\r?\\n" as the delimiter, which matches new line characters on different platforms
        String[] libraryFileLines = libraryFileContent.split("\\r?\\n");
        // iterate over the 'fileLines' array using a for loop
        for (int i = 0; i < fileLines.length; i++) {
            // iterate over the 'libraryFileLines' array using a nested for loop
            for (int j = 0; j < libraryFileLines.length; j++) {
                // compare the current line of the 'fileLines' array with the current line of the 'libraryFileLines' array
                if (fileLines[i].equals(libraryFileLines[j])) {
                    // if the lines match, add the line number (i+1) to the 'plagiarizedLines' ArrayList as a string using the 'valueOf' method of the String class
                    plagiarizedLines.add(String.valueOf(i + 1));
                }
            }
        }
        return plagiarizedLines;
    }
}
