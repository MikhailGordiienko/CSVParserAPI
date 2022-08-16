package com.testTasks.CSVParserAPI.repository;

import com.testTasks.CSVParserAPI.entity.InstitutionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InstitutionRepository extends CrudRepository<InstitutionEntity, Long> {
    InstitutionEntity findByName(String name);

    InstitutionRepository deleteByName(String name);

    List<InstitutionEntity> findAll();
    List<InstitutionEntity> findAllByOrderByName();

    List<InstitutionEntity> findAllByOrderByState();

    List<InstitutionEntity> findAllByOrderByPhoneNumber();
    List<InstitutionEntity> findAllByOrderByInstitution();

}
