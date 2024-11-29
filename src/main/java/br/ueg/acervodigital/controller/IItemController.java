package br.ueg.acervodigital.controller;

import br.ueg.acervodigital.dto.list.ItemListDTO;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IItemController {
    ResponseEntity<Page<ItemListDTO>> listAllWithoutRole(Pageable pageable);
    ResponseEntity<List<ItemListDTO>> getByDescription(@PathVariable("description") String description);
    ResponseEntity<?> exportPdf(@RequestParam(name = "id", required = false) Long id) throws JRException;
}
