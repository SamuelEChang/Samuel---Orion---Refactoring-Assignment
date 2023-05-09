import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
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
        // check if "results.csv" file already exists
        File file = new File("results.csv");
        String overwrite = "";
        boolean fileExists = file.exists();
        BufferedWriter writer = null;
        if (fileExists) {
            System.out.println("File 'results.csv' already exists. Do you want to overwrite it? (yes/no)");
            overwrite = scanner.nextLine();
        }
        // if the results.csv does not exist
        else {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("File Name, Line Number, Copied, Similarity\n");
        }
        scanner.close();

        try {
            FilePlagiarismResults results = new FilePlagiarismResults(fileName, "library");
            results.getResults();
            PlagiarismResultsFile resultsCSV = new PlagiarismResultsFile(fileName, "library");
            resultsCSV.printResults(overwrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}