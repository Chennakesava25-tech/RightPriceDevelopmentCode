package com.rightprice.auth.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rightprice.auth.model.User;
import com.rightprice.auth.util.AppLoger;


@Transactional
@Repository
public class UserRepositoryImpl  implements UserRepository {
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate mysqlTemplate;
	
	@Autowired
	@Qualifier("mssqlJdbcTemplate")
	private JdbcTemplate mssqlTemplate;
	
	@Override
	public User findByUsername(String username) {
		
		AppLoger.APPLOGGER.info("==============================mysqlTemplate==================================");
		
		AppLoger.APPLOGGER.info(mysqlTemplate.queryForList("select * from user").toString());

		String getAdUserID = "SELECT DOB,userid,rmname FROM synprod.getempinformation where name='akash chandarwade'";
		
		SqlRowSet duhID = mssqlTemplate.queryForRowSet(getAdUserID);
		AppLoger.APPLOGGER.info("mssqlTemplate : " + mssqlTemplate);
		
		while(duhID.next())
		{
			AppLoger.APPLOGGER.info(duhID.getString("DOB"));
			AppLoger.APPLOGGER.info(duhID.getString("userid"));
			AppLoger.APPLOGGER.info(duhID.getString("rmname"));
		}

		
		AppLoger.APPLOGGER.info("===================================mysqlTemplate=============================");
		
		AppLoger.APPLOGGER.info("Store proc result..........");
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getAllCountries");
		AppLoger.APPLOGGER.info(query.getResultList().toString());
		AppLoger.APPLOGGER.info("Returning user......!");
		
		String hql = "FROM User where username = ?";
		try{
			return (User)entityManager.createQuery(hql).setParameter(1, username).getSingleResult();
		}catch(Exception e){
			AppLoger.APPLOGGER.info("exception   : " + e.getMessage());
			return null;
		}
	}

	@Override
	public void save(User user) {
		AppLoger.APPLOGGER.info("Saving user ..." + user.getUsername() + " with pass : " + user.getPassword());
		entityManager.persist(user);
	}

}
