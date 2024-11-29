package br.ueg.acervodigital.repository;

import br.ueg.acervodigital.entities.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}