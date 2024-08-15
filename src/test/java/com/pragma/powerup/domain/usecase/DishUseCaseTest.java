package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.DishStatusUpdateRequest;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.DishUpdateModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDish() {
        DishModel dishModel = new DishModel();
        dishModel.setId(1);
        dishModel.setNombre("Test Dish");

        when(dishPersistencePort.saveDish(dishModel)).thenReturn(dishModel);

        boolean result = dishUseCase.saveDish(dishModel);

        assertTrue(result);
    }

    @Test
    void testUpdateDish_UpdateDescripcion() {
        DishUpdateModel dishUpdateModel = new DishUpdateModel();
        dishUpdateModel.setId(1);
        dishUpdateModel.setDescripcion("New Description");

        DishModel existingDishModel = new DishModel();
        existingDishModel.setId(1);
        existingDishModel.setDescripcion("Old Description");
        existingDishModel.setPrecio(10.0);

        // Cambiar el uso de when(...) a doReturn(...) para evitar problemas
        doReturn(existingDishModel).when(dishPersistencePort.getDishById(anyInt()));

        dishUseCase.updateDish(dishUpdateModel);

        verify(dishPersistencePort, times(1)).saveDish(existingDishModel);
        assertEquals("New Description", existingDishModel.getDescripcion());
        assertEquals(10.0, existingDishModel.getPrecio());
    }

    @Test
    void testUpdateDish_UpdatePrecio() {
        DishUpdateModel dishUpdateModel = new DishUpdateModel();
        dishUpdateModel.setId(1);
        dishUpdateModel.setPrecio(20.0);

        DishModel existingDishModel = new DishModel();
        existingDishModel.setId(1);
        existingDishModel.setDescripcion("Some Description");
        existingDishModel.setPrecio(10.0);

        when(dishPersistencePort.getDishById(1)).thenReturn(existingDishModel);

        dishUseCase.updateDish(dishUpdateModel);

        verify(dishPersistencePort, times(1)).saveDish(existingDishModel);
        assertEquals("Some Description", existingDishModel.getDescripcion());
        assertEquals(20.0, existingDishModel.getPrecio());
    }

    @Test
    void testUpdateDish_NoChanges() {
        DishUpdateModel dishUpdateModel = new DishUpdateModel();
        dishUpdateModel.setId(1);

        DishModel existingDishModel = new DishModel();
        existingDishModel.setId(1);
        existingDishModel.setDescripcion("Existing Description");
        existingDishModel.setPrecio(10.0);

        lenient().when(dishPersistencePort.getDishById(1)).thenReturn(existingDishModel);

        dishUseCase.updateDish(dishUpdateModel);

        verify(dishPersistencePort, times(1)).saveDish(existingDishModel);
        assertEquals("Existing Description", existingDishModel.getDescripcion());
        assertEquals(10.0, existingDishModel.getPrecio());
    }

    void updateDishStatus_Success() {
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1);

        DishModel existingDish = new DishModel();
        existingDish.setId(1);
        existingDish.setActivo((byte) 1);
        existingDish.setRestaurant(restaurantModel); // Asumiendo que RestaurantModel tiene un constructor que toma el ID

        when(dishPersistencePort.getDishById(1)).thenReturn(existingDish);

        DishStatusUpdateRequest request = new DishStatusUpdateRequest();
        request.setDishId(1);
        request.setRestaurantId(1);
        request.setIsActive(false);

        boolean result = dishUseCase.updateDishStatus(request);

        assertTrue(result);
        assertEquals((byte) 0, existingDish.getActivo());
        verify(dishPersistencePort, times(1)).saveDish(existingDish);
    }

    @Test
    void updateDishStatus_Fail_WrongRestaurant() {
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(2);

        DishModel existingDish = new DishModel();
        existingDish.setId(1);
        existingDish.setActivo((byte) 1);
        existingDish.setRestaurant(restaurantModel); // Plato pertenece a otro restaurante

        when(dishPersistencePort.getDishById(1)).thenReturn(existingDish);

        DishStatusUpdateRequest request = new DishStatusUpdateRequest();
        request.setDishId(1);
        request.setRestaurantId(1);
        request.setIsActive(false);

        assertThrows(IllegalArgumentException.class, () -> {
            dishUseCase.updateDishStatus(request);
        });
    }
}