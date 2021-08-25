package com.mobiquity.model;

import java.util.List;

public class Package {

	private List<Item> items;
	private Integer weight;

	public Package(List<Item> items, Integer weight) {
		super();
		this.items = items;
		this.weight = weight;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
