package com.testTasks.CSVParserAPI.service;

import com.testTasks.CSVParserAPI.repository.entity.InstitutionEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ParsingService {

    public List<InstitutionEntity> parse(Path file) {
        List<InstitutionEntity> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            boolean isHeader = true;
            HashMap<String, Integer> header = new HashMap<>();
            while (reader.ready()) {
                String[] split = reader.readLine().split(";");
                if (isHeader) {
                    isHeader = false;
                    for (int i = 0; i < split.length; i++) {
                        header.put(split[i], i);
                    }
                } else {
                    for (String name : getField("Name", split, header).split(",")) {
                        for (String type : getField("Institution Type", split, header).split(",")) {
                            result.add(new InstitutionEntity().setState(getField("State", split, header))
                                                              .setPhoneNumber(getField("Phone Number", split, header))
                                                              .setWebSite(getField("Website", split, header))
                                                              .setName(name)
                                                              .setType(type));
                        }
                    }
                }
            }

        } catch (IOException ignore) {
        }

        return result;
    }

    private String getField(String name, String[] fields, HashMap<String, Integer> header) {
        return fields[header.get(name)];
    }
}
