/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voteapplication.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import voteapplication.business.CrowdUser;
import voteapplication.data.CrowdUserDB;

/**
 *
 * @author tanya
 */
public class UserServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        String actionURI = request.getRequestURI();
        String url = "";
        
        if(actionURI.endsWith("/deleteCookies")) {  //I used with the intent allow deletion of cookies later
            url = deleteCookies(request , response);
        }
        
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        String actionURI = request.getRequestURI();
        String url = "";
        
        if (actionURI.endsWith("/registerToVote")) {
            url = registerToVote(request,response);  
        }
        
        getServletContext()
          .getRequestDispatcher(url)
          .forward(request, response);    
    }

    private String registerToVote(HttpServletRequest request, HttpServletResponse response) {
        
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        System.out.println("REGISTERTOVOTE CALLED");
        
        CrowdUser crowdUser = new CrowdUser(email, firstName, lastName);
        request.setAttribute("crowdUser", crowdUser);
        String url;
        String message;
        
        if (CrowdUserDB.emailExists(email)){
            message = "Email address already in use. Please try again.";
            request.setAttribute("message", message);
            url = "/email/index.jsp";
        }else {
          CrowdUserDB.insert(crowdUser);
          message = "";
          request.setAttribute("message", message);
          url = "/email/thanks.jsp";
          request.getSession().setAttribute("crowdUser", crowdUser);
        }
        return url;
    }

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "/delete_cookies.jsp";
    }
    
    private String checkUserVote(HttpServletRequest request, HttpServletResponse response) {
        
        String email = request.getParameter("email");
        request.setAttribute(email, this);
                
        String message;
        
        if(email == null){            
            message = "We're sorry you are having trouble, "
            + "You have already voted." ;  
        }else{
            message = "You may cast a vote for this event!";
        }
        
           
           request.setAttribute("message", message);
        
        return "/event.jsp";
    }
    
}