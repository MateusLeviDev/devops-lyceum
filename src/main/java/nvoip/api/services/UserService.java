package nvoip.api.services;

import nvoip.api.domains.User;
import nvoip.api.requests.User.UserPutRequestBody;
import nvoip.api.requests.User.UserPostRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Page<User> findAllUsers(Pageable pageable);
    void replace(UserPutRequestBody userPutRequest);
    void deleteUser(Integer id);
}
