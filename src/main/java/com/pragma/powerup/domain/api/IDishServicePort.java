package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.request.DishStatusUpdateRequest;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.DishUpdateModel;

public interface IDishServicePort {
    boolean saveDish(DishModel dishModel);
    void updateDish(DishUpdateModel dishUpdateModel);
    boolean updateDishStatus(DishStatusUpdateRequest request);

}
