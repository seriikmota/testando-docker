package br.ueg.acervodigital.controller.impl;

import br.ueg.acervodigital.controller.IItemController;
import br.ueg.acervodigital.dto.list.ItemListDTO;
import br.ueg.acervodigital.dto.request.ItemRequestDTO;
import br.ueg.acervodigital.dto.response.ItemResponseDTO;
import br.ueg.acervodigital.mapper.ItemMapper;
import br.ueg.acervodigital.service.impl.ItemService;
import br.ueg.acervodigitalarquitetura.controller.impl.AbstractCrudFileController;
import io.swagger.v3.oas.annotations.Operation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}/item")
public class ItemController extends AbstractCrudFileController<ItemRequestDTO, ItemResponseDTO, ItemListDTO, ItemService, Long>
        implements IItemController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected ItemService service;
    @Autowired
    protected ItemMapper mapper;

    @GetMapping("/list")
    public ResponseEntity<Page<ItemListDTO>> listAllWithoutRole(Pageable pageable){
        Page<ItemListDTO> listDTO = service.listAll(pageable);
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping(path = "/search/{description}")
    @Operation(description = "End point para obter dados por descrição")
    public ResponseEntity<List<ItemListDTO>> getByDescription(
            @PathVariable("description") String description
    ) {
        List<ItemListDTO> modelList = mapper.toDtoList(service.getByDescription(description));
        return ResponseEntity.of(
                Optional.ofNullable(modelList)
        );
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> exportPdf(@RequestParam(name = "id", required = false) Long id) throws JRException {
        byte[] pdf;
        if (id != null) {
            pdf = service.exportItemsPdf(id);
        } else {
            pdf = service.exportItemsPdf();
        }

        String fileName = "AcervoDigital.pdf";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(pdf));
    }
}
