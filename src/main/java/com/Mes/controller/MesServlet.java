package com.Mes.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Mes.model.MesService;
import com.Mes.model.MesVO;
import com.vendorPic.model.VendorPicService;
import com.vendorPic.model.VendorPicVO;

@WebServlet("/MesServlet")
public class MesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/jpeg");
		Integer mesId = Integer.parseInt(req.getParameter("mesId"));
		MesService mesService = new MesService();
		MesVO mesVO = mesService.getOneMesVO(mesId);
		byte[] pic = mesVO.getSendPic();
		if(pic == null) {
			byte[] buf = null;
		    try (InputStream in = Files.newInputStream(Path.of(getServletContext().getRealPath("/front-end/act/images") + "/Logo.png"));
		    		ServletOutputStream out = res.getOutputStream()){
		     buf = new byte[in.available()];
		     in.read(buf);
		     out.write(buf);
		     return;
		    } catch (IOException e) {
		     e.printStackTrace();
		    }
		}
		try (ServletOutputStream out = res.getOutputStream()){
			out.write(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
