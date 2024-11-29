package br.ueg.acervodigital.service.impl;

import br.ueg.acervodigital.dto.list.PostListDTO;
import br.ueg.acervodigital.dto.request.PostRequestDTO;
import br.ueg.acervodigital.dto.response.PostResponseDTO;
import br.ueg.acervodigital.entities.Post;
import br.ueg.acervodigital.entities.PostImage;
import br.ueg.acervodigital.entities.User;
import br.ueg.acervodigital.mapper.PostMapper;
import br.ueg.acervodigital.repository.PostRepository;
import br.ueg.acervodigital.service.IPostService;
import br.ueg.acervodigitalarquitetura.dto.CredentialDTO;
import br.ueg.acervodigitalarquitetura.enums.ApiErrorEnum;
import br.ueg.acervodigitalarquitetura.exception.DataException;
import br.ueg.acervodigitalarquitetura.security.impl.CredentialProvider;
import br.ueg.acervodigitalarquitetura.service.impl.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService extends AbstractService<PostRequestDTO, PostResponseDTO, PostListDTO, Post, PostRepository, PostMapper, Long>
        implements IPostService {

    @Autowired
    private PostRepository repository;

    @Override
    protected void prepareToCreate(Post data) {
        setUserAndPublicationDate(data);
    }

    @Override
    protected void prepareToUpdate(Post data) {
        setUserAndPublicationDate(data);
    }

    @Override
    protected void prepareToDelete(Post data) {
    }

    protected void setUserAndPublicationDate(Post data) {
        User user = new User();
        user.setId(((CredentialDTO) CredentialProvider.newInstance().getCurrentInstance()).getId());
        data.setUser(user);
        for (PostImage image : data.getImages()) {
            image.setPost(data);
        }
        data.setPublicationDate(LocalDateTime.now());
    }

    public List<Post> getByTag(String tag) {
        List<Post> temp = repository.findByTagContaining(tag);
        if(temp.isEmpty()){
            throw new DataException(ApiErrorEnum.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return temp;
    }
}
