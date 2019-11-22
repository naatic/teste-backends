import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting tests...");

        String[] inputFiles = {
            "./../test/input/input000.txt",
            "./../test/input/input001.txt",
            "./../test/input/input002.txt",
            "./../test/input/input003.txt",
            "./../test/input/input004.txt",
            "./../test/input/input005.txt",
            "./../test/input/input006.txt",
            "./../test/input/input007.txt",
            "./../test/input/input008.txt",
            "./../test/input/input009.txt",
            "./../test/input/input010.txt",
            "./../test/input/input011.txt",
            "./../test/input/input012.txt"
        };

        String[] outputFiles = {
            "./../test/output/output000.txt",
            "./../test/output/output001.txt",
            "./../test/output/output002.txt",
            "./../test/output/output003.txt",
            "./../test/output/output004.txt",
            "./../test/output/output005.txt",
            "./../test/output/output006.txt",
            "./../test/output/output007.txt",
            "./../test/output/output008.txt",
            "./../test/output/output009.txt",
            "./../test/output/output010.txt",
            "./../test/output/output011.txt",
            "./../test/output/output012.txt"
        };

        for(int i = 0; i <= outputFiles.length - 1; ++i) {
            Path inputPath = Paths.get(inputFiles[i]);
            Path outputPath = Paths.get(outputFiles[i]);

            List<String> inputLines = Files.readAllLines(inputPath);
            List<String> outputLines = Files.readAllLines(outputPath);

            if (Solution.processMessages(inputLines).equals(outputLines.get(0))) {
                System.out.println(String.format("Test %s/%s - Passed", i + 1, outputFiles.length));
            } else {
                System.out.println(String.format("Test %s/%s - Failed", i + 1, outputFiles.length));
            }
        }
    }
}