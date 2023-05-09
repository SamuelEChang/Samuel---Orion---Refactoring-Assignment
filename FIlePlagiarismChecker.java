import java.io.IOException;
import java.util.ArrayList;

public class FilePlagiarismChecker {
    private String filePath;
    private ArrayList<String> libraryFiles;

    public FilePlagiarismChecker(String filePath, String libraryPath) throws IOException {
        this.filePath = filePath;
        FileReadAndWrite fileReadAndWrite = new FileReadAndWrite(libraryPath);
        this.libraryFiles = fileReadAndWrite.findFiles(libraryPath, "");
    }

    public void checkPlagiarism() throws IOException {
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

    private int countLines(String filePath) throws IOException {
        FileReadAndWrite fileReadAndWrite = new FileReadAndWrite(filePath);
        return fileReadAndWrite.countLines();
    }

    private String readFile(String filePath) throws IOException {
        FileReadAndWrite fileReadAndWrite = new FileReadAndWrite(filePath);
        return fileReadAndWrite.readFile();
    }

    private ArrayList<String> getPlagiarizedLines(String fileContent, String libraryFileContent) {
        ArrayList<String> plagiarizedLines = new ArrayList<>();
        String[] fileLines = fileContent.split("\\r?\\n");
        String[] libraryFileLines = libraryFileContent.split("\\r?\\n");
        for (int i = 0; i < fileLines.length; i++) {
            for (int j = 0; j < libraryFileLines.length; j++) {
                if (fileLines[i].equals(libraryFileLines[j])) {
                    plagiarizedLines.add(String.valueOf(i + 1));
                }
            }
        }
        return plagiarizedLines;
    }
}
