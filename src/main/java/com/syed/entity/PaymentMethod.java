package com.syed.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class PaymentMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8536345472128992332L;

	@Id
	@GeneratedValue
	@Column(name = "payment_id")
	private Long paymentId;

	private String cardNumber;
	private String cardName;
	private Date dateFrom;

	@Transient
	private String dateType;
	private String cardType;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaymentMethod [paymentId=" + paymentId + ", cardNumber=" + cardNumber + ", cardName=" + cardName
				+ ", dateFrom=" + dateFrom + ", cardType=" + cardType +  "]";
	}

}