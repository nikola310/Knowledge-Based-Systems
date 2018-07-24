package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.converters.UserConverterInterface;
import com.sbnz.doctor.interfaces.services.UserServiceInterface;
import com.sbnz.doctor.model.User;
import com.sbnz.doctor.repository.UserRepository;

/**
 * 
 * @author Nikola
 *
 */
@Service
@Transactional
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserConverterInterface converter;

	@Override
	public UserDTO Create(UserDTO dto) {
		try {

			User entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserDTO> ReadAll() {
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		try {
			for (User entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public UserDTO Update(UserDTO dto) {
		try {
			repository.save(converter.DtoToEntity(dto));
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public UserDTO Delete(long id) {
		User entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		User entity = repository.getUserByUserName(username);
		if (entity == null) {
			throw new IllegalArgumentException("User does not exists: " + username);
		}

		return converter.entityToDto(entity);
	}

}
