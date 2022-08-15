package com.testTasks.CSVParserAPI.model;

//check if not work
import com.testTasks.CSVParserAPI.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
//check if not work
import com.testTasks.CSVParserAPI.entity.InstitutionEntity;
import com.testTasks.CSVParserAPI.repository.InstitutionRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ParserCSV {

    //check if not work
    @Autowired
    private InstitutionRepository institutionRepository;
    //check if not work
    private FinderPath finder;

    public ParserCSV() {
        finder = new FinderPath();
    }

    public List<String[]> parseInstitutionEntity(){
        List<String[]> institutions = new ArrayList<>();
        String pathToCSVFile = finder.findPathCSV();
        try (FileInputStream inputStream = new FileInputStream(pathToCSVFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            boolean firstLine = false;
            while (reader.ready()){
                if (firstLine == false){
                    firstLine = true;
                    reader.readLine();
                    continue;
                }
                institutions.add(reader.readLine().split(";"));
            }
        } catch (IOException e) {
            throw new RuntimeException("error while reading MSI-master.csv");
        }
        return institutions;
    }

    public void updateDataBase(String linkToCsvFile){
        try (FileInputStream inputStream = new FileInputStream(linkToCsvFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            reader.readLine();  // the first line consists of the names of the columns. Let's skip it
            while (reader.ready()){
                InstitutionEntity newInstitution = getInstitution(reader.readLine());
                if (institutionRepository.findByName(newInstitution.getName()) != null){
                    institutionRepository.delete(newInstitution);
                }
                institutionRepository.save(newInstitution);
            }
        } catch (IOException e) {
            throw new RuntimeException("error while downloading .csv file");
        }
    }

    private InstitutionEntity getInstitution(String institutionInfo){
        InstitutionEntity institution = new InstitutionEntity();
        String[] parameters = institutionInfo.split(";");
        institution.setState(parameters[0]);
        institution.setName(parameters[1]);
        institution.setInstitution(parameters[2]);
        institution.setPhoneNumber(parameters[3]);
        institution.setWebSite(parameters[4]);
        return institution;
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
