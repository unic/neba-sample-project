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
    <!--[if lte IE 8]><script src="/neba-sample/static/css/ie/html5shiv.js"></script><![endif]-->
    <script src="/neba-sample/static/js/jquery.min.js"></script>
    <script src="/neba-sample/static/js/skel.min.js"></script>
    <script src="/neba-sample/static/js/init.js"></script>
    <script src="/neba-sample/static/js/contact.js" async></script>
    <noscript>
        <link rel="stylesheet" href="/neba-sample/static/css/skel.css" />
        <link rel="stylesheet" href="/neba-sample/static/css/style.css" />
        <link rel="stylesheet" href="/neba-sample/static/css/style-wide.css" />
    </noscript>
    <!--[if lte IE 8]><link rel="stylesheet" href="/neba-sample/static/css/ie/v8.css" /><![endif]-->
</head>
<body>

<sling:include path="header" resourceType="neba-sample/components/header" />

<!-- Main -->
<div id="main">

    <sling:include path="major" resourceType="neba-sample/components/major" />

    <div class="box alt container">
        <sling:getResource base="${resource}" path="sections" var="sections" />
        <sling:listChildren resource="${sections}" var="sectionList" />
        <c:forEach var="section" items="${sectionList}" varStatus="status">
            <sling:include resource="${section}" replaceSelectors="${status.count % 2 == 0 ? 'left' : 'right'}" />
        </c:forEach>
    </div>

    <sling:include path="footer" resourceType="neba-sample/components/footer" />
</div>

<!-- Footer -->
<div id="footer">
    <div class="container 75%">

        <sling:include path="lastMajor" resourceType="neba-sample/components/major" addSelectors="last"/>

        <div class="contact-form-placeholder" data-form-path="/bin/mvc.do/contact" ></div>

        <ul class="icons">
            <li><a href="https://twitter.com/nebaframework" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="https://www.github.com/unic/neba" class="icon fa-github"><span class="label">Github</span></a></li>
        </ul>

        <ul class="copyright">
            <li>&copy; neba.io. License: Creative Commons</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
        </ul>

    </div>
</div>

</body>
</html>