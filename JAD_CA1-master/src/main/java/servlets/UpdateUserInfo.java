package servlets;

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

import models.*;
@WebServlet("/UpdateUserInfo")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateUserInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Checks if they are logged in
        if (session.getAttribute("custID") == null) {
            response.sendRedirect("http://localhost:8080/JAD_CA1-master/CheckProfileExistence");
            return;
        }

        int custID = Integer.parseInt((String) session.getAttribute("custID"));
        System.out.println("this is my customer ID " + custID);
        
        // Retrieve the updated user information from the request parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create an instance of the SQLqueryUser class
        SQLqueryUser query = new SQLqueryUser();

        // Call the updateUserInfo method to update the user information
        query.updateUserInfo(custID, username, email, password);

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

                    // Call the insertImage method to update the user image
                    query.insertImage(custID, imageData);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error");
                } finally {
                    deleteDirectory(dir);
                }
            }
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
