package fr.spring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(readOnly = true)
public class BookingServiceImpl implements IBookingService {
	protected static final Logger log = Logger.getLogger(BookingServiceImpl.class);
    JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public void book(String... persons) {
		for (String person : persons) {
            log.info("Booking " + person + " in a seat...");
            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
        }
	}
	@Override
	public List<String> findAllBookings() {
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("FIRST_NAME");
            }
        });
    }
	@Override
	public String hello() {
		log.info("hello");
		return "Hello fredo";
	}

}
