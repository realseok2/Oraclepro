package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select에서만 사용됩니다.

//----------------------------------------------------------------------------------------------			필드

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String Id = "phonedb";
	private String pw = "phonedb";

//----------------------------------------------------------------------------------------------			생성자

//----------------------------------------------------------------------------------------------			getter, setter

//----------------------------------------------------------------------------------------------			일반 메소드

	// Connect 얻어오기
	private void getConnect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, Id, pw);
//			System.out.println("접속 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();// select에서만 사용됩니다.
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 사람 추가------------------------------------------------------------------------------------
	public void personInsert(PersonVo personVo) {
		getConnect();

		try {

			String query = "INSERT INTO person VALUES (seq_person_id.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			int count = pstmt.executeUpdate();

			System.out.println(count + "건 처리되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

	}

	// 사람 수정------------------------------------------------------------------------------------
	public void personUpdate(PersonVo personVo) {
		getConnect();

		try {
			String query = "";
			query += " update			person";
			query += " set					name = ?, ";
			query += " 							hp = ?, ";
			query += " 							company = ? ";
			query += " where			person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			int count = pstmt.executeUpdate();

			System.out.println(count + "건 처리되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// 사람 삭제------------------------------------------------------------------------------------
	public void personDelete(int personId) {
		getConnect();

		try {

			String query = "";
			query += " delete from			person ";
			query += " where					person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, personId);

			int count = pstmt.executeUpdate();

			System.out.println(count + "건 처리되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// 사람 검색------------------------------------------------------------------------------------

	public List<PersonVo> personSearch(String search) {
		getConnect();

		List<PersonVo> personList = new ArrayList<PersonVo>();

		try {

			String query = "";
			query += " select		*		from					person ";
			query += " where			name				like				? ";
			query += " or						hp						like				? ";
			query += " or						company			like				? ";

			pstmt = conn.prepareStatement(query);

			String newSearch = '%' + search + '%';
			pstmt.setString(1, newSearch);
			pstmt.setString(2, newSearch);
			pstmt.setString(3, newSearch);

			int count = pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);
				personList.add(personVo);
			}

			System.out.println(count + "건 처리되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return personList;
	}

	// 리스트---------------------------------------------------------------------------------------
	public List<PersonVo> getPersonList() {

		getConnect();

		List<PersonVo> personList = new ArrayList<PersonVo>();

		try {

			String query = "";
			query += " select		person_id,";
			query += " 					name,";
			query += " 					hp,";
			query += " 					company";
			query += " from		person";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);
				personList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
		close();
		return personList;

	}// List

}// class
