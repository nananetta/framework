<%@ page language="java" contentType="text/html; charset=UTF-8"
    isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<h:head>
	<title>Page Error</title>
</h:head>
<h:body>
	<h4>
		<p>
		    <%=exception.getMessage().replace("Handler processing failed; nested exception is java.lang.Error:", "") %><br/>  
		</p>   
	</h4>
</h:body>
</html>