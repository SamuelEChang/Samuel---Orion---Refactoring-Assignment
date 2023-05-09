import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;

public class PlagiarismResultsFile extends FilePlagiarismChecker{

    public PlagiarismResultsFile(String filePath, String libraryPath) throws IOException {
        super(filePath, libraryPath);
    }

    public void printResults(String overwrite) throws IOException{
        File file = new File("results.csv");
        BufferedWriter writer = null;
        if (overwrite.equalsIgnoreCase("yes")){
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("File Name, Line Number, Copied, Similarity\n");
        }
        else if (overwrite.equalsIgnoreCase("no")){
            writer = null;
        }
        else {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("File Name, Line Number, Copied, Similarity\n");
        }
        String fileContent = readFile(filePath);
        for (String fileName : libraryFiles) {
            String libraryFilePath = fileName;
            String libraryFileContent = readFile(libraryFilePath);
            ArrayList<String> plagiarizedLines = getPlagiarizedLines(fileContent, libraryFileContent);
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