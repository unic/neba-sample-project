<%@include file="../../global.jspf" %>

<p>${properties.text}</p>

<form method="post" action="<c:url value="/bin/mvc.do/contact"/>" id="contact" data-success-path="${resource.path}.success.html">
    <input type="hidden" name="comment" value="${resource.path}">
    <div class="row">
        <div class="6u 12u(mobilep)">
            <input type="text" name="name" placeholder="Name" />
        </div>
        <div class="6u 12u(mobilep)">
            <input type="email" name="email" placeholder="Email" />
        </div>
    </div>
    <div class="row">
        <div class="12u">
            <textarea name="message" placeholder="Message" rows="6"></textarea>
        </div>
    </div>
    <div class="row">
        <div class="12u">
            <ul class="actions">
                <li><input type="submit" value="Send Message" /></li>
            </ul>
        </div>
    </div>
</form>