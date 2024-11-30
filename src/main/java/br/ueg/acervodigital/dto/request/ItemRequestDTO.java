package br.ueg.acervodigital.dto.request;

import br.ueg.genericarchitecture.annotation.MandatoryField;
import br.ueg.genericarchitecture.dto.DTOFile;
import br.ueg.genericarchitecture.dto.FileDTO;
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

    @MandatoryField(name = "código do item")
    private String numberCode;

    @MandatoryField(name = "coletor/doador")
    private String collector;

    @MandatoryField(name = "ano da coleta")
    private LocalDate colleactionYear;

    @MandatoryField(name = "coleção")
    private String collection;

    @MandatoryField(name = "proveniência")
    private String provenance;

    @MandatoryField(name = "localização")
    private String location;

    @MandatoryField(name = "período")
    private String period;

    @MandatoryField(name = "data de registro")
    private LocalDate registerDate;

    @MandatoryField(name = "status")
    private Integer status;

    @MandatoryField(name = "aprovação")
    private Boolean approval;

    @MandatoryField(name = "nome")
    private String name;

    @MandatoryField(name = "taxonomia")
    private String taxonomy;

    @MandatoryField(name = "descrição")
    private String description;

    @MandatoryField(name = "data de tombamento")
    private LocalDate heritageDate;

    @MandatoryField(name = "imagem(ns)")
    private List<FileDTO> files;
}
