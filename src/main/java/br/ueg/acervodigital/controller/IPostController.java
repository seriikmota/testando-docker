package br.ueg.acervodigital.controller;

import br.ueg.acervodigital.dto.list.PostListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IPostController {
    ResponseEntity<Page<PostListDTO>> listAllWithoutRole(Pageable pageable);
}
