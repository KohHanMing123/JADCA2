package user;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class getImage
 */
@WebServlet("/getImage")
public class getImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dbUser = System.getenv("PLANETSCALE_USERNAME");
        String dbKey = System.getenv("PLANETSCALE_KEY");

        int custID = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser
                    + "&password=" + dbKey + "&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(connURL);

            String sql = "SELECT custImageURL FROM Customers WHERE custID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, custID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                byte[] imageData = rs.getBytes("custImageURL");

                String contentType = "image/jpeg";

                String imageUrl = rs.getString("custImageURL");
                String fileExtension = imageUrl.substring(imageUrl.lastIndexOf(".") + 1).toLowerCase();
                
                // Currently only works for jpeg, png truncation error, too long
                if (fileExtension.equals("jpeg") || fileExtension.equals("jpg")) {
                    contentType = "image/jpeg";
                } else if (fileExtension.equals("png")) {
                    contentType = "image/png";
                }

                if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
                    response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                    return;
                }

                response.setContentType(contentType);
                response.setContentLength(imageData.length);

                String imageURL = imageUrl; 
                request.getSession().setAttribute("imageURL", imageURL);

             
                OutputStream out = response.getOutputStream();
                out.write(imageData);
                out.close();
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
