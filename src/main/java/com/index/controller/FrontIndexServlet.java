package com.index.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;


@WebServlet("/FrontIndex")
public class FrontIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//Top4熱門房型
		RoomService roomService = new RoomService();
		VendorService vendorService = new VendorService();
		List<RoomVO> roomVOList = roomService.getAll();
		Collections.sort(roomVOList, new RoomSort());
		Set<Integer> venSet = new TreeSet<Integer>();
		for(RoomVO roomVO : roomVOList) {
			venSet.add(roomVO.getVenId());
			if(venSet.size() == 4) {
				break;
			}
		}
		String[] regexes = {"(.*)台北(.*)", "(.*)臺北(.*)", "(.*)新北(.*)", "(.*)桃園(.*)",
				"(.*)臺中(.*)", "(.*)台中(.*)", "(.*)臺南(.*)", "(.*)台南(.*)", "(.*)高雄(.*)", 
				"(.*)宜蘭(.*)", "(.*)花蓮(.*)", "(.*)臺東(.*)", "(.*)台東(.*)", "(.*)新竹(.*)",
				"(.*)屏東(.*)", "(.*)苗栗(.*)", "(.*)彰化(.*)", "(.*)南投(.*)", "(.*)雲林(.*)",
				"(.*)嘉義(.*)", "(.*)基隆(.*)", "(.*)澎湖(.*)", "(.*)金門(.*)", "(.*)連江(.*)"};
		List<Map<String, Object>> hotList = new ArrayList<Map<String,Object>>();
		for(Integer venId : venSet) {
			VendorVO vendorVO = vendorService.getOneVendor(venId);
			Map<String, Object> hotMap = new HashMap<String, Object>();
			String area = "";
			for(String regex : regexes) {
				if(vendorVO.getVenLocation().matches(regex)) {
					area = regex.substring(4, 6);
					break;
				}
			}
			List<RoomVO> roomVOs = roomService.getAllByVen(venId);
			int roomprice = 200000;
			for(RoomVO roomVO : roomVOs) {
				if (roomVO.getRoomPrice() < roomprice) {
					roomprice = roomVO.getRoomPrice(); // 取得最便宜的那間
				}
			}
			hotMap.put("venId", venId);
			hotMap.put("venName", vendorVO.getVenName());
			hotMap.put("area", area);
			hotMap.put("price", roomprice);
			hotMap.put("score", vendorVO.getVenScore());
			hotList.add(hotMap);
		}
		req.setAttribute("roomHotList", hotList);
	
		///文章
		ArticleService artSvc = new ArticleService();
		List<ArticleVO> list = artSvc.getAll();
		req.setAttribute("articleVOlist", list);
		
		//揪團
		ActService actService = new ActService();
		List<ActVO> actVOs = actService.getAll();
		req.setAttribute("actVOlist", actVOs);
		
		String url = "/front-end/index.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//以Room的觀看次數升序排列
	private class RoomSort implements Comparator<RoomVO> { 
		public int compare(RoomVO a, RoomVO b) 
		{ 
			return b.getRoomView() - a.getRoomView(); 
		} 
	} 
}
