<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <form  action="/spring-data-mongo-cross-store/save" method="get">
            Name:
            <input type="text" name="name" />            
            Serial:
            <input type="number" name="serial" value="" required/>
            <input type="submit" value="Save"/>
        </form>
    </body>
    
</html>