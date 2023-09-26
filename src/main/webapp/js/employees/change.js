let password_check = true;
let password = true;

//===============================유효성 모두 완료되면 수정 가능=======================================
function modiEnableSubmit() {
    if (password_check) {
        $("#modify-Button").removeAttr("disabled").css("cursor", "pointer");
    } else {
        $("#modify-Button").attr("disabled", "");
    }
}

//==============================비밀번호 유효성검사 ==============================================
$("#input-password").keyup(function() {
    let pwdCheck= /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    password_check = false;

    if ($("#input-password").val() == "") {
        $("#pwdcheck-blank1").css("color", "red");
        $("#pwdcheck-blank1").text("패스워드를 입력해주세요.");
        password = false;
        modiEnableSubmit();
    }
    else if (!pwdCheck.test($("#input-password").val())) {
        $("#pwdcheck-blank1").css("color", "red");
        $("#pwdcheck-blank1").text("비밀번호는 영문+숫자+특수문자 조합하여 8~16자리를 사용해야 합니다");
        password = false;
        modiEnableSubmit();
    }else {
        $("#pwdcheck-blank1").css("color", "blue");
        $("#pwdcheck-blank1").text("사용 가능한 비밀번호 입니다. 비밀번호를 확인해주세요");
        password = true;
        modiEnableSubmit();
    }


});

//==============================비밀번호 확인 ==============================================
$("#password-check").blur(function() {

    password_check = false;
    if($("#password-check").val() === "") {
        $("#pwdcheck-blank2").css("color", "red");
        $("#pwdcheck-blank2").text("패스워드를 입력해주세요.");
        password_check = false;
        modiEnableSubmit();
    }
    else if(password === true && $("#input-password").val() === $("#password-check").val()) {
        $("#pwdcheck-blank2").css("color", "blue");
        $("#pwdcheck-blank2").text("비밀번호가 일치합니다.");
        password_check = true;
        modiEnableSubmit();
    }else {
        $("#pwdcheck-blank2").css("color", "red");
        $("#pwdcheck-blank2").text("비밀번호를 다시 확인해주세요.");
        $("#password-check").val("");
        password_check = false;
        modiEnableSubmit();
    }

});