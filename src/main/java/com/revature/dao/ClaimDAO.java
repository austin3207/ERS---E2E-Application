package com.revature.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revature.model.Claim;
import com.revature.util.DBConnection;



public class ClaimDAO {
	
	private List<Claim> claims = new ArrayList<Claim>(Arrays.asList(new Claim("abc", 50.55, "Technical replacement"),
			new Claim("def", 65.00, "Gas"), new Claim("ghi", 22.14,"Company lunch")));

	private static ClaimDAO claimDAO = null;

	private ClaimDAO() {
	}

	public static ClaimDAO instance() {
		if (claimDAO == null) {
			claimDAO = new ClaimDAO();
		}
		return claimDAO;
	}

	public List<Claim> getClaimByUserName(String userName) {
		setEmployeeClaims();
		List<Claim> temp = new ArrayList();
		for (Claim claim : claims) {
			if(claim.getUserName().contentEquals(userName)) {
				temp.add(claim);
			}
		}
		return temp;
	}

	public List<Claim> getAllClaims() {
		setEmployeeClaims();
		return claims;
	}

	public Claim addClaim(Claim claim) throws Exception {
		claims.add(claim);
		DBConnection.insertNewClaim(claim);
		return claim;
	}
	public void approveClaim(String claim_id, String approval) throws Exception {
		int id = Integer.parseInt(claim_id);
		for(int i = 0; i < claims.size(); i++) {
			if(id ==claims.get(i).getClaim_id()) {
				claims.get(i).setClaim_status(approval);
				DBConnection.updateClaim(claims.get(i));
				break;
			}
		}
	}
	public void setEmployeeClaims(){
		try {
			claims = DBConnection.findAllClaims();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
