package nvoip.api.services;

import lombok.RequiredArgsConstructor;
import nvoip.api.repositories.LogsTypeRepository;
import nvoip.api.repositories.MistakesRepository;
import nvoip.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreService {
    private final UserRepository userRepository;
    private final LogsTypeRepository logsTypeRepository;
    private final MistakesRepository mistakesRepository;

    public boolean checkUser(String numbersip) {
        return this.userRepository.findByNumbersip(numbersip).isPresent();
    }
}
