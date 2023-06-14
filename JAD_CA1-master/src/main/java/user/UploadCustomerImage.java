package user;

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

import user.SQLqueryUser;

/**
 * Servlet implementation class UploadCustomerImage
 */
@WebServlet("/UploadCustomerImage")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)

public class UploadCustomerImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadCustomerImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // FILE TO BE DELETED (DUPLICATE)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Part imageFile = request.getPart("imageInput");
		String fileName = imageFile.getSubmittedFileName();
		String folderPath = "temporaryImage";
		
		if(!(fileName.contains("png") || fileName.contains("jpeg") || fileName.contains("jpg"))) {
			
		}else {

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
//		         SQLqueryUser query = new SQLqueryUser();
//		         query.insertImage(custID, imageData);
		     } catch (IOException e) {
		         e.printStackTrace();
		         System.out.println("error");
		     }finally {
		    	 deleteDirectory(dir);
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

