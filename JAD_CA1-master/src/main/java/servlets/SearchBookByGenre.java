package servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBookByGenre
 */
@WebServlet("/SearchBookByGenre")
public class SearchBookByGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookByGenre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchInput = request.getParameter("searchInput");
        String genre = request.getParameter("genre");
        
        System.out.println(searchInput);
        System.out.println(genre);

        if (genre != null && !genre.isEmpty()) {
            String path = request.getContextPath() + "/Pages/BookList.jsp?genre=" + genre;

            if (searchInput != null && !searchInput.isEmpty()) {
                path += "&q=" + URLEncoder.encode(searchInput, "UTF-8");
            }
            if (request.getParameter("minPrice") != null && !request.getParameter("minPrice").isEmpty()) {
                path += "&mn=" + Double.parseDouble(request.getParameter("minPrice"));
            }
            if (request.getParameter("maxPrice") != null && !request.getParameter("maxPrice").isEmpty()) {
                path += "&mx=" + Double.parseDouble(request.getParameter("maxPrice"));
            }
            if (!request.getParameter("orderBy").equals("default")) {
                path += "&ob=" + request.getParameter("orderBy");
            }

            response.sendRedirect(path);
        } else {
            response.sendRedirect(request.getContextPath() + "/Pages/BookGenre.jsp?message=No genre selected");
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
