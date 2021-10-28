package com.revature.testDAO;

import java.util.List;

import com.revature.dao.ClaimDAO;
import com.revature.dao.EmployeeDAO;
import com.revature.model.Claim;
import com.revature.model.Employee;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestClaimDAO extends TestCase{
	
	/**
	 * 
	 * @param testName
	 */
	public TestClaimDAO( String testName )
    {
        super( testName );
    }
	/**
	 * @return 
	 */
	public static Test suite() {
		return new TestSuite(TestClaimDAO.class);
	}
	
	
	Claim claim = new Claim();
	public void testGetClaimByUsername() throws Exception {
		ClaimDAO dao = ClaimDAO.instance();
		List<Claim> list = dao.getClaimByUserName("abc");
		assertFalse(list.isEmpty());
		list = dao.getClaimByUserName("random-name");
		assertTrue(list.isEmpty());
	}
	
	public void testGetAllNames() throws Exception {
				
	}
}

