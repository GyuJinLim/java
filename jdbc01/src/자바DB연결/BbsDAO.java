package 자바DB연결;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import 화면DB연결.BbsVO;

//테이블 하나당 DAO 하나! ==> CUD를 완성!!!
public class BbsDAO {
	
	public BbsVO one(int no) {
		ResultSet rs = null;
		BbsVO bag = null;
		try {
			// 1.오라클 11g와 연결할 부품 설정, 커넥터 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			// 2.오라클 11g에 연결해보자 (java --- oracle), DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 오라클 연결 성공.");
			
			// String url = "http://www.naver.com";
			// URL u = new URL(url);
			// 자바는 부품 조립식이여서, String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			// 특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			// SQL 부품으로 만들어주어야 함.
			// PreparedStatement가 SQL부품!
			
			// 3. SQL문을 만든다!
			String sql = "select * from hr.BBS where NO = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			rs = ps.executeQuery();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(rs.next()) {
				int no2 = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String writer = rs.getString(4);
				
				System.out.println(	no2 + " " 
						+ title + " " 
						+ content + " " 
						+ writer);
				bag = new BbsVO();
				bag.setNo(no2);
				bag.setTitle(title);
				bag.setContent(content);
				bag.setWriter(writer);
			} else {
				System.out.println("검색결과 없음. 성공.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bag;
	}
	
	public void delete(int no) {
		try {
			// 1.오라클 11g와 연결할 부품 설정, 커넥터 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			// 2.오라클 11g에 연결해보자 (java --- oracle), DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 오라클 연결 성공.");
			
			// String url = "http://www.naver.com";
			// URL u = new URL(url);
			// 자바는 부품 조립식이여서, String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			// 특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			// SQL 부품으로 만들어주어야 함.
			// PreparedStatement가 SQL부품!
			
			// 3. SQL문을 만든다!
			String sql = "delete from hr.BBS where NO = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			ps.executeUpdate();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(int no, String content) {
		try {
			// 1.오라클 11g와 연결할 부품 설정, 커넥터 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			// 2.오라클 11g에 연결해보자 (java --- oracle), DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 오라클 연결 성공.");
			
			// 3. SQL문을 만든다!
			String sql = "update hr.BBS set CONTENT = ? where NO = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, no);
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			ps.executeUpdate();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String title, String content, String writer) {
		try {
			// 1.오라클 11g와 연결할 부품 설정, 커넥터 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			// 2.오라클 11g에 연결해보자 (java --- oracle), DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 오라클 연결 성공.");
			
			// 3. SQL문을 만든다!
			String sql = "insert into hr.BBS values (hr.BBS_ID_SEQ.nextVal, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			// R이라는 언어 빼고는, 인덱스 0부터 시작!
			// 유일하게 db는 인덱스가 1부터 시작!!!
			//ps.setInt(1, no); // ps.setInt(1, no);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, writer);
			// ==> insert into hr.MEMBER values ('a', 'a', 'a', 'a');
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			ps.executeUpdate();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
