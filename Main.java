import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileReadAndWrite fileRW = new FileReadAndWrite("library/MB1.txt");
        String fileName = "MB1.txt";
        try {
            int lineCount = fileRW.countLines();
            System.out.println("The file " + fileName + " has " + lineCount + " lines.");


            File[] files = new File("library").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName() + " " + file.length() + " bytes");
                }
            }
            String fileContent = fileRW.readFile();
            System.out.println("The content of " + fileName + " is:\n" + fileContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    