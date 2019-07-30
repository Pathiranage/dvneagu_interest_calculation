package com.dvneagu.interestservice;

import com.dvneagu.interestservice.entity.User;
import com.dvneagu.interestservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.dvneagu.interestservice.repository" })
@EnableTransactionManagement
public class InterestServiceApplication implements CommandLineRunner
{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main( String[] args )
	{
		SpringApplication.run( InterestServiceApplication.class, args );
	}

	@Override
	public void run( String... args ) throws Exception
	{
		createAdmin();
	}

	void createAdmin()
	{
		User user = new User();
		user.setUserName( "admin" );
		user.setPassword( this.passwordEncoder.encode( "admin" ) );
		user.setUserType( "ROLE_ADMIN" );
		userRepository.save( user );
	}

}
