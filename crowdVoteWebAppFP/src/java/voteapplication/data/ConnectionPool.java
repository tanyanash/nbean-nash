/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voteapplication.data;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author tanya
 */
public class ConnectionPool {
    
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool()
    {
        try
        {     
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/crowdapplicationDB");
            System.out.println("CONNECTIONPOOL CROWDAPPLCATIONDB");
        } catch(NamingException e) 
        {
        System.out.println(e);
        }
    }
        
    public static synchronized ConnectionPool getInstance() 
    {
        if(pool == null) {
            pool = new ConnectionPool();
            System.out.println("NEW CONNECTIONPOOL POOL");
        }
        return pool;
    }
        
    public Connection getConnection() 
    {
    
        try 
        {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
        
    public void freeConnection(Connection c) {
        try 
        {
            c.close();
            System.out.println("CLOSE CONNECTION");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
        
//        public class RuntimeSQLException extends RuntimeException {
//    public RuntimeSQLException(Throwable cause) {
//        super(cause);
//        }
//    }
}
