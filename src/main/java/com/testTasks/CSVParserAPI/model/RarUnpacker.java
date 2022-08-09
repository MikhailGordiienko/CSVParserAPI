package com.testTasks.CSVParserAPI.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RarUnpacker {
    private String pathToWinRarExe;
    private FinderPath finder;

    public RarUnpacker() {
        finder = new FinderPath();
        pathToWinRarExe = finder.findPathToWinRarExe();
    }

    public void unrar() {
        String absolutePath = finder.findPathRar();
        String pathToFolder = finder.findPathToTempFolder();
        String pathToWinRarExe = "C:\\Program Files (x86)\\Winrar\\WinRAR.exe";

        try {
            String unrarCmd = pathToWinRarExe + " x -r -p- -o+ " + absolutePath + " "
                    + pathToFolder;
            Runtime runtime = Runtime.getRuntime();
            Process toDo = runtime.exec(unrarCmd);
            InputStreamReader streamReader = new InputStreamReader(toDo.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if ("".equals(line)) {
                    continue;
                }
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException("The exception occurred during unpacking");
        }
    }

}
