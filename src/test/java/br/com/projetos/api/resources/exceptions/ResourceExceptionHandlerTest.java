package br.com.projetos.api.resources.exceptions;

import br.com.projetos.api.services.exceptions.DataIntegrItyViolationException;
import br.com.projetos.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";
    public static final String EMAIL_JÁ_CADASTRADO = "Email já cadastrado";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
   }
    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(
                new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO),
                new MockHttpServletRequest());

        response.getBody().getPath();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());

        assertEquals(OBJETO_NÃO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());

    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandardError> response = exceptionHandler.dataIntegrityViolationException(
                new DataIntegrItyViolationException(EMAIL_JÁ_CADASTRADO),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());

        assertEquals(EMAIL_JÁ_CADASTRADO, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}