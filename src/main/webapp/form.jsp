<%-- 
    Document   : form
    Created on : 29 Apr, 2017, 12:36:45 AM
    Author     : Pranav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        	<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Form</title>
        
        <link href="<c:url value="/css/demo.css" />" rel="stylesheet">
        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
        

    </head>
    <body>
         <div class="container">
			<!-- Codrops top bar -->
          	
			<section class="af-wrapper">
	            <h3>Demographics</h3>
		        
				<input id="af-showreq" class="af-show-input" type="checkbox" name="showreq" />
			
				
                                <form class="af-form" id="af-form" action="form.jsp" method="post">
					
					<div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-title">Title</label>
							<input type="text" name="title" id="input-title">
						</div>
					</div>
				
					<div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-name">Name*</label>
							<input type="text" name="name" id="input-name" required>
						</div>
					</div>
					
					<div class="af-outer af-required">
						<div class="af-inner">
						  <label for="input-email">Email address*</label>
						  <input type="email" name="email" id="input-email" required>
						</div>
					</div>
					
					<div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-bdate">Birth Date</label>
							<input type="date" name="birthdate" id="input-bdate" placeholder="DD/MM/YYYY">
						</div>
					</div>
					
					<div class="af-outer af-required">
						<div class="af-inner">
						  <label for="input-country">Country*</label>
						  <input type="text" name="country" id="input-country" required>
						</div>
					</div>
					
					
					<div class="af-outer af-required">
						<div class="af-inner">
						  <label for="input-phone">Phone Number</label>
						  <input type="number" name="phonenumber" id="input-phone">
						</div>
					</div>
					
					<input type="submit" value="Submit"/> 
					
				</form>
			</section>                       
        </div>
    </body>
</html>


