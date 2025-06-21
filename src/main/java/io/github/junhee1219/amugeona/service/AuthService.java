package io.github.junhee1219.amugeona.service;

import io.github.junhee1219.amugeona.dto.SignupRequest;
import io.github.junhee1219.amugeona.dto.LoginRequest;
import io.github.junhee1219.amugeona.entity.User;
import io.github.junhee1219.amugeona.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Transactional
	public void signup(SignupRequest request) {
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			throw new IllegalArgumentException("Username already exists");
		}
		User user = new User(
				request.getUsername(),
				passwordEncoder.encode(request.getPassword()),
				Collections.singleton("ROLE_USER")
		);
		userRepository.save(user);
	}

	public void login(LoginRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("Invalid credentials");
		}
		// TODO: generate JWT or set session cookie
	}
}