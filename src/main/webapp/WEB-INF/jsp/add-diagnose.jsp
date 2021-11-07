<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <p>Add new Diagnose:</p>
    <form:form method="post" modelAttribute="diagnose">

        <form:label path="patientId">patientId</form:label>
        <form:input path="patientId" type="text" required="required" />
        <form:errors path="patientId" />

        <form:label path="doctorId">doctorId</form:label>
        <form:input path="doctorId" type="text" required="required" />
        <form:errors path="doctorId" />

        <form:label path="diagnoseText">diagnose text</form:label>
        <form:input path="diagnoseText" type="text" required="required" />
        <form:errors path="diagnoseText" />

        <d:set var = "now" value ="<%=new java.util.Date()%>" />
        <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="myDate" />
        <form:label path="date">date</form:label>
        <form:input path="date" type="text" required="required" value="${myDate}"/>

        <form:errors path="date" />
        

        <button type="submit">OK</button>
    </form:form>
</div>