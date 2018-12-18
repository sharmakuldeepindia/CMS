package com.example.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.ContractTable;
import com.example.model.Role;
import com.example.model.User;

@Service
@Transactional
public class FetchService {
	@Autowired
	EntityManager em;

	public List<String> getAllInstrumentNames() {
		Query q = em.createQuery("select distinct instrumentName from ContractTable");
		List<String> l = (List<String>) q.getResultList();
		System.out.println(l);
		return l;
	}

	public List<String> getAllTokenSymbol(String instrument) {
		Query q = em.createQuery(
				"select distinct  symbol from ContractTable where instrumentName=:instrumentName "
				+ "order by symbol ");
		q.setParameter("instrumentName", instrument);
		List<String> l = (List<String>) q.getResultList();
		System.out.println(l);
		return l;
	}

	public List<String> getAllExpiryDates(String symbol) {
		Query q = em.createQuery(
				"Select distinct a.expiryDate from ContractTable  a where a.symbol= :symbol "
				+ "order by a.expiryDate asc ");
		q.setParameter("symbol", symbol);
		q.setMaxResults(11);
		List<String> l = (List<String>) q.getResultList();
		System.out.println(l);
		return l;
	}

	public List<String> getAllStrikesForTokenTable(String symbol, String callPut, String expiryDate) {
		Query q = em.createQuery("Select distinct a.strikePrice from ContractTable  "
				+ "a where  a.symbol= :symbol and a.optionType= :option and "
				+ "a.expiryDate= :expiry order by a.strikePrice ");
		q.setParameter("symbol", symbol);
		q.setParameter("option", callPut);
		q.setParameter("expiry", BigInteger.valueOf(Long.parseLong(expiryDate)));
		List<String> l = (List<String>) q.getResultList();
		System.out.println(l);
		return l;
	}

	public List<String> getTokenForRuleRow(String symbol, String callPut, String addTokenExpiry1, String instrument,
			String addTokenExpiry2, String strike, String expiry1Conversion, String expiry2Conversion) {

		List<String> l = new ArrayList<String>();
		if ((instrument).substring(0, 3).equals("FUT")) {
			Query q = em.createQuery(" Select a.token, a.name from ContractTable a "
					+ "where a.instrumentName in "
					+ "('FUTIDX', 'FUTSTK') and a.symbol=:symbol and a.expiryDate=:expiry");
			q.setParameter("symbol", symbol);
			q.setMaxResults(1);
			q.setParameter("expiry", BigInteger.valueOf(Long.parseLong(addTokenExpiry1)));
			List<Object[]> rs = q.getResultList();
			for (Object[] obj : rs) {
				l.add("" + obj[0]);
				l.add("" + obj[1]);
			}
		}

		else if ((instrument).substring(0, 3).equals("OPT")) {
			Query q = em.createQuery("select a.token, a.name from ContractTable a"
					+ " where a.instrumentName"
					+ " LIKE 'OPT%' and a.symbol=:symbol and a.optionType= :callPut"
					+ " and a.expiryDate= :expiry and a.strikePrice =:strike");
			q.setParameter("symbol", symbol);
			q.setParameter("callPut", callPut);
			q.setParameter("strike", ("" + Math.round(Float.parseFloat(strike) * 100)));
			q.setParameter("expiry", BigInteger.valueOf(Long.parseLong(addTokenExpiry2)));
			q.setMaxResults(1);
			List<Object[]> rs = q.getResultList();
			for (Object[] obj : rs) {
				l.add("" + obj[0]);
				l.add("" + obj[1]);

			}

		}
		System.out.println("this is the result:" + l);
		return l;
	}

	public void testJPACriteriaQuery() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root from = cq.from(ContractTable.class);
		cq.select(from);
		cq.where(cb.lessThan(from.get("id"), 20));
		TypedQuery<ContractTable> typedQuery = em.createQuery(cq).setMaxResults(10);
		List<ContractTable> resultlist = typedQuery.getResultList();
		for (ContractTable ct : resultlist) {
			System.out.println(ct);
		}
	}

	public void testUser() {
		Query q = em.createQuery("select u from User u");
		List<User> u = q.getResultList();
		for (User us : u) {
			Set<Role> rr = us.getRoles();
			System.out.println(us.getName());
			for (Role r : rr) {
				System.out.println(r.getRole());
			}
		}
	}

}
