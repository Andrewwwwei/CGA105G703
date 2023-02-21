package com.reader;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.article.model.ArticleService;


@WebServlet("/article/DBGifReader")
public class DBGifReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer artId = Integer.valueOf(req.getParameter("artId"));
			ArticleService artSvc = new ArticleService();
			out.write(artSvc.getOneArticle(artId).getArtPic());
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/front-end/article/image/artpic/13.png");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();

		}
	}
}
