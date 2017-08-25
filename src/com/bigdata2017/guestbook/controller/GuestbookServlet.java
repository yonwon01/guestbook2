package com.bigdata2017.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.guestbook.dao.GuestBookDao;
import com.bigdata2017.guestbook.vo.GuestBookVo;

/**
 * Servlet implementation class GuestbookServlet
 */
@WebServlet("/el")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String actionName = request.getParameter("a");

		if ("index".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);

		} else if ("add".equals(actionName)) {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);

			new GuestBookDao().insert(vo);

			response.sendRedirect(request.getContextPath()+"/el");
			
		} else if ("deleteform".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		} else if ("delete".equals(actionName)) {

			request.setCharacterEncoding("utf-8");
			String password = request.getParameter("password");
			String no = request.getParameter("no");
			GuestBookVo vo = new GuestBookVo();
			vo.setPassword(password);
		 vo.setNumber(Integer.parseInt(no));

			new GuestBookDao().delete(vo);
			response.sendRedirect(request.getContextPath()+"/el");
		} else {

			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();

			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
