package com.vendor.model;

import java.util.List;
import java.util.Map;

public class VendorService {
	private VendorDAO_interface dao ; 
	public VendorService() {
		dao = new VendorJDBCDAO();
	}
	
	public VendorVO addVendor(String pw , String name , String taxNum ,String email,String tel , String location ,String win , String wintel,String intro , String canpolicy , String notice , String bank , Integer status ,Integer scorePeople ,java.sql.Date joinDate) {
		VendorVO  venVO = new VendorVO();
		venVO.setVenPw(pw);
		venVO.setVenName(name);
		venVO.setVenTaxnum(taxNum);
		venVO.setVenEmail(email);
		venVO.setVenTel(tel);
		venVO.setVenLocation(location);
		venVO.setVenWin(win);
		venVO.setVenWintel(wintel);
		venVO.setVenIntro(intro);
		venVO.setVenCanpolicy(canpolicy);
		venVO.setVenNotice(notice);
		venVO.setVenBank(bank);
		venVO.setVenStatus(status);
		venVO.setVenScorePeople(scorePeople);
		venVO.setVenJoinDate(joinDate);
		
		dao.insert(venVO);
		return venVO;
	}
	
	public VendorVO updateVendor(String pw , String name , String taxNum ,
			String email,String tel , String location ,String win , 
			String wintel,String intro , String canpolicy , String notice , 
			String bank , Integer status,Float score,Integer scorePeople ,java.sql.Date joinDate,Integer id) {
		VendorVO venVO = new VendorVO();
		venVO.setVenPw(pw);
		venVO.setVenName(name);
		venVO.setVenTaxnum(taxNum);
		venVO.setVenEmail(email);
		venVO.setVenTel(tel);
		venVO.setVenLocation(location);
		venVO.setVenWin(win);
		venVO.setVenWintel(wintel);
		venVO.setVenIntro(intro);
		venVO.setVenCanpolicy(canpolicy);
		venVO.setVenNotice(notice);
		venVO.setVenBank(bank);
		venVO.setVenStatus(status);
		venVO.setVenScore(score);
		venVO.setVenScorePeople(scorePeople);
		venVO.setVenJoinDate(joinDate);
		venVO.setVenId(id);

		
		dao.update(venVO);
		return venVO;
	}
	
	public VendorVO getOneVendor(Integer venID) {
		VendorVO venVO = dao.findByPrimaryKey(venID);
		return venVO;
	}
	
	public void deleteVendor(Integer venID) {
		dao.delete(venID);
	}
	
	public List<VendorVO> getAll(){	
		List<VendorVO> venList = dao.getAll();
		return venList;
	}
	
	public List<VendorVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<VendorVO> getByKeyword(String keyword){	
		List<VendorVO> venList = dao.getByKeyword(keyword);
		return venList;
	}
	
	public VendorVO getVendorByTax(String venTaxNum) {
		return dao.findByTax(venTaxNum);

	}
	
	
}
