package br.com.projetos.api.resources;

import br.com.projetos.api.domain.Usuario;
import br.com.projetos.api.domain.dto.UserDTO;
import br.com.projetos.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class UserResourceTest {

    public static final Integer INDEX    = 0;
    public static final Integer ID       = 1;
    public static final String  NAME     = "Helber";
    public static final String  EMAIL    = "helber@mail.com";
    public static final String  PASSWORD = "3663";
    private Usuario usuario;
    private UserDTO userDTO;
    private Optional<Usuario> optionalUsuario;

    @InjectMocks
    private UserResource resource;
    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;



    @BeforeEach
    void setUp() {
        openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        usuario = new Usuario(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUsuario = Optional.of(new Usuario(ID, NAME, EMAIL, PASSWORD));
    }
}