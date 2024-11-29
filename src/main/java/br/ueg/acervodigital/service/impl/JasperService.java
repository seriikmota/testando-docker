package br.ueg.acervodigital.service.impl;

import br.ueg.acervodigital.service.IJasperService;
import br.ueg.acervodigital.util.Util;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class JasperService implements IJasperService {

    @Override
    public byte[] generatePdf(String file, HashMap<String,Object> params, JRBeanCollectionDataSource dataSource) throws JRException {
        Path currentRelativePath = Paths.get("");
        String jasperFilePath = currentRelativePath.toAbsolutePath() + file;
        params.putAll(this.getParamsPdfGeneral());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, params, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private HashMap<String, Object> getParamsPdfGeneral() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nomeProjeto", "Lapageo Acervo Digital");
        params.put("endereco", "Universidade Estadual de Goiás, Anápolis - Goiás");
        params.put("website", "lapageo.ueg.br");
        params.put("email", "email@lapageo.ueg.br");
        params.put("numero", "(00) 0000-0000");
        params.put("dataCriacao", Util.formatDateWithHour(LocalDateTime.now()));
        try {
            String logoPath = Paths.get("").toAbsolutePath() + "/src/main/resources/images/logo.png";
            params.put("logo", Files.newInputStream(Path.of(logoPath)));
        } catch (IOException e) {
            System.out.println("Não foi possível carregar o arquivo de logo");
            params.put("logo", InputStream.nullInputStream());
        }
        return params;
    }
}
