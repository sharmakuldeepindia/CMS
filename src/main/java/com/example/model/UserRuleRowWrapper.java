package com.example.model;

import java.util.List;

public class UserRuleRowWrapper {

	List<RuleRow> ruleRows;
	UserRule userRule;
	
	public List<RuleRow> getRuleRows() {
		return ruleRows;
	}
	public void setRuleRows(List<RuleRow> ruleRows) {
		this.ruleRows = ruleRows;
	}
	public UserRule getUserRule() {
		return userRule;
	}
	public void setUserRule(UserRule userRule) {
		this.userRule = userRule;
	}
	@Override
	public String toString() {
		return "UserRuleRowWrapper [ruleRows=" + ruleRows + ", userRule=" + userRule + "]";
	}
	
	
	
	
}
