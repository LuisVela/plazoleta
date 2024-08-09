package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    @Mapping(source = "role", target = "idRol", qualifiedByName = "mapRoleToId")
    UserResponseDto toResponse(UserModel userModel);

    @Named("mapRoleToId")
    default int mapRoleToId(RoleModel role) {
        return role.getId();
    }

    List<UserResponseDto> toResponseList(List<UserModel> userModelList);
}
