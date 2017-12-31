package com.syed.dao;

import java.util.List;

import com.syed.entity.PaymentMethod;

public interface PaymentMethodDao {
	List<PaymentMethod> findPaymentMehods(Long custId);

	int deletePaymentMethods(Long custId);

	PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);

	void insertPaymentMethods(List<PaymentMethod> paymentMethods);
}
