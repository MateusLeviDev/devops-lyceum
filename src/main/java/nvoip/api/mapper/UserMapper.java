package nvoip.api.mapper;

import nvoip.api.domains.User;
import nvoip.api.requests.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    /**
     * Mappers.getMapper(UserMapper.class);
     * método fornecido pela biblioteca MapStruct
     * para criar uma instância de uma classe de mapeamento
     */
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toMapper(UserRequest request);
}
