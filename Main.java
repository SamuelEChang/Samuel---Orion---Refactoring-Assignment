import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = null;
        // Loop until a valid file name is provided by the user
        while (fileName == null) {
            System.out.println("What file do you want to check for plagiarism?");
            fileName = scanner.nextLine();
            File file = new File(fileName);
            // If the file does not exist, print an error message and set fileName to null to repeat the loop
            if (!file.exists()) {
                System.out.println("File path doesn't exist.");
                fileName = null;
            // If the file exists but is not a regular file, print an error message and set fileName to null to repeat the loop
            } 
            else if (!file.isFile()) {
                System.out.println("Not a file.");
                fileName = null;
            }
        }
        scanner.close();

        try {
            FilePlagiarismChecker plagiarismChecker = new FilePlagiarismChecker(fileName, "library");
            plagiarismChecker.checkPlagiarism();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}