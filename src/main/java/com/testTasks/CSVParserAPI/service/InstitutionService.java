package com.testTasks.CSVParserAPI.service;


import com.testTasks.CSVParserAPI.entity.InstitutionEntity;
import com.testTasks.CSVParserAPI.model.DatabaseManager;
import com.testTasks.CSVParserAPI.model.FileManager;
import com.testTasks.CSVParserAPI.model.Institution;
import com.testTasks.CSVParserAPI.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    public void updateListOfInstitutes(String linkForDownloading) {
        FileManager fileManager = new FileManager();
        DatabaseManager databaseManager = new DatabaseManager();
        fileManager.downloadRar(linkForDownloading);
        fileManager.unrarCSV();
        fileManager.removeRar();
        databaseManager.saveEntityToDatabase(institutionRepository);
        fileManager.removeCSV();
    }
    public List<Institution> findAll() {
        List<Institution> institutions = new ArrayList<>();
        var found = institutionRepository.findAll();
        for (InstitutionEntity institutionEntity: found){
            institutions.add(Institution.toModel(institutionEntity));
        }
        return institutions;
    }

    public List<Institution> sortByName() {
        List<Institution> institutions = findAll();
        institutions.sort((institution1, institution2) ->
                institution1.getName().compareTo(institution2.getName())
        );
        return institutions;
    }

    public List<Institution> sortByState() {
        List<Institution> institutions = findAll();
        institutions.sort((institution1, institution2) ->
                institution1.getState().compareTo(institution2.getState())
        );
        return institutions;
    }

    public List<Institution> sortByPhoneNumber() {
        List<Institution> institutions = findAll();
        institutions.sort((institution1, institution2) ->
                institution1.getPhoneNumber().compareTo(institution2.getPhoneNumber())
        );
        return institutions;
    }

    public List<Institution> sortByInstitution() {
        List<Institution> institutions = findAll();
        institutions.sort((institution1, institution2) ->
                institution1.getInstitution().compareTo(institution2.getInstitution())
        );
        return institutions;
    }
}
