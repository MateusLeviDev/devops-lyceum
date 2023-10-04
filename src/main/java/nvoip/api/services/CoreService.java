package nvoip.api.services;

import lombok.RequiredArgsConstructor;
import nvoip.api.domains.Log;
import nvoip.api.domains.LogsType;
import nvoip.api.domains.Mistakes;
import nvoip.api.domains.User;
import nvoip.api.exceptions.BusinessException;
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

    public User findUserByNumbersip(String numbersip) {
        return this.userRepository.findByNumbersip(numbersip)
                .orElseThrow(() -> new BusinessException("Numbersip not found!"));
    }

    public void createMistake(String message) {
        this.mistakesRepository.save(Mistakes.builder()
                .error(message).build());
    }

    public void createLog(String message, int idUser, String ip, String type) {
        LogsType logsType = this.logsTypeRepository.findByType(type)
                .orElseGet(() -> this.logsTypeRepository.save(new LogsType(type)));

        Log log = new Log(idUser, ip, logsType.getId(), message);
    }
}
