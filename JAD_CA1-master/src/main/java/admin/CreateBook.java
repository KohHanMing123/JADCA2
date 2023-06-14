package admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import books.SQLqueryBook;

/**
 * Servlet implementation class CreateBook
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
@WebServlet("/CreateBook")
public class CreateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part imageFile = null;
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String date = request.getParameter("date");
		String genre = request.getParameter("genre");
		String isbn = request.getParameter("isbn");
		String priceStr = request.getParameter("price");
		String stockStr = request.getParameter("stock");
		String desc = request.getParameter("desc");
		if(title == null || author == null || date == null || genre == null || isbn == null || priceStr == null || stockStr == null || desc == null ) {
			response.sendRedirect("admin/newbook.jsp?msg=badreq");
			return;
		}
		double price = 0;
		int stock = 0;
		try {
			price = Double.parseDouble(priceStr);
			stock = Integer.parseInt(stockStr);
		}catch(NumberFormatException e) {
			response.sendRedirect("admin/newbook.jsp?msg=badreq");
			return;
		}
		
		try {
			imageFile = request.getPart("imageFile");
		}catch(Exception e) {
		}
		SQLqueryAdmin query = new SQLqueryAdmin();
		String results = query.createBook(imageFile, title, author, date, genre, isbn, price, stock, desc);
		if(results.equals("Success")) {
			response.sendRedirect("admin/newbook.jsp?msg=success&t=" + title);
		}else {
			response.sendRedirect("admin/newbook.jsp?msg=error");
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
