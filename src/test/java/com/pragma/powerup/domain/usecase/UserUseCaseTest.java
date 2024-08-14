package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IEncryptPort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncryptPort encryptPort;

    @InjectMocks
    private UserUseCase userUseCase;

    private UserModel userModel = new UserModel();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userModel = new UserModel(1,"name", "lastname", "1080364", "+57314752256", LocalDate.of(2000, 10,10), "new@new.com", "encryptedPassword", new RoleModel(2,"", ""));
    }

    @Test
    void saveOwnerSuccess() {
        lenient().when(encryptPort.encodePassword(anyString())).thenReturn("encryptedPassword");

        ResponseDto<Boolean> response = userUseCase.saveUser(userModel);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getData());
        assertNull(response.getErrors());
    }

    @Test
    void saveOwnerFailed() {
        userModel.setFechaNacimiento(LocalDate.of(2015, 10,10));

        ResponseDto<Boolean> response = userUseCase.saveUser(userModel);

        assertEquals(400, response.getStatusCode());
        assertFalse(response.getData());
        assertNotNull(response.getErrors());
    }

    @Test
    void saveEmployeeSuccess() {
        lenient().when(encryptPort.encodePassword(anyString())).thenReturn("encryptedPassword");

        ResponseDto<Boolean> response = userUseCase.saveEmployee(userModel);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getData());
        assertNull(response.getErrors());
    }

    @Test
    void saveEmployeeFailed() {
        userModel.setFechaNacimiento(LocalDate.of(2015, 10,10));

        ResponseDto<Boolean> response = userUseCase.saveEmployee(userModel);

        assertEquals(400, response.getStatusCode());
        assertFalse(response.getData());
        assertNotNull(response.getErrors());
    }
}