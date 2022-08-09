package com.testTasks.CSVParserAPI.repository;

import com.testTasks.CSVParserAPI.entity.InstitutionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface InstitutionRepository extends CrudRepository<InstitutionEntity, Long> {
    InstitutionEntity findByName(String name);
}
