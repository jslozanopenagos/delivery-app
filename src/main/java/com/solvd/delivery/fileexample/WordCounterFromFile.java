package com.solvd.delivery.fileexample;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class WordCounterFromFile {
    private static final Logger LOGGER = LogManager.getLogger(WordCounterFromFile.class);

    public static int wordCounter(String inputFileName, String outputFileName,String keyword) {
        if (StringUtils.isBlank(keyword)) {
            LOGGER.warn("Keyword cannot be null, empty, or blank");
            return 0;
        }

        String content = fileReader(inputFileName);

        if (content == null) {
            LOGGER.error("File could not be read: {}", inputFileName);
            return 0;
        }

        content = content.toLowerCase();
        keyword = keyword.toLowerCase();

        int count = StringUtils.countMatches(content, keyword);

        String message = String.format(
                "The word '%s' appears %d time(s) in file: %s",
                keyword, count, inputFileName
        );

        LOGGER.info(message);

        fileWriter(outputFileName, message);

        return count;
    }

    public static String fileReader(String fileName){
        try{
            ClassLoader classLoader = WordCounterFromFile.class.getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            String content = FileUtils.readFileToString(file, "UTF-8");
            LOGGER.info("Successfully read file: {}", fileName);

            return content;

        } catch (IOException e){
            LOGGER.error("Error reading file: {}", fileName);
            return null;
        }
    }

    public static void fileWriter(String fileName, String content){
        File file = new File(fileName);

        if(StringUtils.isBlank(content)){
            LOGGER.warn("Attempted to write empty or blank content to file: {}", fileName);
            return;
        }

        try {
            FileUtils.writeStringToFile(
                    file,
                    StringUtils.trim(content) + System.lineSeparator(),
                    "UTF-8",
                    true
            );

            LOGGER.info("Successfully wrote to file: {}", fileName);

        } catch (IOException e) {
            LOGGER.error("Error writing to file: {}", fileName, e);
        }
    }
}
