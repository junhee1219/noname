package io.github.junhee1219.amugeona.controller;

import io.github.junhee1219.amugeona.dto.SignupRequest;
import io.github.junhee1219.amugeona.dto.LoginRequest;
import io.github.junhee1219.amugeona.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	public AuthController(AuthService authService) { this.authService = authService; }

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) {
		authService.signup(request);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
		authService.login(request);
		return ResponseEntity.ok("Login successful");
	}
}
