<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <div class="col-lg-4 col-md-offset-4">
        <h4><b>Registration</b></h4>
        <div class="jumbotron">
            <form:form action="/register" method="post" modelAttribute="user">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" autofocus required placeholder="Username"/>
                    <div class="text-danger" role="alert">
                        <form:errors path="username"/>
                        <c:out value="${username_error}"/>
                    </div>
                </div>
                <div class="form-group ">
                    <input type="password" class="form-control" name="password" required placeholder="Password">
                    <div class="text-danger" role="alert">
                        <form:errors path="password"/>
                    </div>
                </div>
                <div class="form-group ">
                    <input type="text" class="form-control" name="fullName" required placeholder="Full Name">
                    <div class="text-danger" role="alert">
                        <form:errors path="fullName"/>
                    </div>
                </div>
                <div class="form-group ">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="submit" tabindex="3"
                                   class="form-control btn btn-success" value="Register">
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
