package br.ufscar.dc.dsw.controller;

import static br.ufscar.dc.dsw.controller.Constants.UPLOAD_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListFileServlet", urlPatterns = { "/upload.jsp" })
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session =  request.getSession();
		request.setAttribute("message", session.getAttribute("message"));
		session.invalidate();
		
		List<String> fileList = new ArrayList<String>();
		
		String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;

		File dir = new File(uploadPath);

		File[] files = dir.listFiles();
		
		if (files != null) {
			for (final File file : files) {
				fileList.add(file.getName());
			}
		}
		
		request.setAttribute("fileList", fileList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/upload.jsp");
        dispatcher.forward(request, response);
	}
}
