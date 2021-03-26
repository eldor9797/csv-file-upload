package com.nodeapp.demo.controllers;


import com.nodeapp.demo.entity.Document;
import com.nodeapp.demo.entity.ParentDoc;
import com.nodeapp.demo.payload.DocumentDTO;
import com.nodeapp.demo.repository.DocumentRepository;
import com.nodeapp.demo.repository.ParentDocRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UploadController {

    @Autowired
    ParentDocRepository parentDocRepository;

    @Autowired
    DocumentRepository documentRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                        .withType(DocumentDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<DocumentDTO> documentDTOS = csvToBean.parse();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm:ssXXX");

                for (DocumentDTO dto : documentDTOS) {
                    Instant date = ZonedDateTime.parse(dto.getDate().trim(), formatter).toInstant();

                    List<String> stringList = Arrays
                            .asList(dto.getId()
                                    .split("\\."));

                    List<Long> collect = Arrays.stream(stringList.toArray())
                            .map(s -> Long.valueOf((String) s))
                            .collect(Collectors.toList());

                    Long id = collect.get(0);
                    Document save = documentRepository.save(
                            new Document(
                                    id,
                                    dto.getName(),
                                    date
                            )
                    );

                    collect.remove(0);

                    for (Long parentIds : collect) {
                        if (!parentDocRepository.findByDoc_DocIdAndParentId(save.getDocId(), parentIds).isPresent())
                            parentDocRepository.save(new ParentDoc(save, parentIds));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       return "success";
    }
}
