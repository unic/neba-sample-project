<%@page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="label_name" var="name" htmlEscape="true" text="Name"/>
<spring:message code="label_email" var="email" htmlEscape="true" text="Email"/>
<spring:message code="label_message" var="message" htmlEscape="true" text="Message"/>
<spring:message code="label_send_message" var="sendMessage" htmlEscape="true" text="Send message"/>
<%--
    Demonstrates how the Spring Form JSP tag library can be used in Sling views.
    Note: In order to work, this view MUST be provided by a request to a Spring controller -
    otherwise, the MVC context required by the Spring form tag library is not available.

    That is, this view is part of a resource (in a REST sense) that a Spring controller is responsible for.
--%>
<form:form action="/bin/mvc.do/contact" method="post" id="contact" modelAttribute="contact">
    <div class="row">
        <div class="6u 12u(mobilep)">
            <form:errors path="name" cssClass="error"  />
            <form:input path="name" autocomplete="true" placeholder="${name}" />
        </div>
        <div class="6u 12u(mobilep)">
            <form:errors path="email" cssClass="error" />
            <form:input path="email" autocomplete="true" placeholder="${email}" />
        </div>
    </div>
    <div class="row">
        <div class="12u">
            <form:errors path="message" cssClass="error"  />
            <form:textarea path="message" rows="6" placeholder="${message}" />
        </div>
    </div>
    <div class="row">
        <div class="12u">
            <ul class="actions">
                <li><input type="submit" value="${sendMessage}" /></li>
            </ul>
        </div>
    </div>
</form:form>