package com.testTasks.CSVParserAPI.controller;


import com.testTasks.CSVParserAPI.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/institutions")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @PostMapping("/update")
    public ResponseEntity updateDatabase(@RequestParam String linkForDownloading){
        institutionService.updateListOfInstitutes(linkForDownloading);
        return ResponseEntity.ok("The list of Institute was updated");
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(institutionService.findAll());
    }

    @GetMapping("/getAllSortByName")
    public ResponseEntity getAllSortByName(){
        return ResponseEntity.ok(institutionService.sortByName());
    }

    @GetMapping("/getAllSortByPhoneNumber")
    public ResponseEntity getAllSortByPhoneNumber(){
        return ResponseEntity.ok(institutionService.sortByPhoneNumber());
    }

    @GetMapping("/getAllSortByState")
    public ResponseEntity getAllSortByState(){
        return ResponseEntity.ok(institutionService.sortByState());
    }

    @GetMapping("/getAllSortByInstitution")
    public ResponseEntity getAllSortByInstitution(){
        return ResponseEntity.ok(institutionService.sortByInstitution());
    }
}
