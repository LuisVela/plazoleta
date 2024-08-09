package com.pragma.powerup.infrastructure.out.encrypt;
import com.pragma.powerup.domain.spi.IEncryptPort;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class EncryptAdapter implements IEncryptPort {

    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encodePassword(String password) {
        return password;
        //return bCryptPasswordEncoder.encode(password);
    }
}
