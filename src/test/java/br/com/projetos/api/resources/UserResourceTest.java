package br.com.projetos.api.resources;

import br.com.projetos.api.domain.Usuario;
import br.com.projetos.api.domain.dto.UserDTO;
import br.com.projetos.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
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
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(usuario);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());

    }

    @Test
    void whenFindAllThenReturnAllListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(usuario));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(usuario);

        ResponseEntity<UserDTO> response = resource.create(userDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

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