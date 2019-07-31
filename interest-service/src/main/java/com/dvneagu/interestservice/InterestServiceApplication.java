package com.dvneagu.interestservice;

import com.dvneagu.interestservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.dvneagu.interestservice.repository" })
@EnableTransactionManagement
public class InterestServiceApplication implements CommandLineRunner
{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	public static void main( String[] args )
	{
		SpringApplication.run( InterestServiceApplication.class, args );
	}

	@Override
	public void run( String... args ) throws Exception
	{
		insertData();
	}

	private void insertData()
	{
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript( new ClassPathResource( "data_insert.sql" ) );

		Connection con = null;
		try
		{
			con = dataSource.getConnection();
			rdp.populate( con ); // this starts the script execution, in the order as added
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( con != null )
			{
				try
				{
					con.close();
				}
				catch ( SQLException e )
				{
					e.printStackTrace();
				}
			}
		}
	}
}
