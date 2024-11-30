package br.ueg.acervodigital.dto.request;

import br.ueg.genericarchitecture.annotation.MandatoryField;
import br.ueg.genericarchitecture.dto.DTOFile;
import br.ueg.genericarchitecture.dto.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO implements DTOFile {
    private Long id;

    @MandatoryField(name = "título")
    private String title;

    @MandatoryField(name = "subtitulo")
    private String subtitle;

    @MandatoryField(name = "conteúdo")
    private String content;

    @MandatoryField(name = "aprovação")
    private Boolean approval;

    @MandatoryField(name = "data de publicação")
    private LocalDateTime publicationDate;

    @MandatoryField(name = "etiqueta")
    private String tag;

    @MandatoryField(name = "imagem(ns)")
    private List<FileDTO> files;
}
