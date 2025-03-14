package com.larrykin.file_manager.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class RenameFiles {

    private static final Logger logger = LoggerFactory.getLogger(RenameFiles.class);

    public static void main(String[] args) {
        String dirPath = "C:\\programming\\zz notes";
        File dir = new File(dirPath);

        if (!dir.exists() || !dir.isDirectory()) {
            logger.error("Invalid directory path");
            return;
        }

        File[] files = dir.listFiles((file, name) -> name.endsWith(".txt"));
        if (files == null) {
            logger.error("No .txt files found in the directory");
            return;
        }

        for (File file : files) {
            String newFileName = file.getName().replace(".txt", ".md");
            File newFile = new File(dirPath + File.separator + newFileName);
            if (file.renameTo(newFile)) {
                logger.info("Renamed: " + file.getName() + " to " + newFileName);
            } else {
                logger.error("Failed to rename: " + file.getName());
            }
        }
    }
}
