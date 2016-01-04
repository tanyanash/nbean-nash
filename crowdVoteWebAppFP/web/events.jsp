<jsp:include page="/includes/header.jsp"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <p><i>${voteMessage}</i></p>
        <table border="1">
            
            <thead>
                <th>Event Id</th>
                <th>Event Host </th>
                <th>Event Date</th>
                <th>Event Description</th>
                <th>Action</th>
            </thead>
            <tbody> 
                <c:forEach var="nextEvent" items="${requestScope.events}">
                <tr>
                    <td>${nextEvent.id}</td>
                    <td>${nextEvent.host}</td>
                    <td>${nextEvent.date}</td>
                    <td>${nextEvent.description}</td>
                    <td><a href="<c:url value='/eventServlet/selectEvent'/>?id=${nextEvent.id}">See more..</a></td>
                </tr>
                </c:forEach>
            </tbody>
        </table><br/>
</section>
<jsp:include page="/includes/footer.jsp"/>
