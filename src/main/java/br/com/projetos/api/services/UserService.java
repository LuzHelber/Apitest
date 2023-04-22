package br.com.projetos.api.services;

import br.com.projetos.api.domain.Usuario;
import br.com.projetos.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    Usuario findById(Integer id);
    List<Usuario> findAll();
    Usuario create(UserDTO obj);

}
