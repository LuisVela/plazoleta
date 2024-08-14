package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.domain.Utilities.Constants;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IEncryptPort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@AllArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IEncryptPort encryptPort;

    @Override
    public ResponseDto<Boolean> saveUser(UserModel userModel) {
        ResponseDto<Boolean> BAD_REQUEST = getBooleanResponseDto(userModel, Constants.ID_ROLE_OWNER);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        return new ResponseDto<>(HttpStatus.OK.value(), true, null);
    }

    @Override
    public ResponseDto<Boolean> saveEmployee(UserModel userModel) {
        ResponseDto<Boolean> BAD_REQUEST = getBooleanResponseDto(userModel, Constants.ID_ROLE_EMPLOYEE);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        return new ResponseDto<>(HttpStatus.OK.value(), true, null);
    }

    private ResponseDto<Boolean> getBooleanResponseDto(UserModel userModel, int role) {
        if (isAdult(userModel.getFechaNacimiento())) {
            //ControllerAdvisor
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), false, Collections.singletonList(Constants.YOUNGER));
        }
        String encryptedPassword = encryptPort.encodePassword(userModel.getClave());
        RoleModel roleModel = new RoleModel();
        roleModel.setId(role);
        userModel.setRole(roleModel);
        userModel.setClave(encryptedPassword);
        userPersistencePort.saveUser(userModel);

        return null;
    }

    private boolean isAdult(LocalDate birthDate) {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now()) < 18;
    }
}
