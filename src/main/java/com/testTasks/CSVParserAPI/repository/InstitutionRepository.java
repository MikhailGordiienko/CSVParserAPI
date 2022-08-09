package com.testTasks.CSVParserAPI.repository;

import com.testTasks.CSVParserAPI.repository.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Long> {

    List<InstitutionEntity> findAllByOrderByName();

    List<InstitutionEntity> findAllByOrderByNameDesc();

    List<InstitutionEntity> findAllByOrderByState();

    List<InstitutionEntity> findAllByOrderByPhoneNumber();

    List<InstitutionEntity> findAllByOrderByType();
}
