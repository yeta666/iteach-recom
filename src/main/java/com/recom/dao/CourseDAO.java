package com.recom.dao;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.recom.model.CourseModel;

/**
 * 
 * 课程相关查询<br>
 * 
 * @author EasonLian YangZQ
 * 
 */
@Repository("courseDao")
public class CourseDAO {

	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<CourseModel> selectCourseByIds(List<Integer> ids) {
		return sqlSession.selectList("course.selectCourseInfoByIds", ids);
	}
}
