package fr.spring.service;

import java.util.List;

public interface IBookingService {
	public void book(String... persons);
	public List<String> findAllBookings();
	public String hello();
}
