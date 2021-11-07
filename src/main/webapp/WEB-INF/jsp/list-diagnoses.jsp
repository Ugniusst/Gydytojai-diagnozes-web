<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<mvc:resources mapping="/resources/**" location="/resources/static/" />
<%@ include file="common/navigation.jspf"%>

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
        <th>Patient ID</th>
        <th>Doctor ID</th>
        <th>Doctor</th>
        <th>Diagnose</th>
        <th>Date</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${diagnoses}" var="diagnose">
        <tr>
            <td>${diagnose.id}</td>
            <td>${diagnose.patientId.id}</td>
            <td>${diagnose.doctorId.id}</td>
            <td>${diagnose.doctorId.name}</td>
            <td>${diagnose.diagnoseText}</td>
            <td>${diagnose.date}</td>
            <td><a type="button" href="/update-diagnose/${diagnose.id}">UPDATE</a></td>
            <td><a type="button" href="/delete-diagnose/${diagnose.id}">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <a href="add-diagnose">ADD diagnose</a>
</div>
</body>
</html>