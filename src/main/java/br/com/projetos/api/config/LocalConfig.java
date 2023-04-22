package br.com.projetos.api.config;

import br.com.projetos.api.domain.Usuario;
import br.com.projetos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class LocalConfig {

    @Autowired
    private UserRepository repository;


    @Bean
    public void startDB(){
        Usuario u1 = new Usuario(null, "Helber", "helber@mail.com", "3663");
        Usuario u2 = new Usuario(null, "Arthur", "arthur@mail.com", "3663");
        Usuario u3 = new Usuario(null, "Ana", "ana@mail.com", "3663");
        Usuario u4 = new Usuario(null, "Alice", "alice@mail.com", "3663");

        repository.saveAll(List.of(u1,u2,u3,u4));

    }
}
