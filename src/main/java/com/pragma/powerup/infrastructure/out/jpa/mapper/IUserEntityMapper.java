package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    @Mapping(source = "role", target = "idRol", qualifiedByName = "mapRole")
    UserEntity toEntity(UserModel user);
    @Mapping(source = "idRol", target = "role", qualifiedByName = "mapRoleModel")
    UserModel toUserModel(UserEntity userEntity);
    List<UserModel> toUserModelList(List<UserEntity> userEntities);

    @Named("mapRole")
    default RoleEntity mapIdToRole(RoleModel role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        return roleEntity;
    }

    @Named("mapRoleModel")
    default RoleModel mapIdToRole(RoleEntity role) {
        RoleModel roleEntity = new RoleModel();
        roleEntity.setId(role.getId());
        roleEntity.setNombre(role.getNombre());
        return roleEntity;
    }
}
