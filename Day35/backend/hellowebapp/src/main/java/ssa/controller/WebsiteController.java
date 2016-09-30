package ssa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebsiteController {

	@RequestMapping("/")
    public ModelAndView home(HttpServletRequest request, ModelAndView mv) {
        if(request.getParameter("name") != null){
            mv.addObject("name", request.getParameter("name"));
        }
        mv.setViewName("home");
        return mv;
    }
	
	@RequestMapping("/about")
    public ModelAndView about(HttpServletRequest request, ModelAndView mv) {
//        if(request.getParameter("name") != null){
//            mv.addObject("name", request.getParameter("name"));
//        }
        mv.setViewName("about");
        return mv;
    }	
	
	@RequestMapping("/help")
    public ModelAndView help(HttpServletRequest request, ModelAndView mv) {
		String[] messageDesc = {"msg description 0.", "msg description 1.", "msg description 2.", "msg description 3.", "msg description 4.", "msg description 5."};
		
        if(request.getParameter("id") != null){
        	mv.addObject("id", request.getParameter("id"));
            mv.addObject("desc", messageDesc[(Integer.parseInt(request.getParameter("id")))]);
//            mv.addObject("desc", request.getParameter("desc"));
        }
        mv.setViewName("help");
        return mv;
    }	
	

	
}
