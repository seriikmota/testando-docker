package br.ueg.acervodigital.mapper;

import br.ueg.acervodigital.dto.list.ItemListDTO;
import br.ueg.acervodigital.dto.request.ItemRequestDTO;
import br.ueg.acervodigital.dto.response.ItemResponseDTO;
import br.ueg.acervodigital.entities.Item;
import br.ueg.acervodigitalarquitetura.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ItemImageMapper.class}
)
public interface ItemMapper extends GenericMapper<ItemRequestDTO, ItemResponseDTO, ItemListDTO, Item, Long> {
    @Mapping(source = "files", target = "images")
    Item toModel(ItemRequestDTO itemRequestDTO);
}
