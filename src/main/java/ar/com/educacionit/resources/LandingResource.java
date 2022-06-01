package ar.com.educacionit.resources;

import org.springframework.web.bind.annotation.RequestMapping;

/*
 * redirect a la home de swagger
 */
public class LandingResource {

	@RequestMapping(value="/")
	public String swaggerUi() {
		return "redirect:/swager-ui/";
	}
}
