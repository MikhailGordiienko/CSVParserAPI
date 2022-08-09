package com.testTasks.CSVParserAPI.controller;

import com.testTasks.CSVParserAPI.repository.entity.InstitutionEntity;
import com.testTasks.CSVParserAPI.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("/healthcheck")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok("Up and running");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateDatabase(@RequestParam("file") MultipartFile file) {
        try {
            institutionService.updateDatabase(file);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Broken file");
        }
        return ResponseEntity.ok().body("The list of Institute was updated");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InstitutionEntity>> getAll() {
        return ResponseEntity.ok().body(institutionService.findAll());
    }

    @GetMapping("/getAllSortByName")
    public ResponseEntity<List<InstitutionEntity>> getAllSortByName() {
        return ResponseEntity.ok().body(institutionService.sortByName());
    }

    @GetMapping("/getAllSortByPhoneNumber")
    public ResponseEntity<List<InstitutionEntity>> getAllSortByPhoneNumber() {
        return ResponseEntity.ok().body(institutionService.sortByPhoneNumber());
    }

    @GetMapping("/getAllSortByState")
    public ResponseEntity<List<InstitutionEntity>> getAllSortByState() {
        return ResponseEntity.ok().body(institutionService.sortByState());
    }

    @GetMapping("/getAllSortByInstitution")
    public ResponseEntity<List<InstitutionEntity>> getAllSortByInstitution() {
        return ResponseEntity.ok().body(institutionService.sortByInstitution());
    }
}
