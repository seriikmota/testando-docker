package br.ueg.acervodigital.service.validations.user;

import br.ueg.acervodigital.entities.User;
import br.ueg.acervodigital.enums.ErrorEnum;
import br.ueg.acervodigitalarquitetura.enums.ValidationActionsEnum;
import br.ueg.acervodigitalarquitetura.exception.Message;
import br.ueg.acervodigitalarquitetura.validation.IValidations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMandatoryFields implements IValidations<User> {
    @Override
    public void validate(User data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (data.getName() == null || data.getName().isEmpty()) {
            messagesToThrow.add(new Message(ErrorEnum.MANDATORY_FIELD, "'nome'"));
        }
        if (data.getEmail() == null || data.getEmail().isEmpty()) {
            messagesToThrow.add(new Message(ErrorEnum.MANDATORY_FIELD, "'email'"));
        }
    }
}
