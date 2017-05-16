package com.recom.services;

import java.util.List;
import javax.annotation.Resource;
import com.recom.utils.DatabaseContextHolder;
import com.recom.utils.DatabaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recom.model.CourseModel;
import com.recom.dao.CourseDAO;

@Service(value = "courseService")
public class CourseService {

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private CourseDAO courseDAO;
	@Autowired
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public CourseService(){
		DatabaseContextHolder.setDatabaseType(DatabaseType.iteach_cernet);
	}

	public List<CourseModel> getCourseByIds(List<Integer> ids){
		return courseDAO.selectCourseByIds(ids);
	}
}