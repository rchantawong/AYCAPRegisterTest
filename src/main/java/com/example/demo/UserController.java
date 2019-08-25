package com.example.demo;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@GetMapping("/users")
	public List<User> retrieveAllStudents() {
		System.out.println("retrieveAllStudents");
		return userRepo.findAll();
	}

	@PostMapping(path= "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
		User newUser = user;
		
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(hashedPassword);
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        String refCode = formatter.format(new Date()) + newUser.getPhone().substring(newUser.getPhone().length() - 4);
        newUser.setRefCode(refCode);
        
        newUser.setClassify( Classify.calculate( newUser.getSalary().intValue() ) );
        
        newUser = userRepo.save(user);
         
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(newUser.getId())
                                    .toUri();
         
        return ResponseEntity.created(location).build();
    }
}
