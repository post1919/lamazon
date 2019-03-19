package com.castingn.example;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.castingn.config.RootConfig;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)*/
public class MySQLConnectionTest {
/*	@Autowired
	private DataSource ds;
	
	@Test
	public void testConnection() throws Exception {
		try(Connection con = ds.getConnection()){
			System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
}
