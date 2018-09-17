package fr.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//import org.junit.Test;

import fr.spring.service.IBookingService;

@ContextConfiguration(locations={"classpath:appContext.xml","classpath:applicationContext-test.xml"})
@DirtiesContext
public class AppTest extends AbstractJUnit4SpringContextTests{
	protected static final Logger log = Logger.getLogger(AppTest.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IBookingService bookingService;
	@Autowired
	private SourcePollingChannelAdapter fixedDelayProducer;
	
	@Test
	public void oneMethod() throws Exception{
		log.info("Creating tables");
		jdbcTemplate.execute("drop table BOOKINGS if exists");
		jdbcTemplate.execute("create table BOOKINGS("
				+ "ID serial, FIRST_NAME varchar(5) NOT NULL)");
		
		log.info("Booking Alice in a seat...");
        jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", "Alice");
        log.info("Booking Bob in a seat...");
        jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", "Bob");
        log.info("Booking Carol in a seat...");
        jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", "Carol");
        List<String> l = jdbcTemplate.query("select FIRST_NAME from BOOKINGS", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("FIRST_NAME");
            }
        });
        Assert.assertNotNull(l);
        log.info(l);
        
	}
	@Test
	public void bookingServiceTest(){
		log.info("Creating tables");
		jdbcTemplate.execute("drop table BOOKINGS if exists");
		jdbcTemplate.execute("create table BOOKINGS("
				+ "ID serial, FIRST_NAME varchar(5) NOT NULL)");
		bookingService.book("Alice", "Bob", "Carol");
		Assert.assertEquals("First booking should work with no problem", 3,
				bookingService.findAllBookings().size());
		
		try {
			bookingService.book("Chris", "Samuel");
		}
		catch (RuntimeException e) {
			log.info("v--- The following exception is expect because 'Samuel' is too big for the DB ---v");
			log.error(e.getMessage());
		}

		for (String person : bookingService.findAllBookings()) {
			log.info("So far, " + person + " is booked.");
		}
		log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, and Chris was rolled back in the same TX");
		Assert.assertEquals("'Samuel' should have triggered a rollback", 3,
				bookingService.findAllBookings().size());

		try {
			bookingService.book("Buddy", null);
		}
		catch (RuntimeException e) {
			log.info("v--- The following exception is expect because null is not valid for the DB ---v");
			log.error(e.getMessage());
		}

		for (String person : bookingService.findAllBookings()) {
			log.info("So far, " + person + " is booked.");
		}
		log.info("You shouldn't see Buddy or null. null violated DB constraints, and Buddy was rolled back in the same TX");
		Assert.assertEquals("'null' should have triggered a rollback", 3, bookingService
				.findAllBookings().size());
	}
	
	@Test
	public void PollMessageTest(){
		fixedDelayProducer.start();
		
		fixedDelayProducer.stop();
	}
}
