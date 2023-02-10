package com.filename.extension.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.filename.extension.main.domain.MainDomain;

public class MainDao {
	private JdbcTemplate jdbcTemplate;
	
//	JDBC Data
	public MainDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<MainDomain> getExtensionByDefaultCheck(boolean check){
		String sql = "SELECT extension,checkBox FROM Extension WHERE defaultCheck= ?";
		return jdbcTemplate.query(sql, new RowMapper<MainDomain>() {

			@Override
			public MainDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
				MainDomain mainDomain = new MainDomain();
				mainDomain.setExtension(rs.getString("extension"));
				mainDomain.setCheckBox(rs.getBoolean("checkBox"));
				return mainDomain;
			}
			
		},check);
	}
	
	public void updateCheckBoxByextension(boolean check, String extension) {
		String sql = "UPDATE Extension SET checkBox = ? WHERE  extension = ?";
		jdbcTemplate.update(sql,check,extension);
	}
	
	public void addExtension(String extension) {
		String sql = "INSERT INTO Extension(extension) VALUES (?)";
		jdbcTemplate.update(sql,extension);
	}
	
	public void deleteExtension(String extension) {
		String sql = "DELETE FROM Extension WHERE extension = ?";
		jdbcTemplate.update(sql,extension);
	}
	
	public int getCountBydefaultCheckFalse() {
		String sql = "SELECT COUNT(*) FROM Extension WHERE defaultCheck = false";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
			
		});
	}
}
