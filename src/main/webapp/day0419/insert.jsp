<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="day0419.BookDAO, day0419.BookVO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
    request.setCharacterEncoding("UTF-8");
 
        String publisher = request.getParameter("publisher");
        String date = request.getParameter("date");
        System.out.println("Date: " + date);
        System.out.println("Publisher: " + publisher);
        if (date != null && publisher != null) {

            BookDAO dao = new BookDAO();
            

            List<BookVO> bookList = dao.list(date, publisher);
            System.out.println("bookList: " + bookList);

            if (bookList != null && !bookList.isEmpty()) {
                for (BookVO book : bookList) {
    %>
                    <p>책 ID: <%= book.getBookId() %></p>
                    <p>책 이름: <%= book.getBookName() %></p>
                    <p>가격: <%= book.getPrice() %></p>
                    <p>출판사: <%= book.getPublisher() %></p>
                    <hr>
    <%
                }
            } else {
    %>
                <p>해당하는 책이 없습니다.</p>
    <%
            }
        }
    %>
</body>
</html>