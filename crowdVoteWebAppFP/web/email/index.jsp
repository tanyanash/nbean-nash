<jsp:include page="/includes/header.jsp" />
<%@taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>
<section>       
    <p>Sign up below to register your vote for an existing event.</p>
    
    <p><i>${message}</i></p>
    
    <form action="<h:url value='/userServlet/registerToVote'/>" method="post">
        <label>Email:</label>
        <input type="email" name="email" required><br>
        <label>First Name:</label>
        <input type="text" name="firstName" required><br>
        <label>Last Name:</label>
        <input type="text" name="lastName" required><br>
        <br/>
        <br/>
        <input type="submit" value="Register" id="submitButton">
    </form>
</section>

<jsp:include page="/includes/footer.jsp"/>