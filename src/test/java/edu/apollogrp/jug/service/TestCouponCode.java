package edu.apollogrp.jug.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.apollogrp.jug.model.CouponCode;
import edu.apollogrp.jug.model.Item;
import edu.apollogrp.jug.model.Order;

public class TestCouponCode extends BaseRuleTestCase {

	@Override
	protected List<String> getPackageNames() {
		List<String> names = new ArrayList<String>();
		names.add("/rules/coupon-code.drl");
		return names;
	}

	public void testCannotCombineOffers() {
		CouponCode noOtherOffers = new CouponCode("all", "percentage", 10, false);
		CouponCode fiveOffShirts = new CouponCode("shirt", "flat", 5, true);

		Collection list = new ArrayList();
		list.add(noOtherOffers);
		list.add(fiveOffShirts);

		Collection result = rulesService.execute(list);
		assertEquals(1, result.size());
	}

	public void testCannotCombineAllOffers() {
		CouponCode noOtherOffers = new CouponCode("all", "percentage", 10, false);
		CouponCode anotherNoOffers = new CouponCode("all", "percentage", 20, false);

		Collection list = new ArrayList();
		list.add(noOtherOffers);
		list.add(anotherNoOffers);

		Collection result = rulesService.execute(list);
		assertEquals(1, result.size());
	}

	public void testItemPercentage() {
		CouponCode tenPercentOffShirts = new CouponCode("shirt", "percentage", 10, true);
		Item redShirt = new Item("shirt", "Red Shirt", 1599);
		Item blueShirt = new Item("shirt", "Blue Shirt", 1299);
		Item shoes = new Item("shoes", "Blue Suede Shoes", 2499);
		Order order = new Order();
		order.addItem(redShirt);
		order.addItem(blueShirt);
		order.addItem(shoes);
		
		Collection list = new ArrayList();
		list.add(tenPercentOffShirts);
		list.add(order);
		list.add(redShirt);
		list.add(blueShirt);
		list.add(shoes);

		Collection result = rulesService.execute(list);
		assertEquals(5, result.size());
		assertEquals(2, order.getCouponCount());
		assertEquals(5107, order.getTotalPrice());
	}

	public void testItemFlat() {
		CouponCode fiveOffShirts = new CouponCode("shirt", "flat", 5, true);
		Item redShirt = new Item("shirt", "Red Shirt", 1599);
		Item blueShirt = new Item("shirt", "Blue Shirt", 1299);
		Item shoes = new Item("shoes", "Blue Suede Shoes", 2499);
		Order order = new Order();
		order.addItem(redShirt);
		order.addItem(blueShirt);
		order.addItem(shoes);
		
		Collection list = new ArrayList();
		list.add(fiveOffShirts);
		list.add(order);
		list.add(redShirt);
		list.add(blueShirt);
		list.add(shoes);

		Collection result = rulesService.execute(list);
		assertEquals(5, result.size());
		assertEquals(2, order.getCouponCount());
		assertEquals(4397, order.getTotalPrice());
	}

	public void testOrderPercentage() {
		CouponCode fivePercentOff = new CouponCode("all", "percentage", 5, true);
		Item redShirt = new Item("shirt", "Red Shirt", 1599);
		Item shoes = new Item("shoes", "Blue Suede Shoes", 2499);
		Order order = new Order();
		order.addItem(redShirt);
		order.addItem(shoes);
		
		Collection list = new ArrayList();
		list.add(fivePercentOff);
		list.add(order);
		list.add(redShirt);
		list.add(shoes);

		Collection result = rulesService.execute(list);
		assertEquals(4, result.size());
		assertEquals(1, order.getCouponCount());
		assertEquals(3893, order.getTotalPrice());
	}

	public void testOrderFlat() {
		CouponCode fiveOffOrder = new CouponCode("all", "flat", 5, true);
		Item redShirt = new Item("shirt", "Red Shirt", 1599);
		Item shoes = new Item("shoes", "Blue Suede Shoes", 2499);
		Order order = new Order();
		order.addItem(redShirt);
		order.addItem(shoes);
		
		Collection list = new ArrayList();
		list.add(fiveOffOrder);
		list.add(order);
		list.add(redShirt);
		list.add(shoes);

		Collection result = rulesService.execute(list);
		assertEquals(4, result.size());
		assertEquals(1, order.getCouponCount());
		assertEquals(3598, order.getTotalPrice());
	}

	public void testOrderPercentageItemFlat() {
		CouponCode fivePercentOff = new CouponCode("all", "percentage", 5, true);
		CouponCode fiveOffShirts = new CouponCode("shirt", "flat", 5, true);
		Item redShirt = new Item("shirt", "Red Shirt", 1599);
		Item shoes = new Item("shoes", "Blue Suede Shoes", 2499);
		Order order = new Order();
		order.addItem(redShirt);
		order.addItem(shoes);
		
		Collection list = new ArrayList();
		list.add(fivePercentOff);
		list.add(fiveOffShirts);
		list.add(order);
		list.add(redShirt);
		list.add(shoes);

		Collection result = rulesService.execute(list);
		assertEquals(5, result.size());
		assertEquals(2, order.getCouponCount());
		assertEquals(3418, order.getTotalPrice());
	}

}
