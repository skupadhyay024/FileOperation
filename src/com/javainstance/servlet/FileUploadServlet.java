package com.javainstance.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.javainstance.dao.FileDao;
import com.javainstance.model.FileDetail;

/**
 * Servlet implementation class FileUploadServlet
 */

@MultipartConfig(maxFileSize = 16177215)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FileUploadServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FileDao dao=new FileDao();
		 response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();   
	        out.println("<h1>File List</h1>");  
	          
	        List<FileDetail> list=dao.getAllFiles();
	          
	        out.print("<table border='1' width='100%'");  
	        out.print("<tr><th>File Id</th><th>File Name</th></tr>");  
	        for(FileDetail f:list){  
	         out.print("<tr><td>"+f.getFileId()+"</td><td>"+f.getFileName()+"</td><td><a href='/FileOperation/download?id="+f.getFileId()+"'>Download</a></td></tr>");  
	        }  
	        out.print("</table>");  
	          
	        out.close();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int result = 0;

		InputStream inputStream = null;

		Part filePart = request.getPart("image");
		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			inputStream = filePart.getInputStream();
		}

		FileDao fileDao = new FileDao();
		if (inputStream != null) {
			result = fileDao.saveFile(filePart.getSubmittedFileName(),inputStream);
		}

		if (result == 1) {
			out.println("uploaded...");
		} else {

			out.println("no file uploaded");
		}

	}
}
