package nvoip.api.exceptions.Handler;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;
    protected String field;
    protected String fieldsMessage;
}
