package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.domain.Utilities.Constants;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private UserModel userModel = new UserModel();

    @BeforeEach
    void setUp() {
        userModel = new UserModel(1,"name", "lastname", "1080364", "+57314752256", LocalDate.of(2000, 10,10), "new@new.com", "hrjej", new RoleModel(2,"", ""));
    }

    @Test
    void saveUser() {
        userUseCase.saveUser(userModel);

        ResponseDto<Boolean> response = userUseCase.saveUser(userModel);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getData());
        assertNull(response.getErrors());
    }

    @Test
    void saveUserFailed() {
        userModel.setFechaNacimiento(LocalDate.of(2015, 10,10));
        userUseCase.saveUser(userModel);

        ResponseDto<Boolean> response = userUseCase.saveUser(userModel);

        assertEquals(400, response.getStatusCode());
        assertFalse(response.getData());
        assertNotNull(response.getErrors());
    }
}