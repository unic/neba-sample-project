<%@include file="../../../global.jspf" %>
<spring:message code="label_name" var="name" htmlEscape="true" text="Name"/>
<spring:message code="label_email" var="email" htmlEscape="true" text="Email"/>
<spring:message code="label_message" var="message" htmlEscape="true" text="Message"/>
<spring:message code="label_send_message" var="sendMessage" htmlEscape="true" text="Send message"/>

<form:form action="/bin/mvc.do/contact" method="post" id="contact" commandName="contact">
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