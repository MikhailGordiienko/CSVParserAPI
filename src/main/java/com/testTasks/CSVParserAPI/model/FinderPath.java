package com.testTasks.CSVParserAPI.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class FinderPath {

    public String findPathToTempFolder(){
        String pathToModel = this.getClass()
                .getResource("").getPath();
        String pathToFolder = ((pathToModel.substring(1, pathToModel.indexOf("CSVParserAPI") + 12)) +
                "/src/main/resources/temp/").replaceAll("/","\\\\");
        return pathToFolder;
    }
    public String findPathRar(){
        return findPathToTempFolder() + "temp.rar";
    }

    public String findPathCSV (){
        return findPathToTempFolder() + "MSI-master.csv";
    }

    public String findPathToWinRarExe() {
        String txtWithPathToWinRarExe = Paths.get(findPathToTempFolder())
                .getParent().toString() + "\\pathToWinRarExe.txt";
        try (FileReader fileReader = new FileReader(txtWithPathToWinRarExe);
             BufferedReader reader = new BufferedReader(fileReader)){
            return reader.readLine().trim();
        } catch (IOException e) {
            throw new RuntimeException("Check file \"pathToWinRarExe\" in the directory \n"
                    + " \"src\\main\\resources\\pathToWinRarExe.txt\"");
        }
    }

}
