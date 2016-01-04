<jsp:include page="includes/header.jsp"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <body>
        <h1>Event Details: </h1>
        <form action="<c:url value='/eventServlet/addEvent'/>" method="post">
            <input type="hidden" name="action" value="viewEvent"/>
            Event Host: <input type="text" name="host" value="${event.host}" /> <br/>
            Event Date: <input type="text" name="date" value="${event.date}" /> <br/>
            Event Description: <input type="text" name="description" value="${event.description}" /> <br/>
            <br/>
        </form>
        <form action="<c:url value='/eventServlet/castVote'/>" method="get">
            <input type="hidden" name="eventId" value="${event.id}"/>
            <input type="hidden" name="action" value="castVote" />
            <input type="submit" value="Cast Vote" ${voteNotAllowed ? 'disabled="disabled"': ''}/>
        </form>
        <form action="<c:url value='/eventServlet/selectEvents'/>" method="get">
            <input type="hidden" name="action" value="showEvents" />
            <input type="submit" value="Show Events" />            
        </form>
</section>
            
<jsp:include page="/includes/footer.jsp"/>           

