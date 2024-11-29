package br.ueg.acervodigital.service.validations.item;

import br.ueg.acervodigital.entities.Item;
import br.ueg.acervodigital.enums.ErrorEnum;
import br.ueg.acervodigitalarquitetura.enums.ValidationActionsEnum;
import br.ueg.acervodigitalarquitetura.exception.Message;
import br.ueg.acervodigitalarquitetura.validation.IValidations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMandatoryFields implements IValidations<Item> {
    @Override
    public void validate(Item data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (data.getName() == null || data.getName().isEmpty()) {
            messagesToThrow.add(new Message(ErrorEnum.MANDATORY_FIELD, "'data'"));
        }
        if (!(data.getApproval() == null)) {
            if (data.getApproval() == Boolean.TRUE && data.getImages().isEmpty()) {
                messagesToThrow.add(new Message(ErrorEnum.MANDATORY_FIELD, "'imagem'"));
            }
        }
    }
}
