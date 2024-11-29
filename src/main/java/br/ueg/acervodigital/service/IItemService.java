package br.ueg.acervodigital.service;

import net.sf.jasperreports.engine.JRException;

public interface IItemService {
    byte[] exportItemsPdf() throws JRException;
    byte[] exportItemsPdf(Long id) throws JRException;
}
