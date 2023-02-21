package com.reader;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.Users.model.UsersService;
import com.article.model.ArticleService;


@WebServlet("/user/DBGifReader")
public class DBGifReaderControllerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			UsersService userSvc = new UsersService();
			out.write(userSvc.getOneUser(userId).getUserPic());
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/front-end/article/image/artpic_person/4.png");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();

		}
	}
}
