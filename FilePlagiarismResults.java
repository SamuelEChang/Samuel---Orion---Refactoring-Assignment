import java.io.IOException;
import java.util.ArrayList;


public class FilePlagiarismResults extends FilePlagiarismChecker{
    // define a constructor that takes 'filePath' and 'libraryPath' as arguments
    public FilePlagiarismResults(String filePath, String libraryPath) throws IOException {
        // call the constructor of the parent class using the 'super' keyword
        super(filePath, libraryPath);
    }

    public void getResults() throws IOException{
        // count the number of lines in the file
        int lineCount = countLines(filePath);
        System.out.println("The file " + filePath + " has " + lineCount + " lines.");
        
        // read the content of the file
        String fileContent = readFile(filePath);

        // iterate through the library files and compare them with the given file
        for (String fileName : libraryFiles) {
            String libraryFilePath = fileName;
            String libraryFileContent = readFile(libraryFilePath);
            ArrayList<String> plagiarizedLines = getPlagiarizedLines(fileContent, libraryFileContent);

             // if plagiarism is detected, print the lines that are plagiarized
            if (plagiarizedLines.size() > 0) {
                System.out.println("The following lines in " + filePath + " are plagiarized from " + libraryFilePath + ":");
                for (String lineNumber : plagiarizedLines) {
                    System.out.println("Line " + lineNumber + ": " + fileContent.split("\\r?\\n")[Integer.parseInt(lineNumber) - 1]);
                }
            }
        }
    }
}