package com.example.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User findUserByEmailAndPassword(String email, String password){
		
		return userRepository.findByEmailAndPassword(email,password);
	}
	
	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id);
	}
	
	@Override
	public List<User> findActiveUser() {
		return  (List<User>)userRepository.findActiveUser();
	}
	
	
	@Override

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setTokenTimeStamp(1L);
        Role userRole1 = roleRepository.findByRole("ADMIN");
        Role userRole2 = roleRepository.findByRole("USER");
        user.setTokenTimeStamp(System.currentTimeMillis());
        if(user.getName()=="admin") {
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole1)));

        }
        else {
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole2)));

        }
		userRepository.save(user);
	}

}
