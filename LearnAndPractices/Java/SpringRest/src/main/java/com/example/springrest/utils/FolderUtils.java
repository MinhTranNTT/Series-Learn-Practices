package com.example.springrest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FolderUtils {
    public static String getDirectoryProject() {
        return System.getProperty("user.dir");
    }

    public static Path getDirectoryRootProject(String pathDir) {
        return FileSystems.getDefault().getPath(pathDir).getRoot();
    }

    public static Path getFullPathExplorer(Path pathRoot, String folderTarget) {
        return Paths.get(pathRoot.toString(), folderTarget);
    }

    public static void appendToFile(Path filePath, String content) {
        try {
            String lineBreak = getLogFormatDateTimeDetail() + System.lineSeparator();
            Files.write(filePath, (lineBreak + content + System.lineSeparator()).getBytes(), java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main888(String[] args) {
        String fullPathTarget = getFullPathTarget();
        System.out.println("fullPathTarget: " + fullPathTarget);
        String fileName = formatDateTime() + ".txt";

        String content = "Trump";

        Path finalPath = Paths.get(fullPathTarget, fileName);
        if(Files.exists(finalPath)) {
            appendToFile(finalPath, content);
            System.out.println("Content appended to existing file.");
        } else {
            createAndWriteToFile(finalPath, content);
            System.out.println("File created and content written.");
        }
        System.out.println(fileName);
    }

    public static void createAndWriteToFile(Path filePath, String content) {
        try {
            String lineBreak = getLogFormatDateTimeDetail() + System.lineSeparator();
            Files.write(filePath, (lineBreak + content + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFullPathTarget() {
        Path pathRoot = getDirectoryRootProject(getDirectoryProject());
        if (pathRoot.toString().contains("C:")) {
            pathRoot = Paths.get("D:\\");
        }
        String folderCreate = "/rest-log";
        String folderTarget = "/aaaaaaa/Test" + folderCreate;
        // return getFullPathExplorer(pathRoot, folderTarget).toString(); // OK
        return pathRoot.resolve(folderTarget).toString();
    }

    public static String formatDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH");
        return currentTime.format(formatter);
    }

    public static String getLogFormatDateTimeDetail() {
        StringBuilder sb = new StringBuilder();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        sb.append("-----------------------------------");
        sb.append(currentTime.format(formatter));
        sb.append("-----------------------------------");
        return sb.toString();
    }

    private static void createTextFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    public static boolean createFolderWithFullPath(Path path) {
        if (Files.exists(path)) {
            System.out.println("Directory already exists: " + path);
            return false;
        }

        try {
            Files.createDirectories(path);
            System.out.println("Create path " + path + " successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("Error creating path: " + path);
            return false;
        }
    }

    public static void main(String[] args) {
        String jsonString = "[{\"name\":\"TEO\",\"images\":[\"img1.png\",\"img2.png\"],\"age\":null,\"customerName\":null,\"orderName\":\"Order1\",\"stock\":null},{\"name\":\"TY\",\"images\":[\"img1.png\",\"img2.png\"],\"age\":12,\"customerName\":null,\"orderName\":null,\"stock\":0}]";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            String formattedJson = objectMapper.writeValueAsString(jsonNode);

            System.out.println(formattedJson);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
