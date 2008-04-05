package edu.apollogrp.jug.model;

public class CouponCode {
	private String type;
	private String discountStyle;
	private int amount;
	private boolean validWithOtherOffers;
	
	public CouponCode(String type, String discountStyle, int amount, boolean validWithOtherOffers) {
		this.type = type;
		this.discountStyle = discountStyle;
		this.amount = amount;
		this.validWithOtherOffers = validWithOtherOffers;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDiscountStyle() {
		return discountStyle;
	}
	public void setDiscountStyle(String discountStyle) {
		this.discountStyle = discountStyle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isValidWithOtherOffers() {
		return validWithOtherOffers;
	}
	public void setValidWithOtherOffers(boolean validWithOtherOffers) {
		this.validWithOtherOffers = validWithOtherOffers;
	}
}
