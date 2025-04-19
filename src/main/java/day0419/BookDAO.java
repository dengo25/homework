package day0419;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;

import common.ConnectionProvider;

public class BookDAO {
	
	
	public List<BookVO> list(String date, String publisher){
		ArrayList<BookVO> lists = new ArrayList();
	    String sql = "select b.bookid,b.bookname,b.price,b.publisher from book b, orders o where b.bookid = o.bookid "
	               + " and orderdate = ? "
	               + " and b.publisher = ? ";
		
//		System.out.println(date);
//		System.out.println(publisher);
//		System.out.println("Executing query: " + sql);
		LocalDate now = LocalDate.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		String formatedNow = now.format(formatter);
//		System.out.println(formatedNow);
		
		Connection conn =  ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareCall(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, publisher);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				BookVO book=new BookVO();
				book.setBookId(rs.getInt("BOOKID"));
				book.setBookName(rs.getString("BOOKNAME"));
				book.setPrice(rs.getInt("PRICE"));
				book.setPublisher(rs.getString("PUBLISHER"));
				lists.add(book);
				
			}
			
			ConnectionProvider.close(conn,pstmt,rs);
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return lists;
	}
}
