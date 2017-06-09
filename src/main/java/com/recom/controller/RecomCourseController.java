package com.recom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.recom.model.CourseModel;
import com.recom.services.RecomCourseService;
import com.recom.web.json.JsonAndView;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecomCourseController {
	@Autowired
	private RecomCourseService recomCourseService;
	
	@RequestMapping(value="/recom", method = RequestMethod.POST)
	@ResponseBody
	public JsonAndView courseReom(HttpServletRequest request,
								  HttpServletResponse respons,
								  @RequestParam(value = "userId", required = false)Integer userId){
		/*
		* userId从前端传过来
		* */
		JsonAndView jv = new JsonAndView();
		if(userId != 0){
         	//手动调用推荐算法
         	 //recomCourseService.recomScheduled();
        	 List<CourseModel> recompredic = recomCourseService.getUserRecomCourse(userId);
        	 if(recompredic.size() != 0){
				 List<CourseModel> recom = recomCourseService.getUserRecomCourse(userId);
				 jv.addData( "result", recom);
			 }else {
				 List<CourseModel> recom = recomCourseService.getIndexCourseRecom();
				 jv.addData("result", recom);
			 }

         }else{
        	 List<CourseModel> recom = recomCourseService.getIndexCourseRecom();
        	 jv.addData("result", recom);
         }
		return jv;
	}
	
	/*@RequestMapping(value="/rate",method = RequestMethod.POST)
	public JsonAndView rate(HttpServletRequest request,HttpServletResponse respons,
			@RequestParam(value="courseId",required=true) int courseId,@RequestParam(value="rate",required=true) double rate){
		JsonAndView jv = new JsonAndView();
		 HttpSession session = request.getSession();
         Integer userId=(Integer)(session.getAttribute("userId"));
         if(userId!=null){
        	 int id = recomCourseService.insertUserRate(userId, courseId, rate);
        	 jv.addData("result", id);
         }else{
        	 jv.setRet(false);
        	 jv.setErrcode(404);
        	 jv.setErrmsg("当前userId没找到");
         }
		return jv;
	}*/
}
