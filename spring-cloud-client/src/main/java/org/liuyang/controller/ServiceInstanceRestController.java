package org.liuyang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceInstanceRestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@RequestMapping("/hello")
	public String sayhello() {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		logger.info("/hello [host:{}, port:{}, serviceId:{}]", instance.getHost(), instance.getPort(),
				instance.getServiceId());
		return "hello";
	}

}
