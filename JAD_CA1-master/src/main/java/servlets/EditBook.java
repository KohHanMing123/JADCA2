package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.*;
/**
 * Servlet implementation class EditBook
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteButton = request.getParameter("deleteButton");
		String submitButton = request.getParameter("submitButton");
		//Initializing variables
		Part imageFile = null;
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String date = request.getParameter("date");
		String genre = request.getParameter("genre");
		String isbn = request.getParameter("isbn");
		String priceStr = request.getParameter("price");
		String stockStr = request.getParameter("stock");
		String desc = request.getParameter("desc");
		String idStr = request.getParameter("bookID");
		String path = "admin/book.jsp?id=" + idStr + "&msg=badreq";
		if(title == null || author == null || date == null || genre == null || isbn == null || priceStr == null || stockStr == null || desc == null || idStr == null) {
			System.out.println(idStr);
			response.sendRedirect(path);
			return;
		}
		double price = 0;
		int stock = 0;
		int id = 0;
		try {
			price = Double.parseDouble(priceStr);
			stock = Integer.parseInt(stockStr);
			id = Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			response.sendRedirect(path);
			return;
		}
		
		try {
			imageFile = request.getPart("imageFile");
		}catch(Exception e) {
		}
		AdminDAO query = new AdminDAO();
		//Logic to delete or edit
		if(submitButton != null) {
			String results = query.editBook(id, imageFile, title, author, date, genre, isbn, price, stock, desc);
			System.out.println(results);
			if(results.equals("Success")) {
				path = "admin/book.jsp?id=" + idStr + "&msg=Success";
				response.sendRedirect(path);
				return;
			}else if(results.equals("imageSizeErr")) {
				path = "admin/book.jsp?id=" + idStr + "&msg=imageSizeErr";
				response.sendRedirect(path);
				return;
			}else {
				path = "admin/book.jsp?id=" + idStr + "&msg=error";
				response.sendRedirect(path);
				return;
			}
		}
		else if(deleteButton != null) {
			String results = query.deleteBook(id);
			if(results.equals("Success")) {
				response.sendRedirect("admin/books.jsp");
				return;
			}else {
				path = "admin/book.jsp?id=" + idStr + "&msg=error";
				response.sendRedirect(path);
				return;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
