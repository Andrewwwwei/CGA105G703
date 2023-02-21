package com.Users.model;


import java.sql.Connection;
import java.util.List;

public class UsersService {
	private UsersDAO_interface dao;

	public UsersService() {
		dao = new UsersJDBCDAO();
	}

	public UsersVO addUser(String userAccount, String userPassword, String userName, String userNickname,
			String userIdNo, java.sql.Date userBrthday, String userPhone, String userAddress,
			Byte userCertificationStatus, Byte userStatus, String userGender, Byte userForumLevel, byte[] userPic,
			Byte userShopLevel, Integer bonusPoints, Float alltogetherScore, Integer alltogetherScorePeople,
			Integer artCount, Integer likeTotalCount, Integer purchaseTotal, Byte reportTotalCount) {
		UsersVO usersVO = new UsersVO();
		usersVO.setUserAccount(userAccount);
		usersVO.setUserPassword(userPassword);
		usersVO.setUserName(userName);
		usersVO.setUserNickname(userNickname);
		usersVO.setUserIdNo(userIdNo);
		usersVO.setUserBrthday(userBrthday);
		usersVO.setUserPhone(userPhone);
		usersVO.setUserAddress(userAddress);
		usersVO.setUserCertificationStatus(userCertificationStatus);
		usersVO.setUserStatus(userStatus);
		usersVO.setUserGender(userGender);
		usersVO.setUserForumLevel(userForumLevel);
		usersVO.setUserPic(userPic);
		usersVO.setUserShopLevel(userShopLevel);
		usersVO.setBonusPoints(bonusPoints);
		usersVO.setAlltogetherScore(alltogetherScore);
		usersVO.setAlltogetherScorePeople(alltogetherScorePeople);
		usersVO.setArtCount(artCount);
		usersVO.setLikeTotalCount(likeTotalCount);
		usersVO.setPurchaseTotal(purchaseTotal);
		usersVO.setReportTotalCount(reportTotalCount);
		dao.insert(usersVO);
		return usersVO;

	}
	public UsersVO updateUser(Integer userId,String userAccount, String userPassword, String userName, String userNickname,
			String userIdNo, java.sql.Date userBrthday, String userPhone, String userAddress,
			Byte userCertificationStatus, Byte userStatus, String userGender, java.sql.Timestamp userRegistrationDate,
			Byte userForumLevel, byte[] userPic, Byte userShopLevel, Integer bonusPoints, Float alltogetherScore,
			Integer alltogetherScorePeople, Integer artCount, Integer likeTotalCount, Integer purchaseTotal,
			Byte reportTotalCount) {
		UsersVO usersVO = new UsersVO();
		usersVO.setUserAccount(userAccount);
		usersVO.setUserPassword(userPassword);
		usersVO.setUserName(userName);
		usersVO.setUserNickname(userNickname);
		usersVO.setUserIdNo(userIdNo);
		usersVO.setUserBrthday(userBrthday);
		usersVO.setUserPhone(userPhone);
		usersVO.setUserAddress(userAddress);
		usersVO.setUserCertificationStatus(userCertificationStatus);
		usersVO.setUserStatus(userStatus);
		usersVO.setUserGender(userGender);
		usersVO.setUserRegistrationDate(userRegistrationDate);
		usersVO.setUserForumLevel(userForumLevel);
		usersVO.setUserPic(userPic);
		usersVO.setUserShopLevel(userShopLevel);
		usersVO.setBonusPoints(bonusPoints);
		usersVO.setAlltogetherScore(alltogetherScore);
		usersVO.setAlltogetherScorePeople(alltogetherScorePeople);
		usersVO.setArtCount(artCount);
		usersVO.setLikeTotalCount(likeTotalCount);
		usersVO.setPurchaseTotal(purchaseTotal);
		usersVO.setReportTotalCount(reportTotalCount);
		dao.update(usersVO);
		return usersVO;

	}
	public void updateUser(UsersVO usersVO, Connection con) {
		dao.update2(usersVO, con);
	}
	
	public void deleteUser(Integer userId) {
		dao.delete(userId);
	}
	
	public UsersVO getOneUser(Integer userId) {
		return dao.findByPrimaryKey(userId);
	}

	public List<UsersVO> getAll() {
		return dao.getAll();
	}
	
	//user功能新增
	public UsersVO UpdatePassword(String userPassword,String userPasswordw,String userAccount) {
		UsersVO usersVO = new UsersVO();
		usersVO.setUserPassword(userPassword);
		usersVO.setUserPasswordW(userPasswordw);
		usersVO.setUserAccount(userAccount);
		dao.updatePassword(usersVO);
		return usersVO;
	}
	public UsersVO ResetPassword(String userPassword,String userAccount) {
		UsersVO usersVO = new UsersVO();
		usersVO.setUserPassword(userPassword);
		usersVO.setUserAccount(userAccount);
		dao.resetPassword(usersVO);
		return usersVO;
	}
	public UsersVO updateUserStatus(String userAccount) {
		UsersVO usersVO = new UsersVO();
		usersVO.setUserAccount(userAccount);
		dao.updateUserStatusByUserAccount(usersVO);
		return usersVO;
	}

	public UsersVO restorationUserStatusByUserAccount(String userAccount) {
		UsersVO usersVO = new UsersVO();
		usersVO.setUserAccount(userAccount);
		dao.restorationUserStatusByUserAccount(usersVO);
		return usersVO;
	}

	public UsersVO updateUserInfo(String userNickname, String userPhone, byte[] userPic, String userAccount) {
		UsersVO usersVO = new UsersVO();
		usersVO.setUserAccount(userAccount);
		usersVO.setUserNickname(userNickname);
		usersVO.setUserPhone(userPhone);
		usersVO.setUserPic(userPic);
		dao.updateinfo(usersVO);
		return usersVO;
	}
	
	public UsersVO getOneUserEmail(String userAccount) {
		return dao.findByUserAccount(userAccount);
	}

	public UsersVO getOneUserName(String userName) {
		return dao.findByUserName(userName);
	}
	
	public List<UsersVO> getAllByReportTotalCount(Integer reportTotalCount) {
		return dao.getAllByReportTotalCount(reportTotalCount);
	}

	public boolean login(String userAccount, String userPassword) {
		return dao.login(userAccount, userPassword);
	}

	public Integer findStatusByUserAccount(String userAccount) {
		return dao.findStatusByUserAccount(userAccount);
	}
	public Integer findIdByUserAccount(String userAccount) {
		return dao.findIdByUserAccount(userAccount);
	}
	public byte[] getPic(String path) {
		return dao.getPic(path);
	}
}
