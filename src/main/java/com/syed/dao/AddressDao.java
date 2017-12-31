package com.syed.dao;

import com.syed.entity.Address;

public interface AddressDao {
	Address updateAddress(Address address);

	int deleteAddress(Long custId);
}
