<%-- 
    Document   : viewResponse
    Created on : 2 May, 2017, 10:44:07 AM
    Author     : Pranav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    
<head>
	<meta charset='UTF-8'>
	
	<title>All Responses</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="stylesheet" href="css/style1.css">
</head>
<body>
	<h2>User Responses</h2>

        <table>
                <tr>
                        <th>Auto Generated ID</th>
			<th>Title</th>
			<th>Name</th>
			<th>Email Address</th>
			<th>Birth Date</th>
			<th>Country</th>
			<th>Phone Number</th>
                        <th>View Feedback</th>
		</tr>
                <c:if test="${not empty lists}">

                        
                        <c:forEach var="listValue" items="${lists}">
                            <tr>
                                <td><c:out value="${listValue.id}"/></td>                                
                                <td>><c:out value="${listValue.title}"/></td>
                                <td><c:out value="${listValue.name}"/></td>
                                <td><c:out value="${listValue.email}"/></td>
                                <td><c:out value="${listValue.birthdate}"/></td>
                                <td><c:out value="${listValue.country}"/></td>
                                <td><c:out value="${listValue.phonenumber}"/></td>                                
                                <td>
                                    <form action="/spring-data-mongo-cross-store/viewFeedback" method="get">
                                        <input type="hidden" name ="id" value="${listValue.id}" />
                                        <input type="submit" value="View Feedback" />
                                    </form>                                    
                                </td>
                            </tr>                            
                            <tr>
<!--                                <table>
                                        <tr>
                                            <th>Question</th>
                                            <th>Answer</th>						
                                        </tr>
                                        <tr>
                                            <td>How did you hear about us?</td>
                                            <td>${listValue.feedback.answer1}</td>
                                        </tr>
                                        <tr>
                                            <td>Was the website helpful?</td>                    
                                            <td>${listValue.feedback.answer2}</td>
                                        </tr>
                                        <tr>
                                            <td>What was most useful?</td>
                                            <td>${listValue.feedback.answer3}</td>
                                        </tr>
                                        <tr>
                                            <td>How do you suggest we improve?</td>
                                            <td>${listValue.feedback.answer4}</td>
                                        </tr>
                                        <tr>
                                            <td>Any Additional Message?</td>
                                            <td>${listValue.feedback.answer5}</td>
                                        </tr>
                                </table>-->
                            </tr>
                            <hr>
                            <hr>
                        </c:forEach>                        


                </c:if>
        </table>
</body>
</html>
