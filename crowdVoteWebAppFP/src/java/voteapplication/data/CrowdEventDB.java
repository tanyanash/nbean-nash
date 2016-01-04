/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voteapplication.data;

import java.sql.*;

import java.util.*;
import voteapplication.business.*;

/**
 *
 * @author tanya
 */
public class CrowdEventDB {
    
    
    
    
    public static boolean eventExists(String id){
        ConnectionPool  pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "Select EVENTID FROM CROWDVOTEEVENT Where EVENTID = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
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

    public static CrowdEvent selectEvent(String id) {
        ConnectionPool  pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "Select * FROM CROWDVOTEEVENT WHERE EVENTID = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            System.out.println("SELECTEVENT" + query);
            CrowdEvent event = null;
            if(rs.next()){
                event =  new CrowdEvent();
                event.setId(String.valueOf(rs.getInt("eventid")));
                event.setHost(rs.getString("eventhost"));
                event.setDate(rs.getString("eventdate"));
                event.setDescription(rs.getString("eventdescription"));
                System.out.println("SelectEvent" + event);
                
            }
            return event;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List <CrowdEvent> selectEvents(){
        ConnectionPool  pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "Select * FROM CROWDVOTEEVENT";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            List <CrowdEvent> events = new ArrayList<>();
            while(rs.next()) {
                CrowdEvent event = new CrowdEvent();
                event.setId(String.valueOf(rs.getInt("eventid")));
                event.setHost(rs.getString("eventhost"));
                event.setDate(rs.getString("eventdate"));
                event.setDescription(rs.getString("eventdescription"));
                
                events.add(event);
            }
            return events;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }   
    
}
