package nvoip.api.services;

import lombok.RequiredArgsConstructor;
import nvoip.api.domains.Log;
import nvoip.api.domains.LogsType;
import nvoip.api.domains.Mistakes;
import nvoip.api.domains.User;
import nvoip.api.exceptions.ElementNotFoundException;
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
                .orElseThrow(() -> new ElementNotFoundException("Numbersip not found!"));
    }

    public void createMistake(Integer id, String error) {
        Mistakes mistakes = new Mistakes(id, error);
        this.mistakesRepository.save(mistakes);
    }

    public void createLog(String message, int idUser, String ip, String type) {
        LogsType logsType = this.logsTypeRepository.findByType(type)
                .orElseGet(() -> this.logsTypeRepository.save(new LogsType(type)));

        Log log = new Log(idUser, ip, logsType.getId(), message);
    }
}
