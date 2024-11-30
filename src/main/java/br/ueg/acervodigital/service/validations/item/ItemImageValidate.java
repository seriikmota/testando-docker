package br.ueg.acervodigital.service.validations.item;

import br.ueg.acervodigital.entities.Item;
import br.ueg.acervodigital.entities.ItemImage;
import br.ueg.acervodigital.enums.ErrorEnum;
import br.ueg.genericarchitecture.enums.ValidationActionsEnum;
import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.validation.IValidations;

import java.util.List;

public class ItemImageValidate implements IValidations<Item> {
    @Override
    public void validate(Item data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        for (ItemImage image : data.getImages()) {
            String contentType = image.getContentType();
            if (!ItemImage.ALLOWED_MIME_TYPES.contains(contentType)) {
                messagesToThrow.add(new Message(ErrorEnum.IMAGE_CONTENT_TYPE_INVALID, contentType));
            }
        }
    }
}
