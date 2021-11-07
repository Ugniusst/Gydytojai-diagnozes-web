<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <p>Add new patient:</p>
    <form:form method="post" modelAttribute="patient">


        <form:label path="name">name</form:label>
        <form:input path="name" type="text" required="required" />
        <form:errors path="name" />

        <form:label path="telephoneNumber">Telephone Number</form:label>
        <form:input path="telephoneNumber" type="text" required="required" />
        <form:errors path="telephoneNumber" />


        <button type="submit">OK</button>
    </form:form>
</div>