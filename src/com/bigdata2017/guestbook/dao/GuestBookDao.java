package com.bigdata2017.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.guestbook.vo.GuestBookVo;

public class GuestBookDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public int delete(GuestBookVo vo) {

		List<GuestBookVo> list = new ArrayList<GuestBookVo>();// 데이터 전부 다 가져오는 거
		Connection conn = null;
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 생성

			// 4.sql문 실행
			// vo.getNumber()
			String sql = "delete from guestbook where no=? and password=?";// mybatis쓰면 쿼리를 xml에 넣어서 불편함을
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNumber());
			pstmt.setString(2, vo.getPassword());
			// 덜 수 있다.
			count = pstmt.executeUpdate();// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		} finally {
			// 자원 정리 ->역순으로 닫아줌

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {

				try {
					conn.close();
				}

				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return count;

	}

	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1. jdbc 드라이버 로딩 (JDBC 클래스를 로딩)->내 db에맞는 드라이버를 로딩
		try {
			conn = getConnection();
			// 3.statement 객체 생성
			stmt = conn.createStatement();

			// 4.sql문 실행
			String sql = "select no,name,content,to_char(reg_date,'yyyy-mm--dd hh:mi:ss') from guestbook order by reg_date desc";// mybatis쓰면
																																	// 쿼리를
																																	// xml에
			// 넣어서 불편함을
			// 덜 수 있다.
			rs = stmt.executeQuery(sql);// select 쿼리는 executeQuery 이고 insert,update,delete는 executeUpdate.

			// 5.결과 가져오기 (사용하기)
			while (rs.next()) {
			int no=rs.getInt(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String date = rs.getString(4);
				GuestBookVo vo = new GuestBookVo();
			vo.setNumber(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setDate(date);

				list.add(vo);
			}

			System.out.println("connection 성공!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		} finally {
			// 자원 정리 ->역순으로 닫아줌

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {

				try {
					conn.close();
				}

				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return list;
	}

	public int insert(GuestBookVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert into guestbook values(seq_guestbook.nextval,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			

			count=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패 :" + e);
		} finally {
			// 자원 정리 ->역순으로 닫아줌

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {

				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return count;

	}

}
