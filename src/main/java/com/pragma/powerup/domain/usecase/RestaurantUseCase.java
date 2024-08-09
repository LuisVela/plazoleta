package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistentPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistentPort restaurantPersistentPort;

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        restaurantPersistentPort.saveRestaurant(restaurantModel);
    }
}
