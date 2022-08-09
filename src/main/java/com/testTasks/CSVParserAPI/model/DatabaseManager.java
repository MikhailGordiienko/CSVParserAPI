package com.testTasks.CSVParserAPI.model;

import com.testTasks.CSVParserAPI.repository.InstitutionRepository;

public class DatabaseManager {
    private ParserCSV parserCSV;

    public DatabaseManager() {
        parserCSV = new ParserCSV();
    }

    public void saveEntityToDatabase (InstitutionRepository institutionRepository){
        parserCSV.saveEntitiesToDatabase(institutionRepository);
    }
}
