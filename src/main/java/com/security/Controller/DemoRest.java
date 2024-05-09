package com.security.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * DemoRest
 */

@RestController
@RequestMapping("security")
public class DemoRest {

	@GetMapping("demo")
	public String demoSecurity() {
		return new String("message from securitys");
	}
	
	@GetMapping("tournament")
	public String getTournament() {
		return new String("Tournament Controller");
	}
	@GetMapping("teams")
	public String getTeams() {
		return new String("Teams Controller");
	}
	@GetMapping("players")
	public String getplayers() {
		return new String("players Controller");
	}
	

}

