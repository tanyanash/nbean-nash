<jsp:include page="/includes/header.jsp" />
<%@taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <h1>Thanks for joining Crowd Vote!</h1>


    <label>Email:</label>
    <span>${crowdUser.email}</span><br>
    <label>First Name:</label>
    <span>${crowdUser.firstName}</span><br>
    <label>Last Name:</label>
    <span>${crowdUser.lastName}</span><br>

   <form action="" method="post">
        <input type="hidden" name="action" value="registerToVote">
    </form>
    <br><br>
    
    <a href="<h:url value='/eventServlet/selectEvents'/>">View All Events.</a>
</section>

<jsp:include page="/includes/footer.jsp" />