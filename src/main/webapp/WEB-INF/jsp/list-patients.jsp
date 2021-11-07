<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<mvc:resources mapping="/resources/**" location="/resources/static/" />
<%@ include file="common/navigation.jspf"%>

<html>
<head>
    <title>View patients</title>
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
    <c:forEach items="${patients}" var="patient">
        <tr>
            <td>${patient.id}</td>
            <td>${patient.name}</td>
            <td>${patient.telephoneNumber}</td>
            <td><a type="button" href="/update-patient/${patient.id}">UPDATE</a></td>
            <td><a type="button" href="/delete-patient/${patient.id}">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <a href="add-patient">ADD patient</a>
</div>
</body>
</html>