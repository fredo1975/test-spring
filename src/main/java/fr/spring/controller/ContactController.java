package fr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {
	@RequestMapping("/contact/{id}")
    @ResponseBody
    public Contact contact(@PathVariable("id") Integer id) {
        return new Contact(id,"Test Firstname","Test Lastname");
    }
}
