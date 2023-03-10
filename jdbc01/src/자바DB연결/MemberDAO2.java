package 자바DB연결;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MemberDAO2 { // CRUD
	
	// 메서드를 만드는 것 ==> 메서드 정의!(define)
	// 메서드를 정의했다고 실행되는 것은 아니다!
	// 메서드를 쓰는 것 ==> 메서드 호출!(call)
	public int delete(String id) {
		int result = 0;
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
			String sql = "delete from hr.MEMBER where id = ?";
			PreparedStatement ps = con.prepareStatement(sql); 
			ps.setString(1, id);
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			result = ps.executeUpdate();
			if(result == 1) {
				System.out.println("탈퇴 성공.");
			}
			System.out.println("4. SQL문 오라클로 보내기 성공.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(String id, String tel) {
		int result = 0;
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
			String sql = "update hr.MEMBER set tel = ? where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tel);
			ps.setString(2, id);
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			result = ps.executeUpdate();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(result == 1) {
				System.out.println("수정 성공.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insert(String id, String pw, String name, String tel) {
		int result = 0;
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
			String sql = "insert into hr.MEMBER values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			// R이라는 언어 빼고는, 인덱스 0부터 시작!
			// 유일하게 db는 인덱스가 1부터 시작!!!
			ps.setString(1, id); // ps.setInt(1, no);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, tel);
			// ==> insert into hr.MEMBER values ('a', 'a', 'a', 'a');
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			result = ps.executeUpdate(); // 1 (실행한 row 수 return)
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(result == 1) {
				System.out.println("회원가입 성공!");
			}
		} catch (Exception e) {
			// insert가 제대로 안된 경우, 위험한 상황이라고 판단하고
			// catch 부분이 실행
			
			//e.printStackTrace();
		}
		//System.out.println(result);
		return result;
	}
	
}
