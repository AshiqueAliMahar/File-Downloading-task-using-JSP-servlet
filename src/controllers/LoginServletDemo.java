package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="LoginServletDemo", urlPatterns= {"/login"})
public class LoginServletDemo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getParameter("email")!=null && request.getParameter("password")!=null && !request.getParameter("email").equals("") && !request.getParameter("password").equals("")) {
			if(request.getParameter("email").equals("email") && request.getParameter("password").equals("password")) {
				request.getSession().setAttribute("loggedIn", true);
			}
		}
    	if(request.getSession().getAttribute("loggedIn")!=null && (Boolean)request.getSession().getAttribute("loggedIn")==true) {
			
			request.getRequestDispatcher("fileDownload").forward(request, response);
			
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
