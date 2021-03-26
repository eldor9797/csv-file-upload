package com.nodeapp.demo.controllers;


import com.nodeapp.demo.entity.Document;
import com.nodeapp.demo.payload.DocumentDTO;
import com.nodeapp.demo.repository.DocumentRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
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
    DocumentRepository documentRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public List uploadCSVFile(@RequestParam("file") MultipartFile file) {

        List<StringBuilder> response = new ArrayList<>();


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
                                date,
                                dto.getId()
                        )
                );
                System.out.println(save);
                collect.remove(0);

            }
            List<Object[]> result = documentRepository.getResult();
            Long key = ((BigInteger) result.get(0)[0]).longValue();

            StringBuilder res = new StringBuilder((String) result.get(0)[1] + " - " + (String) result.get(0)[2]);
            result.remove(0);
            for (Object[] object : result) {
                try {
                    if (key.equals(((BigInteger) object[0]).longValue())) {
                        res.append(" -------> ").append((String) object[1]).append(" - ").append((String) object[2]);
                    } else {
                        key = ((BigInteger) object[0]).longValue();
                        response.add(res);
                        res = new StringBuilder((String) object[1] + " - " + (String) object[2]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            response.add(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
