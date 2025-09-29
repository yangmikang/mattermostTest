package kr.go.distep.member.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class MemberController {

	 /** logger */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

   
	
	@RequestMapping(value="/member/join.do")
	public ModelAndView index(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
	
		ModelAndView view = new ModelAndView();
		
		String referer = (String)request.getHeader("REFERER");
		
		view.addObject("prevURL", referer);
		view.setViewName("member/join");
		
		return view;
	}
	
} 
