package voteapplication.data;

import java.sql.*;


import voteapplication.business.*;
/**
 *
 * @author tanya
 */
public class CrowdUserDB {
    
    public static boolean emailExists(String email) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "Select Email  FROM  CROWDVOTEUSER Where EMAIL = ? ";
          
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            System.out.println("EMAIL EXIST CHECK");
            return rs.next();
        }catch (SQLException e){
            System.out.println(e);
            return false;
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int insert(CrowdUser crowdUser) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO CROWDVOTEUSER (EMAIL, FIRSTNAME, LASTNAME) " +
                "Values (?, ?, ?)";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, crowdUser.getEmail());
            ps.setString(2, crowdUser.getFirstName());
            ps.setString(3, crowdUser.getLastName());
            System.out.println("INSERT CROWDUSER");
           return ps.executeUpdate();
           
        }catch (SQLException e){
            System.out.println(e);
            return 0;
        }finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
    
    
}

