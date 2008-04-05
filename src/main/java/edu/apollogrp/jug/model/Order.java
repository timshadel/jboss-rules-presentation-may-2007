package edu.apollogrp.jug.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int totalPrice;
	private List<Item> items = new ArrayList<Item>();
	private List<Item> coupons = new ArrayList<Item>();

	public void addItem(Item item) {
		if (item.getType() != "coupon") {
			items.add(item);
		} else {
			coupons.add(item);
		}
		totalPrice = totalPrice + item.getPrice();
	}

	public int getItemCount() {
		return items.size();
	}

	public int getCouponCount() {
		return coupons.size();
	}

	public int getTotalPrice() {
		return totalPrice;
	}
}
