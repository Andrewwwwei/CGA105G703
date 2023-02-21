package com.vendor.model;

import java.util.List;
import java.util.Map;

public interface VendorDAO_interface {
	public void insert(VendorVO vendorVO);
    public void update(VendorVO vendorVO);
    public void delete(Integer venId);
    public VendorVO findByPrimaryKey(Integer venId);
    public List<VendorVO> getAll();
    public List<VendorVO> getAll(Map<String, String[]> map);
    public List<VendorVO> getByKeyword(String keyword);
    public void update(VendorVO vendorVO, java.sql.Connection con);
    public VendorVO findByTax(String venTaxnum);

}

