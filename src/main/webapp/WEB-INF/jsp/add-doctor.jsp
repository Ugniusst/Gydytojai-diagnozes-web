<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
    <p>Add new Doctor:</p>
    <form:form method="post" modelAttribute="doctor">
        <form:label path="id">Id</form:label>
        <form:input path="id" type="text" required="required" />
        <form:errors path="id" />

        <form:label path="name">name</form:label>
        <form:input path="name" type="text" required="required" />
        <form:errors path="name" />

        <form:label path="telephoneNumber">Telephone Number</form:label>
        <form:input path="telephoneNumber" type="text" required="required" />
        <form:errors path="telephoneNumber" />


        <button type="submit">OK</button>
    </form:form>
</div>