package edu.apollogrp.jug.model;

public class Item {
	private String type;
	private String name;
	private int price;
	
	public Item(String type, String name, int price) {
		this.type = type;
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
