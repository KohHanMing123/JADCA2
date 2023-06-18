package user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UpdateUserInfo")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateUserInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // Checks if they are logged in
        if (session.getAttribute("custID") == null) {
            response.sendRedirect("http://localhost:8080/JAD_CA1-master/CheckProfileExistence");
            return;
        }

        String dbUser = System.getenv("PLANETSCALE_USERNAME");
        String dbKey = System.getenv("PLANETSCALE_KEY");

        int custID = Integer.parseInt((String) session.getAttribute("custID"));
        System.out.println("this is my customer ID " + custID);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser
                    + "&password=" + dbKey + "&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(connURL);

            StringBuilder sqlBuilder = new StringBuilder("UPDATE Customers SET");

            // Check if the username field is provided
            String username = request.getParameter("username");
            if (username != null && !username.isEmpty()) {
                sqlBuilder.append(" username = ?,");
            }

            // Check if the email field is provided
            String email = request.getParameter("email");
            if (email != null && !email.isEmpty()) {
                sqlBuilder.append(" email = ?,");
            }

            // Check if the password field is provided
            String password = request.getParameter("password");
            if (password != null && !password.isEmpty()) {
                sqlBuilder.append(" password = ?,");
            }

            // Remove the trailing comma from the SQL query
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE custID = ?");
            PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString());

            int parameterIndex = 1;

            if (username != null && !username.isEmpty()) {
                pstmt.setString(parameterIndex, username);
                parameterIndex++;
            }

            if (email != null && !email.isEmpty()) {
                pstmt.setString(parameterIndex, email);
                parameterIndex++;
            }

            if (password != null && !password.isEmpty()) {
                pstmt.setString(parameterIndex, password);
                parameterIndex++;
            }

            pstmt.setInt(parameterIndex, custID);
            
            
         // Update the user image if provided
            Part imageFile = request.getPart("imageInput");
            System.out.println("image file data is " + imageFile);
            if (imageFile != null && imageFile.getSize() > 0) {
                String fileName = imageFile.getSubmittedFileName();
                String folderPath = "temporaryImage";

                if (fileName.contains("png") || fileName.contains("jpeg") || fileName.contains("jpg")) {
                    String filePath = folderPath + File.separator + fileName;
                    File dir = new File(folderPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    try {
                        InputStream fileInputStream = imageFile.getInputStream();
                        OutputStream fileOutputStream = new FileOutputStream(filePath);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fileInputStream.read(buffer)) > 0) {
                            fileOutputStream.write(buffer, 0, length);
                        }
                        fileOutputStream.close();
                        File imageToUpload = new File(filePath);
                        byte[] imageData = new byte[(int) imageToUpload.length()];
                        try (FileInputStream fis = new FileInputStream(imageToUpload)) {
                            fis.read(imageData);
                        }
                        fileInputStream.close();
                        SQLqueryUser query = new SQLqueryUser();
                        query.insertImage(custID ,imageData);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error");
                    } finally {
                        deleteDirectory(dir);
                    }
                }
            }

            pstmt.executeUpdate();

            

            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e);
        }	

        response.sendRedirect(request.getContextPath() + "/Pages/User.jsp");
        System.out.println("Customer info updated!");
    }

    public static void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}
