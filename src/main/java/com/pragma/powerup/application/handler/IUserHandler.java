package com.pragma.powerup.application.handler;


import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);
    ResponseDto<Boolean> saveUser2(UserRequestDto userRequestDto);
}
