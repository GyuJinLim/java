package 자바DB연결;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import 화면DB연결.MemberVO;

public class MemberDAO3 { // CRUD
	
	// 메서드를 만드는 것 ==> 메서드 정의!(define)
	// 메서드를 정의했다고 실행되는 것은 아니다!
	// 메서드를 쓰는 것 ==> 메서드 호출!(call)
	
	public ArrayList<MemberVO> list() {
		ResultSet rs = null; // 항목명 + 결과 데이터를 담고 있는 테이블
		
		// 가방들 넣어줄 큰 컨테이너 역할의 부품이 필요!
		// ArrayList<MemberVO> ==> MemberVO만 들어간 arraylist라는 의미
		ArrayList<MemberVO> list = new ArrayList<>();
		
		MemberVO bag = null;
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
			String sql = "select * from hr.MEMBER";
			PreparedStatement ps = con.prepareStatement(sql); 
			// 삭제! ps.setString(1, id);			
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			rs = ps.executeQuery(); // select문 전송시 executeQuery!
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			
			while(rs.next()) { // 검색결과가 있는지 여부는 rs.next() 
				// rs.next() -> 있으면 true, 없으면 false
				// 1. 검색 결과가 있으면,
				// System.out.println("검색 결과 있음. 성공.");
				// 2. 각 컬럼에서 값들을 꺼내오자.
				String id2 = rs.getString(1); // id
				String pw = rs.getString(2); // pw
				String name = rs.getString(3); // name
				String tel = rs.getString(4); // tel
				// System.out.println(id2 + " " + pw + " " + name + " " + tel);
				// 검색결과를 검색화면 UI부분으로 주어야 함.
				// 3. 가방을 만들자.
				bag = new MemberVO();
				bag.setId(id2);
				bag.setPw(pw);
				bag.setName(name);
				bag.setTel(tel);
				// 4. list에 bag을 추가해주자.
				list.add(bag);
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return id, pw, name, tel은 안된다!
		// return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야 함.
		// 검색결과가 있을 때는 bag에 데이터가 들어있음.
		// 검색결과가 없을 때는 bag에 무엇이 들어있나? null
		return list;
	}
	
	public int login(MemberVO bag) {
		int result = 0;
		ResultSet rs = null;
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
			String sql = "select * from hr.MEMBER where id = ? and pw = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bag.getId());
			ps.setString(2, bag.getPw());
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			rs = ps.executeQuery();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(rs.next()) {
				result = 1;
				System.out.println("로그인 성공.");
			} else {
				System.out.println("로그인 실패.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public MemberVO one(String id) {
		ResultSet rs = null; // 항목명 + 결과 데이터를 담고 있는 테이블
		// 기본형 정수/실수/문자/논리만 값으로 초기화
		// 나머지 데이터형(참조형) 주소가! 들어가 있음.
		// 참조형 변수를 초기화할 때는 null(주소가 없다라는 의미)
		MemberVO bag = null;
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
			String sql = "select * from hr.MEMBER where id = ?";
			PreparedStatement ps = con.prepareStatement(sql); 
			ps.setString(1, id);
			// con 부품으로 sql스트링에 있는 것을 SQL 부품으로 만든다.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기.");
			
			// 4. SQL문을 oracle 프로그램에 전송.
			rs = ps.executeQuery(); // select문 전송시 executeQuery!
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(rs.next()) { // 검색결과가 있는지 여부는 rs.next() 
				// rs.next() -> 있으면 true, 없으면 false
				System.out.println("검색 결과 있음. 성공.");
				String id2 = rs.getString(1); // id
				String pw = rs.getString(2); // pw
				String name = rs.getString(3); // name
				String tel = rs.getString(4); // tel
				System.out.println(id2 + " " + pw + " " + name + " " + tel);
				// 검색결과를 검색화면 UI부분으로 주어야 함.
				bag = new MemberVO();
				bag.setId(id2);
				bag.setPw(pw);
				bag.setName(name);
				bag.setTel(tel);
			} else {
				System.out.println("검색 결과 없음. 성공.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return id, pw, name, tel은 안된다!
		// return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야 함.
		// 검색결과가 있을 때는 bag에 데이터가 들어있음.
		// 검색결과가 없을 때는 bag에 무엇이 들어있나? null
		return bag;
	}
	
	public int delete(MemberVO bag) {
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
			ps.setString(1, bag.getId());
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
	
	// 1. 가방을 받아서 저장해두자.
	// 2. 필요할 때 가방에서 값들을 하나씩 꺼내자.
	public int update(MemberVO bag) {
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
			ps.setString(1, bag.getTel());
			ps.setString(2, bag.getId());
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
	
	public int insert(MemberVO bag) {
		// 1. 가방을 받아서 변수에 넣어주세요.
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
			ps.setString(1, bag.getId()); // ps.setInt(1, no);
			ps.setString(2, bag.getPw());
			ps.setString(3, bag.getName());
			ps.setString(4, bag.getTel());
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
