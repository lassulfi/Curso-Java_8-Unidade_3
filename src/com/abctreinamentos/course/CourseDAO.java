package com.abctreinamentos.course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {

	public void create(Course course) throws SQLException;
	public void update(Course course) throws SQLException;
	public void delete(int code) throws SQLException;
	public Course read(int code) throws SQLException;
	public List<Course> list() throws SQLException;
}
