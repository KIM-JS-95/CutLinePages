package com.myGallary.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorControler implements ErrorController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private final String DEFAULT_ERROR_PATH = "error";


	@Override
	public String getErrorPath() {
		return DEFAULT_ERROR_PATH;
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String errorHandle(HttpServletRequest request, Model model) {
		return errorHandleImpl(request, model);
	}


	@RequestMapping(value = "/access-denied",  method = RequestMethod.GET)
	public String accessDenied(Model model) {
		//< set the attributes
		model.addAttribute("errorCode", "403");
		model.addAttribute("errorMessage", "Forbidden");

		return getErrorPath() + "/404error";
	}

	private String errorHandleImpl(HttpServletRequest request, Model model) {
		//< get the status code
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		//< get the HTTP status
		String state = status.toString();
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(state));
		//< set the attributes
		model.addAttribute("errorCode", state);
		model.addAttribute("errorMessage", httpStatus.getReasonPhrase());

		return getErrorPath() + "/404error";
	}
}





