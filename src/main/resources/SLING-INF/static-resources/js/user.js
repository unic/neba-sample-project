$(function () {
    $.get("/bin/neba-sample/userinfo").done(function (user) {
        $("#userInfo").text(user.name);
    }).fail(function () {
        $("#userInfo").text("anonymous");
    });
});
