<%@include file="../../global.jspf" %>
<!DOCTYPE HTML>
<!--
Directive by HTML5 UP
html5up.net | @n33co
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>${m.header.title}</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <!--[if lte IE 8]><script src="/adaptto/static/css/ie/html5shiv.js"></script><![endif]-->
    <script src="/adaptto/static/js/jquery.min.js"></script>
    <script src="/adaptto/static/js/skel.min.js"></script>
    <script src="/adaptto/static/js/init.js"></script>
    <script src="/adaptto/static/js/jquery.blockUI.js"></script>
    <script src="/adaptto/static/js/loginlogout.js" async></script>
    <script src="/adaptto/static/js/forms.js" async></script>
    <script src="/adaptto/static/js/contact.js" async></script>
    <noscript>
        <link rel="stylesheet" href="/adaptto/static/css/skel.css" />
        <link rel="stylesheet" href="/adaptto/static/css/style.css" />
        <link rel="stylesheet" href="/adaptto/static/css/style-wide.css" />
    </noscript>
    <!--[if lte IE 8]><link rel="stylesheet" href="/adaptto/static/css/ie/v8.css" /><![endif]-->
</head>
<body>

<%@include file="topbar.jspf"%>

<sling:include path="header" resourceType="adaptto/components/header" />

<!-- Main -->
<div id="main">

    <sling:include path="major" resourceType="adaptto/components/major" />

    <div class="box alt container">
        <sling:getResource base="${resource}" path="sections" var="sections" />
        <sling:listChildren resource="${sections}" var="sectionList" />
        <c:forEach var="section" items="${sectionList}" varStatus="status">
            <sling:include resource="${section}" replaceSelectors="${status.count % 2 == 0 ? 'left' : 'right'}" />
        </c:forEach>
    </div>

    <sling:include path="footer" resourceType="adaptto/components/footer" />
</div>

<!-- Footer -->
<div id="footer">
    <div class="container 75%">

        <sling:include path="lastMajor" resourceType="adaptto/components/major" addSelectors="last"/>

        <sling:include path="contact" resourceType="adaptto/components/contact" />

        <ul class="icons">
            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
            <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
            <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
        </ul>

        <ul class="copyright">
            <li>&copy; Sling @ Java-Magazin. License: Creative Commons</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
        </ul>

    </div>
</div>

</body>
</html>