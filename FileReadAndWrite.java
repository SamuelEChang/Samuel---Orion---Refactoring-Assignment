import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReadAndWrite {
    private String fileName;

    public FileReadAndWrite(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String> findFiles(String dir, String extension) {
        ArrayList<String> fileNames = new ArrayList<>();
        File directory = new File(dir);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(extension)) {
                    fileNames.add(file.getPath());
                }
            }
        }
        return fileNames;
    }

    public int countLines() throws IOException {
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        }
        return lineCount;
    }

    public String readFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append(System.lineSeparator());
                line = br.readLine();
            }
        }
        return sb.toString();
    }

}