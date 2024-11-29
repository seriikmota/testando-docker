package br.ueg.acervodigital.dto.list;

import br.ueg.acervodigital.dto.ImageDTO;
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
public class PostListDTO {
    private Long id;
    private String title;
    private String subtitle;
    private String content;
    private Boolean approval;
    private LocalDateTime publicationDate;
    private String tag;
    private List<ImageDTO> images;
}
