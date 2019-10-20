package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="FileDownload" ,urlPatterns= {"/fileDownload"})
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean checkLoggedIn=checkLoggedIn(request, response);
		if(checkLoggedIn) {
			
			java.io.File file=new java.io.File("sk.mp4‎⁨");
			
			
			try {
				response.setContentType("application/OCTET-STREAM");
				response.addHeader("Content-Disposition", "attachment;filename="+file.getName()+"");
				FileInputStream fStream=new FileInputStream(file);
				
				byte [] b=new byte[fStream.available()];
				fStream.read(b,0,fStream.available());
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(b);
				fStream.close();
				request.getSession().invalidate();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	private boolean checkLoggedIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean check=false;
		if(request.getSession().getAttribute("loggedIn")!=null && (Boolean)request.getSession().getAttribute("loggedIn")==true) {
			check=true;
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		return check;
	}
}
