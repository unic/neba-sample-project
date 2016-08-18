<%@include file="../../global.jspf" %>
<c:set var="linkedPage" value="${m.linkedPage}" />
<!-- Header -->
<div id="header">
    <span class="logo icon fa-paper-plane-o"></span>
    <h1>${properties.title}</h1>
    <p>
        <c:if test="${!empty(linkedPage)}"><a href="${linkedPage.path}.html" title="${linkedPage.header.title}"></c:if>
        ${properties.text}
        <c:if test="${!empty(linkedPage)}"></a></c:if>
    </p>
</div>