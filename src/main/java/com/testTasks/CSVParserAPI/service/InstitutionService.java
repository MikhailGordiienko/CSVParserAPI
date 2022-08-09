package com.testTasks.CSVParserAPI.service;

import com.testTasks.CSVParserAPI.repository.InstitutionRepository;
import com.testTasks.CSVParserAPI.repository.entity.InstitutionEntity;
import com.testTasks.CSVParserAPI.utils.RarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final ParsingService parsingService;

    public void updateDatabase(MultipartFile multipartFile) throws IOException {

        Path archive = Files.createTempFile("arch_", null);
        Path unpacked = Files.createTempFile("unp_", null);
        Files.copy(multipartFile.getInputStream(), archive, StandardCopyOption.REPLACE_EXISTING);
        //One must be crucified for writing shit like this
        unpacked = RarService.extractArchive(archive.toFile(), unpacked.toFile());

        List<InstitutionEntity> entities = parsingService.parse(unpacked);
        institutionRepository.saveAll(entities);
    }

    public List<InstitutionEntity> findAll() {
        return institutionRepository.findAll();
    }

    public List<InstitutionEntity> sortByName(Boolean asc) {
        if (asc) {
            return institutionRepository.findAllByOrderByName();
        } else {
            return institutionRepository.findAllByOrderByNameDesc();
        }
    }

    public List<InstitutionEntity> sortByState() {
        return institutionRepository.findAllByOrderByState();
    }

    public List<InstitutionEntity> sortByPhoneNumber() {
        return institutionRepository.findAllByOrderByPhoneNumber();
    }

    public List<InstitutionEntity> sortByInstitution() {
        return institutionRepository.findAllByOrderByType();
    }
}
