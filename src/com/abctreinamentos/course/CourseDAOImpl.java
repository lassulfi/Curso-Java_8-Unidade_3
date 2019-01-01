package com.abctreinamentos.course;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

	private Connection connection;

	public CourseDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Course course) throws SQLException {
		String sql = "insert into curso values(?,?,?,?)";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, course.getCode());
		pst.setString(2, course.getName());
		pst.setDouble(3, course.getValue());
		pst.setString(4, course.getUrl());
		pst.executeUpdate();

		connection.commit();
	}

	@Override
	public void update(Course course) throws SQLException {
		String sql = "update curso set nome=?, valor=?, url=? where cdcurso=?";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, course.getName());
		pst.setDouble(2, course.getValue());
		pst.setString(3, course.getUrl());
		pst.setInt(4, course.getCode());
		pst.executeUpdate();

		connection.commit();

	}

	@Override
	public void delete(int code) throws SQLException {
		String sql = "delete from curso where cdcurso=?";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, code);
		pst.executeUpdate();

		connection.commit();
	}

	@Override
	public Course read(int code) throws SQLException {
		String sql = "select * from curso where cdcurso=" + code;

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		if (rs.next()) {
			int resultCode = rs.getInt(1);
			String resultName = rs.getString(2);
			double resultValue = rs.getDouble(3);
			String resultURL = rs.getString(4);

			return new Course(resultCode, resultName, resultValue, resultURL);
		}

		return null;
	}

	@Override
	public List<Course> list() throws SQLException {
		String sql = "select * from curso";

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		List<Course> courses = new ArrayList<>();

		while (rs.next()) {
			int code = rs.getInt(1);
			String name = rs.getString(2);
			double value = rs.getDouble(3);
			String url = rs.getString(4);
			courses.add(new Course(code, name, value, url));
		}

		return courses;
	}
}
