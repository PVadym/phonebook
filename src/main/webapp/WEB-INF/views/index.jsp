<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <div class="btn-group pull-right">
        <a class="btn btn-s btn-success active" role="button" style="margin: 10px"
           href="<c:out value='/contact/add'/>">Add New Contact</a>
    </div>

    <table class="table table-striped table table-condensed">
        <thead>
        <tr>
            <th>Last name</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Mobile phone</th>
            <th>Home phone</th>
            <th>Address</th>
            <th>E-mail</th>
        </tr>
        <tr class='table-filters'>
            <td>
                <input/>
            </td>
            <td>
                <input/>
            </td>
            <td></td>
            <td>
                <input/>
            </td>
        </tr>
        </thead>
        <tbody id="fbody">
        <c:if test="${!empty contacts}">
        <c:forEach items="${contacts}" var="contact">
            <tr class='table-data'>
                <td><c:out value="${contact.lastName}"/></td>
                <td><c:out value="${contact.firstName}"/></td>
                <td><c:out value="${contact.middleName}"/></td>
                <td><c:out value="${contact.mobilePhone}"/></td>
                <td><c:out value="${contact.homePhone}"/></td>
                <td><c:out value="${contact.address}"/></td>
                <td><c:out value="${contact.email}"/></td>
                <td>
                    <div class="btn-group pull-right">

                        <a class="btn btn-xs btn-warning" role="button"
                           href="<c:url value='/contact/update/${contact.id}'/>">Edit</a>
                        <a class="btn btn-xs btn-danger" role="button"
                           href="<c:url value='/contact/delete/${contact.id}'/>">Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
<script src="filterTable.js"></script>
</body>
</html>
