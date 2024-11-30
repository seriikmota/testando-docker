package br.ueg.acervodigital.mapper;

import br.ueg.acervodigital.dto.ImageDTO;
import br.ueg.acervodigital.entities.ItemImage;
import br.ueg.genericarchitecture.dto.FileDTO;
import org.mapstruct.*;

import java.util.Base64;
import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ItemImageMapper {
    @Mapping(source = "file", target = "image")
    ItemImage toModel(FileDTO fileDTO);

    @Named("toDTO")
    default ImageDTO toDTO(ItemImage model) {

        return ImageDTO.builder()
                .id(model.getId())
                .image(Base64.getEncoder().encodeToString(model.getImage()))
                .build();
    }

    @IterableMapping(qualifiedByName = "toDTO")
    List<ImageDTO> fromModelListToDTOList(List<ItemImage> ItemImage);
}