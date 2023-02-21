package com.vendorPic.model;

import java.util.List;

public interface VendorPicDAO_interface {
	public void insert(VendorPicVO vendorPicVO);
    public void update(VendorPicVO vendorPicVO);
    public void delete(Integer venPicid);
    public VendorPicVO findByPrimaryKey(Integer venPicid);
    public List<VendorPicVO> getAll();
	public List<VendorPicVO> getAVendor(Integer venId);
}
