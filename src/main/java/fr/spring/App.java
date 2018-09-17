package fr.spring;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.spring.service.IBookingService;

/**
 * bookingService
 *
 */
public class App {
	protected static final Logger log = Logger.getLogger(App.class);
	

	public static void main( String[] args ){
    	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    	IBookingService bookingService = (IBookingService) context.getBean("bookingService");
    	jdbcTemplate.execute("drop table BOOKINGS if exists");
		jdbcTemplate.execute("create table BOOKINGS("
				+ "ID serial, FIRST_NAME varchar(5) NOT NULL)");
		/*
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
        log.info(l);
        
        log.info("Booking Samuel in a seat...");
        try{
        	jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", "Samuel");
        }catch(RuntimeException e){
        	log.info("v--- The following exception is expect because null is not valid for the DB ---v");
			log.error(e.getMessage());
        }
        l = jdbcTemplate.query("select FIRST_NAME from BOOKINGS", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("FIRST_NAME");
            }
        });
        log.info(l);*/
		
		bookingService.book("Alice", "Bob", "Carol");
		for (String person : bookingService.findAllBookings()) {
			log.info("So far, " + person + " is booked.");
		}
		
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
        //context.close();
    }
}
