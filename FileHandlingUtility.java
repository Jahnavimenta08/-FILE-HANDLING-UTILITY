import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandlingUtility {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName;

        while (true) {
            System.out.println("\nFile Handling Utility Menu:");
            System.out.println("1. Create File");
            System.out.println("2. Write to File");
            System.out.println("3. Read File");
            System.out.println("4. Append to File");
            System.out.println("5. Delete File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter file name to create: ");
                    fileName = scanner.nextLine();
                    createFile(fileName);
                    break;
                case 2:
                    System.out.print("Enter file name to write to: ");
                    fileName = scanner.nextLine();
                    writeFile(fileName, scanner);
                    break;
                case 3:
                    System.out.print("Enter file name to read: ");
                    fileName = scanner.nextLine();
                    readFile(fileName);
                    break;
                case 4:
                    System.out.print("Enter file name to append to: ");
                    fileName = scanner.nextLine();
                    appendFile(fileName, scanner);
                    break;
                case 5:
                    System.out.print("Enter file name to delete: ");
                    fileName = scanner.nextLine();
                    deleteFile(fileName);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeFile(String fileName, Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Enter content to write (type 'exit' on a new line to finish):");
            String line;
            while (true) {
                line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Content written to file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred or file not found.");
            e.printStackTrace();
        }
    }

    public static void appendFile(String fileName, Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // Append mode
            System.out.println("Enter content to append (type 'exit' on a new line to finish):");
            String line;
            while (true) {
                line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Content appended to file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file or file not found.");
        }
    }
}