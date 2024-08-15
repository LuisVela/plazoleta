package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishStatusUpdateRequest;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishUpdateRequestMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.DishUpdateModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishUpdateRequestMapper dishUpdateRequestMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toDish(dishRequestDto);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(dishRequestDto.getIdRestaurant());
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(dishRequestDto.getIdCategory());

        dishServicePort.saveDish(dishModel);
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishUpdateRequestDto){
        DishUpdateModel dishUpdateModel = dishUpdateRequestMapper.toDishUpdate(dishUpdateRequestDto);
        dishServicePort.updateDish(dishUpdateModel);
    }

    @Override
    public boolean updateDishStatus(DishStatusUpdateRequest dishStatusUpdateRequestDto){
        return dishServicePort.updateDishStatus(dishStatusUpdateRequestDto);
    }

}
