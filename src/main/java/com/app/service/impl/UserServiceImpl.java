package com.app.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.exception.UserAlreadyExistException;
import com.app.repo.IUserRepository;
import com.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setConfirmPassword(null);
			user.setCreatedAt(LocalDateTime.now());
			return userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserAlreadyExistException("Username " + user.getUsername() + " Already Exists");
		}
	}

	@Override
	public UserDetails loadUserByUsername(final String username) {
		final Optional<User> OptionalUser = userRepository.findByUsername(username);
		if (!OptionalUser.isPresent())
			throw new UsernameNotFoundException("Username not found");
		User user = OptionalUser.get();
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
	}

	@Override
	public User loadUserById(final Long id) {
		final Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with this id"));
	}

}
