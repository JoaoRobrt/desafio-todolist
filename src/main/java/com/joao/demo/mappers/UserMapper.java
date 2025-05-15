package com.joao.demo.mappers;

import com.joao.demo.dto.UserDTO;
import com.joao.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User ToEntity(UserDTO dto);

    UserDTO ToDTO(User entity);

}
