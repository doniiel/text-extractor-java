package com.adalAi.app.service.impl;

import com.adalAi.app.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Slf4j
@Service
@RequiredArgsConstructor
public class OcrServiceImpl implements OcrService {

    private final Tesseract tesseract;

    @Override
    public String extractText(MultipartFile file) {
        try {
            // 1. Считываем изображение в память
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new RuntimeException("File is not a valid image or is corrupted.");
            }

            // 2. Запускаем OCR
            String text = tesseract.doOCR(image);
            log.info("Extracted text: {}", text);

            return text;
        } catch (IOException e) {
            log.error("Error reading file", e);
            throw new RuntimeException("Failed to read image file", e);
        } catch (TesseractException e) {
            log.error("Tesseract OCR error", e);
            throw new RuntimeException("Failed to extract text from image", e);
        }
    }
}