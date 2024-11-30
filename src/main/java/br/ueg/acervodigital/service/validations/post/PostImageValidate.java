package br.ueg.acervodigital.service.validations.post;

import br.ueg.acervodigital.entities.Post;
import br.ueg.acervodigital.entities.PostImage;
import br.ueg.acervodigital.enums.ErrorEnum;
import br.ueg.genericarchitecture.enums.ValidationActionsEnum;
import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.validation.IValidations;

import java.util.List;

public class PostImageValidate implements IValidations<Post> {
    @Override
    public void validate(Post data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        for (PostImage image : data.getImages()) {
            String contentType = image.getContentType();
            if (!PostImage.ALLOWED_MIME_TYPES.contains(contentType)) {
                messagesToThrow.add(new Message(ErrorEnum.IMAGE_CONTENT_TYPE_INVALID, contentType));
            }
        }
    }
}