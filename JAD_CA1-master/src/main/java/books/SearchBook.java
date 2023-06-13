package books;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBook
 */
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath() + "/Pages/search.jsp?q=" + request.getParameter("searchInput");
		if(!request.getParameter("genres").equals("default")) {
			path += "&g=" + request.getParameter("genres");
		}
		if(request.getParameter("minPrice") != "" && Double.parseDouble(request.getParameter("minPrice")) != 0) {
			path += "&mn=" + Double.parseDouble(request.getParameter("minPrice"));
		}
		if(request.getParameter("maxPrice") != "" && Double.parseDouble(request.getParameter("maxPrice")) != 0) {
			path += "&mx=" + Double.parseDouble(request.getParameter("maxPrice"));
		}
		if(!request.getParameter("orderBy").equals("default")) {
			path += "&ob=" + request.getParameter("orderBy");
		}
		response.sendRedirect(path);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
