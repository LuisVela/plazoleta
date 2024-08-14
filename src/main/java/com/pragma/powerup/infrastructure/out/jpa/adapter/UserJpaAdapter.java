package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        UserEntity userEntity = userEntityMapper.toEntity(userModel);
        userEntity= userRepository.save(userEntity);
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel getUserByEmail(String email) throws UsernameNotFoundException {
        UserEntity userEntity= userRepository.findByCorreo(email);
        return userEntityMapper.toUserModel(userEntity);
    }
}
