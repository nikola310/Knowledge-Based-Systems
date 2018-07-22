package com.sbnz.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.UserDTO;
import com.sbnz.interfaces.converters.UserConverterInterface;
import com.sbnz.model.User;

@Component
@Transactional
public class UserConverter implements UserConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public UserDTO entityToDto(User entity) {
		UserDTO dto;

		try {
			dto = mapper.map(entity, UserDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public User DtoToEntity(UserDTO dto) {
		User entity;

		try {
			entity = mapper.map(dto, User.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
