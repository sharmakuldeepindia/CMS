package com.example.model;

import java.util.List;

public class RuleTokenWrapper {
	
	List<RuleToken> ruleTokens;
	UserRule userRule;
	public List<RuleToken> getRuleTokens() {
		return ruleTokens;
	}
	public void setRuleTokens(List<RuleToken> ruleTokens) {
		this.ruleTokens = ruleTokens;
	}
	public UserRule getUserRule() {
		return userRule;
	}
	public void setUserRule(UserRule userRule) {
		this.userRule = userRule;
	}
	@Override
	public String toString() {
		return "RuleTokenWrapper [ruleTokens=" + ruleTokens + ", userRule=" + userRule + "]";
	}
	


}
