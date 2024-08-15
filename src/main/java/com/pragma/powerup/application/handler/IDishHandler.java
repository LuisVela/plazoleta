package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishStatusUpdateRequest;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto);
    void updateDish(DishUpdateRequestDto dishUpdateRequestDto);
    boolean updateDishStatus(DishStatusUpdateRequest dishStatusUpdateRequestDto);
}
