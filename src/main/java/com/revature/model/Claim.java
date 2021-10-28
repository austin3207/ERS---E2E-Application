package com.revature.model;

public class Claim {
	
		private int claim_id;
		String userName;
		private double claim_amount;
		
		private String claim_reason;
		private String claim_status;
		
		public Claim(int claim_id, String userName, double claim_amount, String claim_reason, String claim_status) {
			super();
			this.claim_id = claim_id;
			this.userName = userName;
			this.claim_amount = claim_amount;
			this.claim_reason = claim_reason;
			this.claim_status = claim_status;
		}
		public Claim(String userName, double claim_amount, String claim_reason) {
			super();
			this.userName = userName;
			this.claim_amount = claim_amount;
			this.claim_reason = claim_reason;
			this.claim_status = "Pending";
		}
		public Claim() {
			super();
		}
		public double getClaim_amount() {
			return claim_amount;
		}
		public void setClaim_amount(double claim_amount) {
			this.claim_amount = claim_amount;
		}
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getClaim_reason() {
			return claim_reason;
		}

		public void setClaim_reason(String claim_reason) {
			this.claim_reason = claim_reason;
		}

		public String getClaim_status() {
			return claim_status;
		}

		public void setClaim_status(String claim_status) {
			this.claim_status = claim_status;
		}
		public int getClaim_id() {
			return claim_id;
		}
		public void setClaim_id(int claim_id) {
			this.claim_id = claim_id;
		}
		
		
		

}
