package br.ueg.acervodigital.service;

import br.ueg.acervodigital.entities.Post;

import java.util.List;

public interface IPostService {
    List<Post> getByTag(String tag);
}
