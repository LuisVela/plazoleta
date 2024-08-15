package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishStatusUpdateRequest {
    private Integer dishId;
    private Integer restaurantId;
    private Boolean isActive;
}
