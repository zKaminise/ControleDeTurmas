package com.example.ControleTurmas.Services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfGenerator {

    public void generateReport(List<Map<String, Object>> data, OutputStream outputStream, Map<String, Object> parameters) {
        try {
            InputStream templateStream = PdfGenerator.class.getClassLoader().getResourceAsStream("templates/alunoReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

            // Converter o mapa de parâmetros em um HashMap mutável
            Map<String, Object> mutableParameters = new HashMap<>(parameters);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mutableParameters, dataSource);

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar o relatório PDF: " + e.getMessage(), e);
        }
    }

}
