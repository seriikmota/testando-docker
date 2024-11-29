package br.ueg.acervodigital.dto.request;

import br.ueg.acervodigitalarquitetura.dto.DTOFile;
import br.ueg.acervodigitalarquitetura.dto.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO implements DTOFile {
    private Long id;
    private String numberCode;
    private String collector;
    private LocalDate colleactionYear;
    private String collection;
    private String provenance;
    private String location;
    private String period;
    private LocalDate registerDate;
    private Integer status;
    private Boolean approval;
    private String name;
    private String taxonomy;
    private String description;
    private LocalDate heritageDate;
    private List<FileDTO> files;
}
