/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voteapplication.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import voteapplication.business.*;

/**
 *
 * @author tanya
 */
public class EventVoteDB {
    
    public static int castVote(EventVote eventVote) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO EVENT_VOTE (EVENTID, CROWDUSERID) " +
                "Values (?, ?)";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setLong(1, Long.parseLong(eventVote.getId()));
            ps.setString(2, eventVote.getCrowduser());
            
            System.out.println("CAST VOTE");
           return ps.executeUpdate();
           
        }catch (SQLException e){
            System.out.println(e);
            e.printStackTrace();
            return 0;
        }finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
    
    public static Boolean checkUserVoteForEvent(int id, String crowdUserId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "Select *  FROM  EVENT_VOTE WHERE EVENTID = ? AND CROWDUSERID = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, crowdUserId);
            rs = ps.executeQuery();
             return rs.next();
        }catch (SQLException e){
            System.out.println(e);
            return true;  //if exception occurs don't allow user to vote.
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
