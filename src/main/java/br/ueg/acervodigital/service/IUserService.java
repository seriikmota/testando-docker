package br.ueg.acervodigital.service;

import br.ueg.acervodigital.entities.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    boolean isUserEnabled(Long userId);
    void toggleUserAccess(Long userId, boolean enable);
    Page<UserLog> getLogUsers(Pageable pageable);
}
