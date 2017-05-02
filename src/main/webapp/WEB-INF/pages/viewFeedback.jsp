<%-- 
    Document   : viewFeedback
    Created on : 2 May, 2017, 11:24:49 PM
    Author     : Pranav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset='UTF-8'>
	
	<title>Submitted feedback</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="stylesheet" href="css/style1.css">
</head>
    <body>        
        <h2>Submitted Feedback</h2>
        <table>
            <tr>
                <th>Question</th>
                <th>Answer</th>
            </tr>
            <tr>
                <td>How did you hear about us?</td>
                <td>${feedback.answer1}</td>
            </tr>
            <tr>
                <td>Was the website helpful?</td>                    
                <td>${feedback.answer2}</td>
            </tr>
            <tr>
                <td>What was most useful?</td>
                <td>${feedback.answer3}</td>
            </tr>
            <tr>
                <td>How do you suggest we improve?</td>
                <td>${feedback.answer4}</td>
            </tr>
            <tr>
                <td>Any Additional Message?</td>
                <td>${feedback.answer5}</td>
            </tr>
        </table>
    </body>
</html>
