package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.FetchService;

@Controller
public class FetchController {
	@Autowired 
	FetchService fetchService;
	
	@RequestMapping(value="/fetch/getAllInstrumentNames", method=RequestMethod.GET)
	public ResponseEntity<?> getAllInstrumentNames(){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<String>l=	fetchService.getAllInstrumentNames();
			return ResponseEntity.ok(l);			
		
	}
	@RequestMapping(value="/fetch/getAllTokenSymbol", method=RequestMethod.POST)
	public ResponseEntity<?> getAllTokenSymbol(@RequestParam String instrumentName){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<String>l=	fetchService.getAllTokenSymbol(instrumentName);
			return ResponseEntity.ok(l);			
		
	}
	
	@RequestMapping(value="/fetch/getAllExpiryDates", method=RequestMethod.POST)
	public ResponseEntity<?> getAllExpiryDates(@RequestParam String symbol){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<String>l=	fetchService.getAllExpiryDates(symbol);
			return ResponseEntity.ok(l);			
		
	}
	@RequestMapping(value="/fetch/getAllStrikesForTokenTable", method=RequestMethod.POST)
	public ResponseEntity<?> getAllStrikesForTokenTable(@RequestParam String symbol,
			@RequestParam String callPut, @RequestParam String expiryDate){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<String>l=	fetchService.getAllStrikesForTokenTable(symbol, callPut, expiryDate);
			return ResponseEntity.ok(l);			
		
	}
	
	@RequestMapping(value="/fetch/getTokenForRuleRow", method=RequestMethod.POST)
	public ResponseEntity<?> getTokenForRuleRow(@RequestParam String symbol,
			@RequestParam String callPut, @RequestParam String addTokenExpiry1,
			@RequestParam String instrument,@RequestParam String addTokenExpiry2,
			@RequestParam String strike,@RequestParam String expiry1Conversion, 
			@RequestParam String expiry2Conversion){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<String>l=	fetchService.getTokenForRuleRow(symbol, callPut, addTokenExpiry1, instrument,
											addTokenExpiry2, strike,expiry1Conversion,expiry2Conversion	);
			return ResponseEntity.ok(l);			
		
	}
	
	
	
	
	
	
	
	
	
}
