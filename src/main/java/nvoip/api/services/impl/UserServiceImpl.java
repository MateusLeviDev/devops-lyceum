package nvoip.api.services.impl;

import lombok.AllArgsConstructor;
import nvoip.api.domains.User;
import nvoip.api.exceptions.BusinessException;
import nvoip.api.exceptions.NotFoundException;
import nvoip.api.mapper.UserMapper;
import nvoip.api.repositories.UserRepository;
import nvoip.api.requests.User.UserPutRequestBody;
import nvoip.api.requests.User.UserPostRequestBody;
import nvoip.api.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Optional<User> createUser(UserPostRequestBody userPostRequest) {
        if (repository.existsByEmail(userPostRequest.getEmail())) {
            throw new BusinessException("Email already registered");
        }
        return Optional.of(repository.save(
                UserMapper.INSTANCE.toMapper(userPostRequest)));
    }

    public Optional<User> findUserById(Integer id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found")));
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void replace(UserPutRequestBody userPutRequestBody) {
        Optional<User> savedUserOptional = repository.findById(userPutRequestBody.getId());

        if (savedUserOptional.isPresent()) {
            User savedUser = savedUserOptional.get();
            User user = UserMapper.INSTANCE.toMapper(userPutRequestBody);
            user.setId(savedUser.getId());
            repository.save(user);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
