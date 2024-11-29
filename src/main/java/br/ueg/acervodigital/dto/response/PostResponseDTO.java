
package br.ueg.acervodigital.dto.response;

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
public class PostResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;
    private String subtitle;
    private String content;
    private Boolean approval;
    private LocalDateTime publicationDate;
    private String tag;
    private List<ImageDTO> images;
}
