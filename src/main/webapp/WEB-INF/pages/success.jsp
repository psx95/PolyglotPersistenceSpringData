<%-- 
    Document   : newjsp
    Created on : 2 May, 2017, 1:06:16 PM
    Author     : Pranav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset='UTF-8'>
	
	<title>Submitted Response</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="stylesheet" href="css/style1.css">
</head>

<body>

	<div id="page-wrap">

	
            <h1>Data Successfully Submitted</h1>
            <h3>Your Submitted Response is</h3>
            <p><strong>DEMOGRAPHICS</strong></p>
		
	<table>
		<tr>
                        <th>Auto Generated ID</th>
			<th>Title</th>
			<th>Name</th>
			<th>Email Address</th>
			<th>Birth Date</th>
			<th>Country</th>
			<th>Phone Number</th>			
		</tr>
		<tr>
			<td>${id}</td>
			<td>${title}</td>
			<td>${name}</td>
			<td>${email}</td>
			<td>${birthdate}</td>
			<td>${country}</td>
			<td>${phone}</td>			
		</tr>
<!--		<tr>
		  <td>The</td>
		  <td>Tick</td>
		  <td>Crimefighter Sorta</td>
		  <td>Blue</td>
		  <td>Wars</td>
		  <td>John Smith</td>
		  <td>July 19, 1968</td>
		  <td>Athens</td>
		  <td>N/A</td>
		  <td>Edlund, Ben (July 1996).</td>
		</tr>
		<tr>
		  <td>Jokey</td>
		  <td>Smurf</td>
		  <td>Giving Exploding Presents</td>
		  <td>Smurflow</td>
		  <td>Smurf</td>
		  <td>Smurflane Smurfmutt</td>
		  <td>Smurfuary Smurfteenth, 1945</td>
		  <td>New Smurf City</td>
		  <td>4.Smurf</td>
		  <td>One</td>
		</tr>
		<tr>
		  <td>Cindy</td>
		  <td>Beyler</td>
		  <td>Sales Representative</td>
		  <td>Red</td>
		  <td>Wars</td>
		  <td>Lori Quivey</td>
		  <td>July 5, 1956</td>
		  <td>Paris</td>
		  <td>3.4</td>
		  <td>3451</td>
		</tr>
		<tr>
		  <td>Captain</td>
		  <td>Cool</td>
		  <td>Tree Crusher</td>
		  <td>Blue</td>
		  <td>Wars</td>
		  <td>Steve 42nd</td>
		  <td>December 13, 1982</td>
		  <td>Las Vegas</td>
		  <td>1.9</td>
		  <td>Under the couch</td>
		</tr>-->
	</table>
                <p><strong>USER FEEDBACK</strong></p>
                <table>
                    <tr>
                        <th>Question</th>
			<th>Answer</th>						
                    </tr>
                    <tr>
                        <td>How did you hear about us?</td>
                        <td>${answer1}</td>
                    </tr>
                    <tr>
                        <td>Was the website helpful?</td>                    
                        <td>${answer2}</td>
                    </tr>
                    <tr>
                        <td>What was most useful?</td>
                        <td>${answer3}</td>
                    </tr>
                    <tr>
                        <td>How do you suggest we improve?</td>
                        <td>${answer4}</td>
                    </tr>
                    <tr>
                        <td>Any Additional Message?</td>
                        <td>${answer5}</td>
                    </tr>
                </table>
                    
                    <br><br>
                <div>
                    <form action="/spring-data-mongo-cross-store/redirect" method="post">
                        <input type="hidden" name="location" value="index"/>
                        <input type="submit" value="Submit Another Response"/>
                    </form>
                    <br>
                    <br>
                    <form action="/spring-data-mongo-cross-store/viewAll" method="post">
                        <input type="hidden" name="location" value="viewResponse"/>
                        <input type="submit" value="View All Responses"/>
                    </form>
                    <form action="/spring-data-mongo-cross-store/viewUnstructured" method="post">
                        <input type="hidden" name="location" value="viewUnstructured" />
                        <input type="submit" value="View The MongoDB Collection" />
                    </form>                  
                </div>                    
	</div>                
</body>

</html>
