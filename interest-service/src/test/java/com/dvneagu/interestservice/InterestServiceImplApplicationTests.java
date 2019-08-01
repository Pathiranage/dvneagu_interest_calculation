package com.dvneagu.interestservice;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class InterestServiceImplApplicationTests
{

	@Test
	public void contextLoads()
	{
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );

		String dateString1 = "02/06/2017";
		String dateString2 = "11/07/2017";

		//string to date
		LocalDate localDate1 = LocalDate.parse( dateString1, dateTimeFormatter );
		LocalDate localDate2 = LocalDate.parse( dateString2, dateTimeFormatter );

		long betweenCount = ChronoUnit.DAYS.between( localDate1, localDate2 );
		System.out.println( betweenCount * 10000 * ( 0.4 / 1000 ) );

	}

}
