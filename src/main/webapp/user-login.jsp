<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>User Registration Application</title>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<body>
    <header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #9d32ff">
			<div>
				<a href="<%=request.getContextPath()%>/" class="navbar-brand"> User Registration Application </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			    <c:if test="${user == null}">
			    <form action="check" method="post">
			    </c:if>
                    <fieldset class="form-group">
                        <label>Login</label> <input type="email"
                                class="form-control"
						    value="<c:out value='${user.email}' />"
                            name="login" placeholder="email" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="text"
                                class="form-control"
						value="<c:out value='${user.password}' />"
                            name="password" required="required">
                    </fieldset>
                    <c:if test="${error != null}">
                        <font color="red"> <c:out value='${error}' /></font>
                    </c:if>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success">Sign in</button>
                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success" role="button">Sign up</a>
                    <div class="text-center"></div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>