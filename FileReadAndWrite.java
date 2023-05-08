import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReadAndWrite {
    private String fileName;

    public FileReadAndWrite(String fileName) {
        this.fileName = fileName;
    }

    public void findFiles(String dirName, String extension) {
        File[] files = new File(dirName).listFiles((dir1, filename) -> filename.endsWith(extension));
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
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

