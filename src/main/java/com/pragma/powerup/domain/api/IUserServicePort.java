package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.domain.model.UserModel;

public interface IUserServicePort {
    ResponseDto<Boolean> saveUser(UserModel userModel);
    ResponseDto<Boolean> saveEmployee(UserModel userModel);
}
