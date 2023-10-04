package nvoip.api.exceptions.Handler;

import lombok.extern.log4j.Log4j2;
import nvoip.api.exceptions.NotFoundException;
import nvoip.api.repositories.LogsTypeRepository;
import nvoip.api.repositories.MistakesRepository;
import nvoip.api.repositories.UserRepository;
import nvoip.api.services.CoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends CoreService {

    public RestExceptionHandler(UserRepository userRepository, LogsTypeRepository logsTypeRepository, MistakesRepository mistakesRepository) {
        super(userRepository, logsTypeRepository, mistakesRepository);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> globalExceptionHandler(Exception e, HttpServletRequest request) {

        String path = request.getRequestURI().substring(request.getContextPath().length());
        this.createMistake(path + " - " + e.getMessage());

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Server error")
                        .details("Error Processing Request")
                        .developerMessage(e.getClass().getName())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> objectNotFoundException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining());

        String fieldsMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        String path = request.getRequestURI().substring(request.getContextPath().length());
        this.createMistake(path + " - " + ex.getMessage());

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Invalid field")
                        .details("Check the field(s) error")
                        .developerMessage(ex.getClass().getName())
                        .field(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> objectNotFound(NotFoundException e, HttpServletRequest request) {

        String path = request.getRequestURI().substring(request.getContextPath().length());
        this.createMistake(path + " - " + e.getMessage());

        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not found Exception")
                        .details("object not found")
                        .developerMessage(e.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
