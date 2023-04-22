package br.com.projetos.api.services;

import br.com.projetos.api.domain.User;

public interface UserService {

    User  findById(Integer id);
}
