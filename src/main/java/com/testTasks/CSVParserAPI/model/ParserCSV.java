package com.testTasks.CSVParserAPI.model;

import com.testTasks.CSVParserAPI.entity.InstitutionEntity;
import com.testTasks.CSVParserAPI.repository.InstitutionRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ParserCSV {
    private FinderPath finder;

    public ParserCSV() {
        finder = new FinderPath();
    }

    public List<String[]> parseInstitutionEntity(){
        List<String[]> institutions = new ArrayList<>();
        String pathToCSVFile = finder.findPathCSV();
        try (FileInputStream inputStream = new FileInputStream(pathToCSVFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            boolean firstLine = true;
            while (reader.ready()){
                if (firstLine){
                    reader.readLine();
                    firstLine = false;
                    continue;
                }
                institutions.add(reader.readLine().split(";"));
            }
        } catch (IOException e) {
            throw new RuntimeException("error while reading MSI-master.csv");
        }
        return institutions;
    }

    public void saveEntitiesToDatabase (InstitutionRepository institutionRepository){
        List<String[]> listInstitutionEntity = parseInstitutionEntity();
        for (String[] institutionInfo: listInstitutionEntity) {
            InstitutionEntity entity = new InstitutionEntity();
            entity.setState(institutionInfo[0]);
            entity.setName(institutionInfo[1]);
            entity.setInstitution(institutionInfo[2]);
            entity.setPhoneNumber(institutionInfo[3]);
            entity.setWebSite(institutionInfo[4]);
            var checkedEntity = institutionRepository.findByName(institutionInfo[1]);
            if(checkedEntity != null)
                institutionRepository.delete(checkedEntity);
            institutionRepository.save(entity);
        }
    }
}
