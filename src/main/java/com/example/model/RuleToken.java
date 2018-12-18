package com.example.model;

public class RuleToken {
	
	private int id;
	private int userRuleId;
	private String quantity;
	private String ruleBuySell;
	private String ruleTokenName;
	private String ruleToken;
	
	public RuleToken() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserRuleId() {
		return userRuleId;
	}

	public void setUserRuleId(int userRuleId) {
		this.userRuleId = userRuleId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRuleBuySell() {
		return ruleBuySell;
	}

	public void setRuleBuySell(String ruleBuySell) {
		this.ruleBuySell = ruleBuySell;
	}

	public String getRuleTokenName() {
		return ruleTokenName;
	}

	public void setRuleTokenName(String ruleTokenName) {
		this.ruleTokenName = ruleTokenName;
	}

	public String getRuleToken() {
		return ruleToken;
	}

	public void setRuleToken(String ruleToken) {
		this.ruleToken = ruleToken;
	}

	@Override
	public String toString() {
		return "RuleToken [id=" + id + ", userRuleId=" + userRuleId + ", quantity=" + quantity + ", ruleBuySell="
				+ ruleBuySell + ", ruleTokenName=" + ruleTokenName + ", ruleToken=" + ruleToken + "]";
	}

	

}
