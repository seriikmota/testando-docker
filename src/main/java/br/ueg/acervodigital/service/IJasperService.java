package br.ueg.acervodigital.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;

public interface IJasperService {
    byte[] generatePdf(String file, HashMap<String,Object> params, JRBeanCollectionDataSource dataSource) throws JRException;
}
