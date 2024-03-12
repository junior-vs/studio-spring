package com.studio.spring.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.studio.spring.services.CnabService;


@RestController
@RequestMapping("/cnab")
public class CnabController {

    CnabService service;

    public CnabController(CnabService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam ("file")MultipartFile file) {

        try {
            service.upload(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "Upload realizado com sucesso!";
    }
}
