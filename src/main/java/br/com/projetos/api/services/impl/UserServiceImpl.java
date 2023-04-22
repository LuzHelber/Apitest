package br.com.projetos.api.services.impl;

import br.com.projetos.api.domain.Usuario;
import br.com.projetos.api.repositories.UserRepository;
import br.com.projetos.api.services.UserService;
import br.com.projetos.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
