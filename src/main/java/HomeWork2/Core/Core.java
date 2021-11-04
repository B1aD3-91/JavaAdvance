package HomeWork2.Core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Core {

    private final String START_POSITION = "/";
    private final int MAX_BYTES_SIZE = 10000000;
    private File files = new File(START_POSITION);
    private Scanner scanner = new Scanner(System.in);
    boolean loop = true;

    public void userHandlerLoop() {
        printFiles(files);
        while (loop) {
            System.out.print("Current position: " + files.getAbsolutePath() + " ");
            scanner = new Scanner(System.in);
            var userPath = scanner.next();
            try {
                switch (userPath) {
                    case "cd" -> execute(scanner.next());
                    case ".." -> up();
                    case "read" -> readFile(scanner.next());
                    case "exit" -> loop = false;
                    default -> System.out.println("Unknown command!");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void up() {
        if (files.getParent() != null) {
            files = new File(files.getParent());
            printFiles(files);
            return;
        }
        throw new InputMismatchException("Top level path!");
    }

    private void execute(String path) throws FileNotFoundException {
        files = new File(path);
        if (files.exists()) {
            printFiles(files);
            return;
        }
        throw new FileNotFoundException("No files found!");
    }

    private void readFile(String filePath) throws IOException {
        try (InputStream is = new FileInputStream(filePath)) {
            if (Files.size(Path.of(filePath)) >= MAX_BYTES_SIZE){
                throw new IOException("File is too big to read!!!");
            }
            System.out.println(new String(is.readAllBytes()));
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    private void printFiles(File files) {
        if (files.exists()) {
            System.out.println(Arrays.stream(
                    files.listFiles())
                    .sorted(Comparator.comparing(File::isFile))
                    .map(File::getName)
                    .collect(Collectors.joining("\n")));
        }
    }
}
