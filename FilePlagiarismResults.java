import java.io.IOException;
import java.util.ArrayList;

public class FilePlagiarismResults extends FilePlagiarismChecker{
    public FilePlagiarismResults(String filePath, String libraryPath) throws IOException {
        super(filePath, libraryPath);
    }

    public void getResults() throws IOException{
        int lineCount = countLines(filePath);
        System.out.println("The file " + filePath + " has " + lineCount + " lines.");
        
        String fileContent = readFile(filePath);

        for (String fileName : libraryFiles) {
            String libraryFilePath = fileName;
            String libraryFileContent = readFile(libraryFilePath);
            ArrayList<String> plagiarizedLines = getPlagiarizedLines(fileContent, libraryFileContent);
            if (plagiarizedLines.size() > 0) {
                System.out.println("The following lines in " + filePath + " are plagiarized from " + libraryFilePath + ":");
                for (String lineNumber : plagiarizedLines) {
                    System.out.println("Line " + lineNumber + ": " + fileContent.split("\\r?\\n")[Integer.parseInt(lineNumber) - 1]);
                }
            }
        }
    }
}