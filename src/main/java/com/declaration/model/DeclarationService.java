package com.declaration.model;

import java.util.List;

import com.Users.model.UsersVO;


public class DeclarationService {
	private DeclarationDAO_interface dao;
	
	public DeclarationService() {
		dao = new DeclarationJDBCDAO();
	}
	
	public DeclarationVO addDeclaration(String title, String content , byte[] pic) {
		DeclarationVO decVO = new DeclarationVO();
		decVO.setTitle(title);
		decVO.setContent(content);
		
		if(pic == null || pic.length == 0) {
			decVO.setPic(null);
			int id = dao.insert(decVO);
			decVO.setDeclarationID(id);
			return decVO;
		}
		
		decVO.setPic(pic);
		int id = dao.insert(decVO);
		decVO.setDeclarationID(id);

		return decVO;
	}
	
	public DeclarationVO updateDeclaration(DeclarationVO decVO) {		
		dao.update(decVO);
		return decVO;
	}
	
	public DeclarationVO getOneDeclaration(Integer id) {
		DeclarationVO decVO = dao.findByPrimaryKey(id);
		return decVO;
	}
	public List<UsersVO> getUsers(String input){
		List<UsersVO> list = dao.getUsers(input);
		return list;
	}
	
	public List<UsersVO> getAnnounceUsers(){
		List<UsersVO>list = dao.getUsers(null);
		return null;
	}
	
	public List<DeclarationVO> getAll() {
		List<DeclarationVO> decList  = dao.getAll();
		return decList;	
	}
	
	public void deleteDeclaration(Integer id) {
		dao.delete(id);
	}
	public void deleteStatus(Integer id) {
		dao.deleteStatus(id);
	}
	public byte[] getPic(Integer declarationID) {
		byte[] pic = dao.getPic(declarationID);
		return pic;
	}
	public List<DeclarationVO> findPage(Integer currPage) {
		List<DeclarationVO> list = dao.findPage(currPage);
		return list;
	}
}
