/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voteapplication.admin;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import voteapplication.business.CrowdEvent;
import voteapplication.business.CrowdUser;

import voteapplication.business.EventVote;
import voteapplication.data.CrowdEventDB;

import voteapplication.data.EventVoteDB;


/**
 *
 * @author tanya
 */
public class EventServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String actionURI = request.getRequestURI();
        
     
        String url = "";
                
        if(actionURI.endsWith("/selectEvents")) { 
            
            url = selectEvents(request, response);
           
        }else if(actionURI.endsWith("/selectEvent")) { 
            
            url = selectEvent(request, response);
            
        }else if(actionURI.endsWith("/castVote")){
            url = castVote(request, response);
        }
         
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.jsp";
        
        // get current action
        String actionURI = request.getParameter("action");
        if (actionURI == null) {
            actionURI = "selectEvents";  // default action
        }
                
       if (actionURI.equals("event")) {
            
            url = "/index.jsp";    
        } 
        else if (actionURI.equals("addEvent")) {                
            // get parameters from the request
            String eventId  = request.getParameter("id");
            String eventHost = request.getParameter("eventHost");
            String eventDate = request.getParameter("eventDate");
                        
//            CrowdEvent crowdEvent = new CrowdEvent(eventId, eventHost, eventDate);
//            CrowdEventDB.(crowdEvent);
            
//            request.setAttribute("crowdEvent", crowdEvent);
            
        }

            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    private String selectEvent(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("id");
        CrowdEvent event = CrowdEventDB.selectEvent(id);
        String message;
        
        if(id == null){            
            message = "We're sorry you are having trouble, "
            + "it appears the host has not registered this event with Crowd Event." ;  
        }else{
            message = "You may cast a vote for this event!";
        }
        CrowdUser crowdUser = (CrowdUser) request.getSession().getAttribute("crowdUser");
        boolean voteNotAllowed = EventVoteDB.checkUserVoteForEvent(Integer.valueOf(id), crowdUser.getEmail());
        request.setAttribute("voteNotAllowed", voteNotAllowed);
        
           request.setAttribute("event", event);
           request.setAttribute("message", message);
        
        return "/event.jsp";
    }
    
    
    private String selectEvents(HttpServletRequest request, HttpServletResponse response) {
        List<CrowdEvent> events = (List) CrowdEventDB.selectEvents();
        request.setAttribute("events", events);
        
        return "/events.jsp";
        
    }

    private String castVote(HttpServletRequest request, HttpServletResponse response) {
        
        String eventId = request.getParameter("eventId").trim();
        CrowdUser crowdUser = (CrowdUser) request.getSession().getAttribute("crowdUser");
        System.out.println("EventId " + eventId);
        System.out.println("Crowduser " + crowdUser.getEmail());
        EventVote eventVote = new EventVote(eventId, crowdUser.getEmail());
        int success = EventVoteDB.castVote(eventVote);
        System.out.println("Success " + success);
       if(success > 0){
           request.setAttribute("voteMessage", "Vote Successful");
       }else {
           request.setAttribute("voteMessage", "Vote Unsuccessful");
       }
       return selectEvents(request, response);
    }
}
