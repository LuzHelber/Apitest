package br.com.projetos.api.services;

import br.com.projetos.api.domain.Usuario;

import java.util.List;

public interface UserService {

    Usuario findById(Integer id);
    List<Usuario> findAll();
}
