package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.DishStatusUpdateRequest;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.DishUpdateModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    @Override
    public boolean saveDish(DishModel dishModel) {
        DishModel dish = dishPersistencePort.saveDish(dishModel);
        return (dish != null);
    }

    @Override
    public void updateDish(DishUpdateModel dishUpdateModel) {
        DishModel dishModel = dishPersistencePort.getDishById(dishUpdateModel.getId());
        int res = dishUpdateModel.getId();

        if (dishUpdateModel.getDescripcion() != null) {
            dishModel.setDescripcion(dishUpdateModel.getDescripcion());
        }
        if (dishUpdateModel.getPrecio() > 0){
            dishModel.setPrecio(dishUpdateModel.getPrecio());
        }
        dishPersistencePort.saveDish(dishModel);
    }

    public boolean updateDishStatus(DishStatusUpdateRequest request) {
        DishModel dishModel = dishPersistencePort.getDishById(request.getDishId());

        if (dishModel.getRestaurant().getId() != request.getRestaurantId()) {
            throw new IllegalArgumentException("El plato no pertenece al restaurante especificado.");
        }

        dishModel.setActivo(request.getIsActive() ? (byte) 1 : (byte) 0);
        dishPersistencePort.saveDish(dishModel);

        return true;
    }
}
