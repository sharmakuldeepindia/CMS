package com.example.model;


public class UserRule {
	private String id;
	private String name;
	private boolean alert;
	private String depth;
	private String tokenCount;
	private String bestBuy;
	private String bestSell;
	private String bestBuyQty;
	private String bestSellQty;
	
	public UserRule() {}
	
	
	public UserRule(String id, String name, boolean alert, String depth, String tokenCount, String bestBuy,
			String bestSell, String bestBuyQty, String bestSellQty) {
		super();
		this.id = id;
		this.name = name;
		this.alert = alert;
		this.depth = depth;
		this.tokenCount = tokenCount;
		this.bestBuy = bestBuy;
		this.bestSell = bestSell;
		this.bestBuyQty = bestBuyQty;
		this.bestSellQty = bestSellQty;
	}


	public UserRule(String id, String name, boolean alert) {
		super();
		this.id = id;
		this.name = name;
		this.alert = alert;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
	}
	
	
	
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getTokenCount() {
		return tokenCount;
	}
	public void setTokenCount(String tokenCount) {
		this.tokenCount = tokenCount;
	}
	public String getBestBuy() {
		return bestBuy;
	}
	public void setBestBuy(String bestBuy) {
		this.bestBuy = bestBuy;
	}
	public String getBestSell() {
		return bestSell;
	}
	public void setBestSell(String bestSell) {
		this.bestSell = bestSell;
	}
	public String getBestBuyQty() {
		return bestBuyQty;
	}
	public void setBestBuyQty(String bestBuyQty) {
		this.bestBuyQty = bestBuyQty;
	}
	public String getBestSellQty() {
		return bestSellQty;
	}
	public void setBestSellQty(String bestSellQty) {
		this.bestSellQty = bestSellQty;
	}
	@Override
	public String toString() {
		return "UserRule [id=" + id + ", name=" + name + ", alert=" + alert + "]";
	}
	
	
	
	
	
	
}
