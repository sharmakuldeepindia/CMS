package com.example.model;

public class RuleRow {
	private int ruleRowId;
	private int userRuleId;
	private String lots;
	private String levels;
	private String ruleBuySell;
	private String ruleTokenName;
	private String ruleToken;
	public RuleRow() {}
	public RuleRow(int ruleRowId ,int userRuleId, String lots, String levels, String ruleBuySell,
			 String ruleToken, String ruleTokenName) {
		super();
		this.ruleRowId = ruleRowId;
		this.userRuleId = userRuleId;
		this.lots = lots;
		this.levels = levels;
		this.ruleBuySell = ruleBuySell;
		this.ruleTokenName = ruleTokenName;
		this.ruleToken = ruleToken;
	}
	
	
	public int getRuleRowId() {
		return ruleRowId;
	}
	public void setRuleRowId(int ruleRowId) {
		this.ruleRowId = ruleRowId;
	}
	public void setUserRuleId(int userRuleId) {
		this.userRuleId = userRuleId;
	}
	public int getUserRuleId() {
		return userRuleId;
	}
	public void setUserRule(int userRuleId) {
		this.userRuleId = userRuleId;
	}
	public String getLots() {
		return lots;
	}
	public void setLots(String lots) {
		this.lots = lots;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
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
		return "RuleRow [ruleRowId=" + ruleRowId + ", userRuleId=" + userRuleId + ", lots=" + lots + ", levels="
				+ levels + ", ruleBuySell=" + ruleBuySell + "]";
	}
	
	
	

}
