package com.app.service;

import com.app.entity.User;

public interface IUserService {
	public User saveUser(final User user);

	public User loadUserById(final Long id);
}
