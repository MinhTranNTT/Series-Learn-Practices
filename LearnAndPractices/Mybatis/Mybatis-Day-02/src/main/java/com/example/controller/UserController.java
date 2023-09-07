package com.example.controller;

import com.example.dto.UserRequestDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public String uploadUser(@ModelAttribute UserRequestDTO userRequestDTO) {
        List<MultipartFile> images = userRequestDTO.getImages();
        List<String> fileNames = new ArrayList<>();

        /*String folder = "images";
        Path uploadPath = Paths.get(folder);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            for (MultipartFile image : images) {
                String filename = image.getOriginalFilename();
                Path filePath = uploadPath.resolve(filename);
                image.transferTo(filePath.toFile());
                fileNames.add(filename);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        fileNames = images.stream().map(this::saveFile).collect(Collectors.toList());

        String s = userRequestDTO.toString();
        return s;
    }

    private String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path imagesDirectory  = Paths.get("src/main/resources/images");

            if (!Files.exists(imagesDirectory)) {
                Files.createDirectories(imagesDirectory);
            }

            Path imagePath = imagesDirectory.resolve(fileName);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
