package com.filename.extension.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.filename.extension.main.domain.MainDomain;

public class MainDao {
	private JdbcTemplate jdbcTemplate;
	
//	JDBC Data
	public MainDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//	매개변수를 통해 고정확장자 , 추가확장자의 데이터를 얻을 수 있다. checkBox를 통해 고정확장자의 체크버튼 유지
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
	
//	고정 확장자 체크박스 체크 유무를 따져 true false 값을 넣는다.
	public void updateCheckBoxByextension(boolean check, String extension) {
		String sql = "UPDATE Extension SET checkBox = ? WHERE  extension = ?";
		jdbcTemplate.update(sql,check,extension);
	}
	
//	커스텀확장자 추가
	public void addExtension(String extension){
		String sql = "INSERT INTO Extension(extension) VALUES (?)";
		jdbcTemplate.update(sql,extension);
	}
	
//	커스텀확장자 삭제
	public void deleteExtension(String extension) {
		String sql = "DELETE FROM Extension WHERE extension = ?";
		jdbcTemplate.update(sql,extension);
	}
	
//	커스텀확장자 개수 출력
	public int getCountBydefaultCheckFalse() {
		String sql = "SELECT COUNT(*) FROM Extension WHERE defaultCheck = false";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
			
		});
	}
	
//	동일 확장자 검사
	public boolean extensionDuplicatedCheck(String extension) {
		try {
			String sql = "SELECT extension FROM Extension WHERE extension = ?";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Boolean>() {

				@Override
				public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
					return true;
				}
				
			},extension);
		} catch (EmptyResultDataAccessException e) { // duplicate exception 예외처리하여 false값을 리턴
			return false;
		}
	}
	
	public void deleteAllCustomExtension() {
		String sql = "DELETE FROM Extension WHERE defaultCheck = false";
		jdbcTemplate.update(sql);
	}
	
}
