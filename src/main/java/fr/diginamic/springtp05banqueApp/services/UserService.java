package fr.diginamic.springtp05banqueApp.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.diginamic.springtp05banqueApp.model.User;
import fr.diginamic.springtp05banqueApp.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Objects.requireNonNull(username);
		User user = userRepository.findUserWithName(username)
				.orElseThrow( () -> new UsernameNotFoundException("user not found") );
			
		return user;
	}

}
