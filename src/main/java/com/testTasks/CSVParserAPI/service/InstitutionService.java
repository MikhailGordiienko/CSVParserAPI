package com.testTasks.CSVParserAPI.service;


import com.testTasks.CSVParserAPI.entity.InstitutionEntity;
import com.testTasks.CSVParserAPI.model.ParserCSV;
import com.testTasks.CSVParserAPI.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    public void updateDataBase(String linkToCsvFile){
        new ParserCSV().updateDataBase(linkToCsvFile, institutionRepository);
    }

    public List<InstitutionEntity> findAll() {
        return institutionRepository.findAll();
    }

    public List<InstitutionEntity> sortByName() {
        return institutionRepository.findAllByOrderByName();
    }

    public List<InstitutionEntity> sortByState() {
        return institutionRepository.findAllByOrderByState();
    }

    public List<InstitutionEntity> sortByPhoneNumber() {
        return institutionRepository.findAllByOrderByPhoneNumber();
    }

    public List<InstitutionEntity> sortByInstitution() {
        return institutionRepository.findAllByOrderByInstitution();
    }
}
