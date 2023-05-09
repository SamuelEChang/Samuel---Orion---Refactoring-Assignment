import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;

// define a class called PlagiarismResultsFile that extends the FilePlagiarismChecker class
public class PlagiarismResultsFile extends FilePlagiarismChecker{

    // define a constructor that takes 'filePath' and 'libraryPath' as arguments
    public PlagiarismResultsFile(String filePath, String libraryPath) throws IOException {
        // call the constructor of the superclass with 'filePath' and 'libraryPath' as arguments
        super(filePath, libraryPath);
    }

    public void printResults(String overwrite) throws IOException{
        // create a new File object with file name "results.csv"
        File file = new File("results.csv");
        // initialize a BufferedWriter object as null
        BufferedWriter writer = null;
        // check if the value of 'overwrite' is "yes" ignoring the case
        if (overwrite.equalsIgnoreCase("yes")) {
            // create a new BufferedWriter object and open a new FileWriter with the specified file name
            writer = new BufferedWriter(new FileWriter(file, false)); // pass 'false' as the second argument to FileWriter to indicate that the file should be overwritten
            // write a header row in the CSV file
            writer.write("File Name, Line Number, Copied, Similarity\n");
        }
        // check if the value of 'overwrite' is "no" ignoring the case
        else if (overwrite.equalsIgnoreCase("no")){

            // set the BufferedWriter object to null
            writer = null;
        }
        // if the value of 'overwrite' is neither "yes" nor "no"
        else {
            // create a new BufferedWriter object and open a new FileWriter with the specified file name
            writer = new BufferedWriter(new FileWriter(file));
            // write a header row in the CSV file
            writer.write("File Name, Line Number, Copied, Similarity\n");
        }
        // read the content of the file specified in 'filePath' and store it in a String variable
        String fileContent = readFile(filePath);
        // iterate through the 'libraryFiles' ArrayList
        for (String fileName : libraryFiles) {
            // set the 'libraryFilePath' variable to the current file name in the loop
            String libraryFilePath = fileName;
            // read the content of the current file in the loop and store it in a String variable
            String libraryFileContent = readFile(libraryFilePath);
            // get an ArrayList of plagiarized lines between the two files
            ArrayList<String> plagiarizedLines = getPlagiarizedLines(fileContent, libraryFileContent);
            // check if there are plagiarized lines and if the BufferedWriter object is not null
            if (plagiarizedLines.size() > 0 && writer != null) {
                
                for (String lineNumber : plagiarizedLines) {
                    writer.write(filePath + ", " + "Line " + lineNumber + ", " + libraryFilePath + ", " + fileContent.split("\\r?\\n")[Integer.parseInt(lineNumber) - 1] + "\n");
                }
            }
        }
        if (writer != null){
            writer.close();
        }
    }
}
