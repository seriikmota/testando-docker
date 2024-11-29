package br.ueg.acervodigital.mapper;

import br.ueg.acervodigital.dto.list.PostListDTO;
import br.ueg.acervodigital.dto.request.PostRequestDTO;
import br.ueg.acervodigital.dto.response.PostResponseDTO;
import br.ueg.acervodigital.entities.Post;
import br.ueg.acervodigitalarquitetura.mapper.GenericMapper;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {PostImageMapper.class}
)
public interface PostMapper extends GenericMapper<PostRequestDTO, PostResponseDTO, PostListDTO, Post, Long> {
    @Mapping(source = "files", target = "images")
    Post toModel(PostRequestDTO postRequestDTO);
}
