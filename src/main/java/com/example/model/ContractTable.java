package com.example.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ContractTable")
public class ContractTable {
	@Id
	int id;
	int token;
	int assetToken;
	String instrumentName;
	String symbol;
	String series;
	String reserve1;
	BigInteger expiryDate;
	String strikePrice;
	String optionType;
	int category;
	int cALevel;
	String reserve2;
	int reservedIdentifier;
	int permittedToTrade;
	BigInteger issueRate;
	int securityStatus;
	BigInteger eligibility;
	String reserved44;
	int securityStatus_copy1;
	BigInteger eligibility_copy1;
	String reserved44_copy1;
	int securityStatus_copy2;
	int eligibility_copy2;
	String reserved44_copy2;
	int securityStatus_copy3;
	BigInteger eligibility_copy3;
	String reserved44_copy3;
	BigInteger issueStartDate;
	BigInteger interestPaymentDate;
	BigInteger issueMaturityDate;
	BigInteger marginPercentage;
	BigInteger minimumLotQuantity;
	BigInteger boardLotQuantity;
	BigInteger tickSize;
	BigInteger issuedCapital;
	BigInteger freezeQuantity;
	BigInteger warningQuantity;
	BigInteger listingDate;
	BigInteger expulsionDate;
	BigInteger readmissionDate;
	BigInteger recordDate;
	int contractTablecol;
	BigInteger noDeliveryEndDate;
	BigInteger lowPriceRange;
	BigInteger highPriceRange;
	BigInteger exDate;
	BigInteger bookClosureStartDate;
	BigInteger bookClosureEndDate;
	BigInteger localLDBUpdateDateTime;
	BigInteger exerciseStartDate;
	BigInteger exerciseEndDate;
	int oldTokenNumber;
	int creditRating;
	String name;
	int eGMAGM;
	int interestDivident;
	int rightsBonus;
	int mFAON;
	String remarks;
	String exStyle;
	String exAllowed;
	String exRejectionAllowed;
	String plAllowed;
	String checkSum;
	int isCOrporateAdjusted;
	int symbolForAsset;
	BigInteger instrumentOfAsset;
	BigInteger basePrice;
	String deleteFlag;
	@Override
	public String toString() {
		return "ContractTable [id=" + id + ", token=" + token + ", assetToken=" + assetToken + ", instrumentName="
				+ instrumentName + ", symbol=" + symbol + "]";
	}
	
	
}
