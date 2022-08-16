package com.testTasks.CSVParserAPI.model;


import com.testTasks.CSVParserAPI.entity.InstitutionEntity;
import com.testTasks.CSVParserAPI.repository.InstitutionRepository;
import java.io.*;
import java.net.URL;

public class ParserCSV {

    public void updateDataBase(String linkToCsvFile, InstitutionRepository institutionRepository){
        try (var inputStream = new InputStreamReader(new URL(linkToCsvFile).openStream());
                BufferedReader reader = new BufferedReader(inputStream)){
            reader.readLine();  // the first line consists of the names of the columns. Let's skip it
            while (reader.ready()){
                InstitutionEntity newInstitution = getInstitution(reader.readLine(), institutionRepository);
                institutionRepository.save(newInstitution);
            }
        } catch (IOException e) {
            throw new RuntimeException("error while downloading .csv file");
        }
    }

    private InstitutionEntity getInstitution(String institutionInfo, InstitutionRepository repository){
        String[] parameters = institutionInfo.split(";");
        InstitutionEntity institution = repository.findByNameEquals(parameters[1]);
        if(institution == null){
            institution = new InstitutionEntity();
        }
        institution.setState(parameters[0]);
        institution.setName(parameters[1]);
        institution.setInstitution(parameters[2]);
        institution.setPhoneNumber(parameters[3]);
        institution.setWebSite(parameters[4]);
        return institution;
    }
}
