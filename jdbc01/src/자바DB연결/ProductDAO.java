package 자바DB연결;

import 화면DB연결.ProductVO;

public class ProductDAO {
	// CRUD 기능 메서드를 만들어준다.
	public void insert(ProductVO bag) {
		// 1. 가방이 전달되면 저장
		// 2. 필요할 때 가방에서 데이터를 하나씩 꺼내주세요.
		System.out.println("전달된 id는 " + bag.getId());
		System.out.println("전달된 name는 " + bag.getName());
		System.out.println("전달된 content는 " + bag.getContent());
	}
}
