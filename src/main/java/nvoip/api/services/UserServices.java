package nvoip.api.services;

import lombok.RequiredArgsConstructor;
import nvoip.api.domains.User;
import nvoip.api.mapper.UserMapper;
import nvoip.api.repositories.UserRepository;
import nvoip.api.requests.UserRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {
    /**
     * torna as dependências explícitas e imutáveis
     */
    private final UserRepository repository;

    public User save(UserRequest request){
        //TODO validations
        return repository.save(UserMapper.INSTANCE.toMapper(request));
    }
}
