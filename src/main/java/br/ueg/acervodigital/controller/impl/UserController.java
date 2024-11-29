package br.ueg.acervodigital.controller.impl;

import br.ueg.acervodigital.controller.IUserController;
import br.ueg.acervodigital.dto.list.UserListDTO;
import br.ueg.acervodigital.dto.request.UserRequestDTO;
import br.ueg.acervodigital.dto.response.UserResponseDTO;
import br.ueg.acervodigital.entities.UserLog;
import br.ueg.acervodigital.service.impl.UserService;
import br.ueg.acervodigitalarquitetura.controller.impl.AbstractCrudController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/user")
public class UserController extends AbstractCrudController<UserRequestDTO, UserResponseDTO, UserListDTO, UserService, Long>
        implements IUserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}/access")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toggleUserAccess(@PathVariable Long id, @RequestParam boolean enable) {
        userService.toggleUserAccess(id, enable);
    }

    @GetMapping("/{id}/enabled")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUserEnabled(@PathVariable Long id) {
        return userService.isUserEnabled(id);
    }

    @GetMapping("/getLogUsers")
    public ResponseEntity<Page<UserLog>> getLogUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getLogUsers(pageable));
    }
}
