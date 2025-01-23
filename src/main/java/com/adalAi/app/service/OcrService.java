package com.adalAi.app.service;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface OcrService {
    String extractText(MultipartFile file);
}
