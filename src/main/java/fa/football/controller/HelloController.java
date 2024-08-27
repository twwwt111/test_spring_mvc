package fa.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HelloController {
	
	@GetMapping({"/home", "/"})
	public String hello() {
		return "helloworld";
	}
	
	@GetMapping("/admin/hello")
	public String hi() {
		return "helloworld";
	}
}
