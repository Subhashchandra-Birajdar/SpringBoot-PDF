package com.pdf;

import com.pdf.services.PdfService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PdfServiceTest {

    @Autowired
    private PdfService pdfService;

    @Test
    public void testCreatePdf() {
        // Generate the PDF
        ByteArrayInputStream pdfStream = pdfService.createPdf();

        // Assert that the PDF is not null
        assertNotNull(pdfStream, "The PDF stream should not be null");

        // Additional checks to ensure the PDF is valid
        assertTrue(isPdfValid(pdfStream), "The generated PDF should be valid");
    }

    private boolean isPdfValid(InputStream inputStream) {
        try {
            // Read the first few bytes to check if it starts with PDF header
            byte[] header = new byte[5];
            inputStream.read(header);
            String headerString = new String(header);

            // Check if the header starts with "%PDF-"
            return headerString.startsWith("%PDF-");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
