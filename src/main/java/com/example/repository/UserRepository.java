package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository("userRepository")
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
	 User findById(Long id);
	 @Query(value="select * from user", nativeQuery=true)
	 List<User> findActiveUser();
	 
	 User findByEmailAndPassword(String email,String Password);
	
}
