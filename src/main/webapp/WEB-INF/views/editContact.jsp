<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Contact</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Edit this contact</b></h4>
    <div class="panel-body">

        <div class="jumbo">
            <div class="row">

                <div class="col-lg-12">
                    <form:form class="form-horizontal" action="/contact/edit" method="get" role="form"
                               style="display: block;" modelAttribute="contact">
                        </div>
                <div class="form-group" hidden>
                    <div class="col-sm-10">
                        <input type="text" name="username" class="form-control"
                               value="${pageContext.request.userPrincipal.name}">

                    </div>
                </div>
                <div class="form-group" hidden>
                    <label class="control-label col-sm-2" for="id">Id</label>
                    <div class="col-sm-10">
                        <input type="text" name="id" id="id"  class="form-control"
                               value="${contact.id}">
                    </div>
                    </div>
                    <div class="form-group" hidden>
                        <label class="control-label col-sm-2" for="user.id">Id</label>
                        <div class="col-sm-10">
                            <input type="text" name="user.id" id="user.id"  class="form-control"
                                   value="${contact.user.id}">
                        </div>
                </div>
                        <br>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="lastName">Last Name</label>
                            <div class="col-sm-10">
                                <input type="text" name="lastName" id="lastName" tabindex="1" class="form-control"
                                       placeholder="Last Name" required value="${contact.lastName}">
                                <div class="text-danger" role="alert">
                                    <form:errors path="lastName"></form:errors>
                                </div>
                            </div>
                        </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="firstName">First Name</label>
                    <div class="col-sm-10">
                        <input type="text" name="firstName" id="firstName" tabindex="1" class="form-control"
                               placeholder="First Name" requiredvalue="${contact.firstName}">
                        <div class="text-danger" role="alert">
                            <form:errors path="firstName"></form:errors>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="middleName">Middle Name</label>
                    <div class="col-sm-10">
                        <input type="text" name="middleName" id="middleName" tabindex="1" class="form-control"
                               placeholder="Middle Name" required value="${contact.middleName}">
                        <div class="text-danger" role="alert">
                            <form:errors path="firstName"></form:errors>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="mobilePhone">Mobile Phone</label>
                    <div class="col-sm-10">
                        <input type="text" name="mobilePhone" id="mobilePhone" tabindex="1" class="form-control"
                               placeholder="Mobile Phone" required value="${contact.mobilePhone}">
                        <div class="text-danger" role="alert">
                            <form:errors path="mobilePhone"></form:errors>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="homePhone">Home Phone</label>
                    <div class="col-sm-10">
                        <input type="text" name="homePhone" id="homePhone" tabindex="1" class="form-control"
                               placeholder="Home Phone" value="${contact.homePhone}">
                        <div class="text-danger" role="alert">
                            <form:errors path="homePhone"></form:errors>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="address">Address</label>
                    <div class="col-sm-10">
                        <input type="text" name="address" id="address" tabindex="1" class="form-control"
                               placeholder="Address" value="${contact.address}">
                        <div class="text-danger" role="alert">
                            <form:errors path="address"></form:errors>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">E-mail</label>
                    <div class="col-sm-10">
                        <input type="text" name="email" id="email" tabindex="1" class="form-control"
                               placeholder="E-mail" value="${contact.email}">
                        <div class="text-danger" role="alert">
                            <form:errors path="email"></form:errors>
                        </div>
                    </div>
                </div>


                        <div class="form-group ">
                            <div class="row">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <input type="submit" tabindex="5"
                                           class="form-control btn-success" value="Submit">
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="row">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <input type="reset" tabindex="6"
                                           class="form-control btn-danger" value="Reset">
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
