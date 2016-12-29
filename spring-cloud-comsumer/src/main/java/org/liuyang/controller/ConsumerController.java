package org.liuyang.controller;

import org.liuyang.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConsumerController {
	
	/*@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String add() {
		return restTemplate.getForEntity("http://cloud-client/hello", String.class).getBody();
	}*/
	
	@Autowired
	private MyService myService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return myService.hello();
	}
}
