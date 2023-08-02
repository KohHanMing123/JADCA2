package models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.Part;

public class Image {
	public static String saveImage(Part imageFile) throws Exception {
		String fileName = imageFile.getSubmittedFileName();
		String folderPath = "temporaryImage";
		
		if(!(fileName.contains("png") || fileName.contains("jpeg") || fileName.contains("jpg"))) {
			throw new Exception("Wrong file format");
		}else {
			String imageUID = UUID.randomUUID().toString() + ".jpg";
			String filePath = System.getenv("ASSETS_PATH") + imageUID;
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

		         return "/assets/" + imageUID;
		     } catch (IOException e) {
		         e.printStackTrace();
		         throw e;
		     }
		}
	}
}
