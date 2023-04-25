package br.com.projetos.api.services.impl;

import br.com.projetos.api.domain.Usuario;
import br.com.projetos.api.domain.dto.UserDTO;
import br.com.projetos.api.repositories.UserRepository;
import br.com.projetos.api.services.UserService;
import br.com.projetos.api.services.exceptions.DataIntegrItyViolationException;
import br.com.projetos.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Usuario> findAll() { return repository.findAll(); }

    @Override
    public Usuario create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Usuario.class)); }

    @Override
    public Usuario update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Usuario.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO obj){
        Optional<Usuario> usuario = repository.findByEmail(obj.getEmail());
        if(usuario.isPresent() && !usuario.get().getId().equals(obj.getId())) {
            throw new DataIntegrItyViolationException("Email já cadastrado no sistema");
        }
    }

}
