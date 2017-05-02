<%-- 
    Document   : save
    Created on : 30 Apr, 2017, 11:58:30 AM
    Author     : Pranav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Form</title>
        
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />

    </head>
    <body>
        <div class="container">
			<!-- Codrops top bar -->
          	
			<section class="af-wrapper">
	            <h3>Demographics</h3>
		        
				<input id="af-showreq" class="af-show-input" type="checkbox" name="showreq" />
			
				
                                <form class="af-form" id="af-form" action="/spring-data-mongo-cross-store/add" method="post">
					
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
							<input type="date" name="birthdate" id="input-bdate" placeholder="MM/DD/YYYY" />
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
                 <h3>Feedback</h3>
                    <div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-hearaboutus">How did you hear about us?</label>
							<input type="text" name="answer1" id="input-hearaboutus" >
						</div>
					</div>
                     <div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-helpful">Was the website helpful?</label>
							<input type="text" name="answer2" id="input-helpful" >
						</div>
					</div>
                    <div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-best">What was most useful?</label>
							<input type="text" name="answer3" id="input-best" >
						</div>
					</div>
                    
                   
                    
					 <div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-improve">How do you suggest we improve?</label>
							<input type="text" name="answer4" id="input-improve" >
						</div>
					</div>
                    
                    <div class="af-outer af-required">
						<div class="af-inner">
							<label for="input-message">Any Additional Message?</label>
							<input type="text" name="answer5" id="input-message" >
						</div>
					</div>
                    
					<input class="button" type="submit" value="Submit"/> 
					
				</form>
			</section>
        </div>
    </body>
</html>
