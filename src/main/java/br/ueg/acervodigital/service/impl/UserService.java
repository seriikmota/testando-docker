package br.ueg.acervodigital.service.impl;

import br.ueg.acervodigital.dto.list.UserListDTO;
import br.ueg.acervodigital.dto.request.UserRequestDTO;
import br.ueg.acervodigital.dto.response.UserResponseDTO;
import br.ueg.acervodigital.entities.User;
import br.ueg.acervodigital.entities.UserLog;
import br.ueg.acervodigital.enums.ErrorEnum;
import br.ueg.acervodigital.mapper.UserMapper;
import br.ueg.acervodigital.repository.UserLogRepository;
import br.ueg.acervodigital.repository.UserRepository;
import br.ueg.acervodigital.service.IUserService;
import br.ueg.genericarchitecture.enums.ApiErrorEnum;
import br.ueg.genericarchitecture.exception.DataException;
import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.service.impl.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<UserRequestDTO, UserResponseDTO, UserListDTO, User, UserRepository, UserMapper, Long>
        implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Override
    protected void prepareToCreate(User data) {
    }

    @Override
    protected void prepareToUpdate(User dataDB) {

    }

    @Override
    protected void prepareToDelete(User dataDB) {

    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    protected void validateToMapCreate(UserRequestDTO dto, List<Message> messagesToThrow) {
        if (dto.getPassword() == null)
            messagesToThrow.add(new Message(ErrorEnum.PASSWORD_NOT_ENTIRED));
        if (dto.getConfirmPassword() == null)
            messagesToThrow.add(new Message(ErrorEnum.CONFIRM_PASSWORD_NOT_ENTIRED));
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            messagesToThrow.add(new Message(ErrorEnum.PASSWORD_DIFERENT));
        if (dto.getPassword().trim().length() < 8)
            messagesToThrow.add(new Message(ErrorEnum.PASSWORD_INVALID));

        dto.setPassword(encryptPassword(dto.getPassword()));
    }

    @Override
    protected void validateToMapUpdate(UserRequestDTO dto, List<Message> messagesToThrow) {
        if (dto.getPassword() != null) {
            if (dto.getConfirmPassword() == null)
                messagesToThrow.add(new Message(ErrorEnum.CONFIRM_PASSWORD_NOT_ENTIRED));
            if (!dto.getPassword().equals(dto.getConfirmPassword()))
                messagesToThrow.add(new Message(ErrorEnum.PASSWORD_DIFERENT));
            if (dto.getPassword().trim().length() < 8)
                messagesToThrow.add(new Message(ErrorEnum.PASSWORD_INVALID));

            dto.setPassword(encryptPassword(dto.getPassword()));
        }
    }

    @Override
    public void toggleUserAccess(Long userId, boolean enable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataException(ApiErrorEnum.NOT_FOUND, HttpStatus.NOT_FOUND));
        user.setEnabled(enable);
        userRepository.save(user);
    }

    @Override
    public boolean isUserEnabled(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataException(ApiErrorEnum.NOT_FOUND, HttpStatus.NOT_FOUND));
        return user.isEnabled();
    }

    @Override
    public Page<UserLog> getLogUsers(Pageable pageable) {
        return userLogRepository.findAll(pageable);
    }
}
