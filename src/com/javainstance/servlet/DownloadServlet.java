package com.javainstance.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javainstance.dao.FileDao;
import com.javainstance.model.FileDetail;

/**
 * Servlet implementation class DownloadServlet
 */

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
		int id=Integer.parseInt(request.getParameter("id"));
		
		
		FileDao dao=new FileDao();
		FileDetail fileDetail=dao.downloadFile(id);
		
		 Blob blob = fileDetail.getFileContent();
         InputStream inputStream;
		try {
			inputStream = blob.getBinaryStream();
			 int fileLength = inputStream.available();
			 System.out.println("fileLength = " + fileLength);

	         ServletContext context = getServletContext();


		        // sets MIME type for the file download
		        String mimeType = context.getMimeType(fileDetail.getFileName());
		        if (mimeType == null) {        
		            mimeType = "application/octet-stream";
		        }              
		         
		        // set content properties and header attributes for the response
		        response.setContentType(mimeType);
		        response.setContentLength(fileLength);
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", fileDetail.getFileName());
		        response.setHeader(headerKey, headerValue);
		        
		        // writes the file to the client
		        ServletOutputStream outStream = response.getOutputStream();
		         
		        byte[] buffer = new byte[BUFFER_SIZE];
		        int bytesRead = -1;
		         
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		         
		        inputStream.close();
		        outStream.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
          
        

	                
	    } 
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
