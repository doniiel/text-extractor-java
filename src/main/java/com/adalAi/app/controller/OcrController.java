package com.adalAi.app.controller;

import com.adalAi.app.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/ocr")
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAndExtractText(@RequestParam("file") MultipartFile file) {
        String extractedText = ocrService.extractText(file);
        return ResponseEntity.ok(extractedText);
    }
}
