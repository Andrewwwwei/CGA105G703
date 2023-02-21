package com.vendorPic.model;

import java.util.List;


public class VendorPicService {
	private VendorPicDAO_interface dao;

	public VendorPicService() {
		dao = new VendorPicJDBCDAO();
//		dao = new RoomPhotoDAO();
	}

	public VendorPicVO addVendorpic(Integer venId, byte[] venPic) {

		VendorPicVO vendorPicVO = new VendorPicVO();

		vendorPicVO.setVenId(venId);
		vendorPicVO.setVenPic(venPic);
		dao.insert(vendorPicVO);

		return vendorPicVO;
	}

	public void addVendorpic(VendorPicVO vendorPicVO) {
		dao.insert(vendorPicVO);
	}
	
	public VendorPicVO updateVendorpic(Integer venPicid, Integer venId, byte[] venPic) {

		VendorPicVO vendorPicVO = new VendorPicVO();
		vendorPicVO.setVenPicid(venPicid);
		vendorPicVO.setVenId(venId);
		vendorPicVO.setVenPic(venPic);
		dao.update(vendorPicVO);

		return dao.findByPrimaryKey(venPicid);
	}
	
	public void updateVendorpic(VendorPicVO vendorPicVO) {
		dao.update(vendorPicVO);
	}

	public void deleteVendorpic(Integer vendorPicVO) {
		dao.delete(vendorPicVO);
	}

	public VendorPicVO getOneVendorpic(Integer venPicid) {
		return dao.findByPrimaryKey(venPicid);
	}

	public List<VendorPicVO> getAll() {
		return dao.getAll();
	}
	
	public List<VendorPicVO> getThisVendorpic(Integer venId) {
		return dao.getAVendor(venId);
	}
}
