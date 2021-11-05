<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<mvc:resources mapping="/resources/**" location="/resources/static/" />

<html>
<head>
    <title>View doctors</title>
    <link href="/css/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>TelephoneNumber</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${doctors}" var="doctor">
        <tr>
            <td>${doctor.id}</td>
            <td>${doctor.name}</td>
            <td>${doctor.telephoneNumber}</td>
            <td><a type="button" href="/update-doctor/${doctor.id}">UPDATE</a></td>
            <td><a type="button" href="/delete-doctor/${doctor.id}">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <a href="add-doctor">ADD doctor</a>
</div>
</body>
</html>