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
				<a href="<%=request.getContextPath()%>" class="navbar-brand"> User Registration Application </a>
			</div>
            <a href="<%=request.getContextPath()%>" class="btn btn-danger" role="button"  style="display: block; margin-left: auto;">Sign Out</a>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<caption>
					<h2>
						<c:if test="${user != null}">
            			    User Page
            		    </c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
					<input type="hidden" name="password" value="<c:out value='${user.password}' />" />
				</c:if>

                <fieldset class="form-group">
					<label>User Surname</label> <input type="text"
						value="<c:out value='${user.surname}' />" class="form-control"
						name="surname" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
                    <label>User Birthday</label> <input type="text"
                        value="<c:out value='${user.birthday}' />" class="form-control"
                        name="birthday" required="required">
                </fieldset>

				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Phone</label> <input type="text"
						value="<c:out value='${user.phone}' />" class="form-control"
						name="phone" required="required">
				</fieldset>


                <div class="text-center">
                    <a href="<%=request.getContextPath()%>/edit" class="btn btn-success" role="button">Edit</a>
                <div class="text-center"></div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>