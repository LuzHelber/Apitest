package br.com.projetos.api.services;

import br.com.projetos.api.domain.Usuario;

public interface UserService {

    Usuario findById(Integer id);
}
