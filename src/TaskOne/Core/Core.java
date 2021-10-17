package TaskOne.Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Core {

    private final String START_POSITION = "/";
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
