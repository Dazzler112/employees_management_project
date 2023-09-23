let checkId = false;
let password_check = false;
let name = false;
let gender = false;
//===============================유효성 모두 완료되면 가입가능======================================
function enableSubmit() {
    if (checkId && password_check && name && gender ) {
        $("#signup-submit").removeAttr("disabled").css("cursor", "pointer");
        $("#need-sign").hide();
    } else {
        $("#signup-submit").attr("disabled", "");
        $("#need-sign").show();
    }
}
//==============================아이디 유효성검사 ================================================
$("#input-id").blur(function (){
    let idCheck = /^(?=.*[a-zA-Z])[a-zA-Z0-9]{6,20}$/;

    if($("#input-id").val() == "") {
        $("#idcheck-blank").css("color", "red");
        $("#idcheck-blank").text("! 아이디는 필수 입력");
        id = false;
        enableSubmit();
    }else if(!idCheck.test($("#input-id").val())) {
        $("#idcheck-blank").css("color", "red");
        $("#idcheck-blank").text("! 영문 또는 영문 숫자 조합하여 6~20자만 가능");
        id = false;
        enableSubmit();
    }else {
        $("#idcheck-blank").css("color", "blue");
        $("#idcheck-blank").text("사용 가능한 아이디입니다. 중복확인을 해주세요.");
        id = true;
        enableSubmit();
    }
    if(id == true) {
        $("#id-Confirm").show();
    }else {
        $("#id-Confirm").hide();
    }
});

//==============================아이디 중복검사 ==============================================
$("#id-Confirm").click(function() {
    const id = $("#input-id").val();
    if (id == "") {
        alert("아이디를 입력해주세요.");
    } else {
        $.ajax({
            url: "/employees/checkId/" + id,
            type: "get",
            success: function(data) {
                if (data.available) {
                    $("#idcheck-blank").css("color", "blue");
                    $("#idcheck-blank").text("사용가능한 아이디입니다.");
                    checkId = true;
                } else {
                    $("#idcheck-blank").css("color", "red");
                    $("#idcheck-blank").text("! 중복된 아이디입니다.");
                    checkId = false;
                }
            },
            error: function() {
                alert("오류가 발생했습니다. 다시 시도해주세요.");
            }
            ,complete: enableSubmit
        });
    }
});

//==============================비밀번호 유효성검사 ==============================================
$("#input-password").blur(function() {
    let pwdCheck= /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,16}$/;

    if ($("#input-password").val() == "") {
        $("#pwdcheck-blank1").css("color", "red");
        $("#pwdcheck-blank1").text("! 패스워드를 입력해주세요.");
        password = false;
    }
    else if (!pwdCheck.test($("#input-password").val())) {
        $("#pwdcheck-blank1").css("color", "red");
        $("#pwdcheck-blank1").text("! 비밀번호는 영문+숫자+특수문자 조합하여 6~16자리를 사용해야 합니다");
        password = false;
    }else {
        $("#pwdcheck-blank1").css("color", "blue");
        $("#pwdcheck-blank1").text("사용 가능한 비밀번호 입니다. 비밀번호를 확인해주세요");
        password = true;
    }


});

//==============================비밀번호 확인 ==============================================
$("#password-check").blur(function() {
    if($("#password-check").val() == "") {
        $("#pwdcheck-blank2").css("color", "red");
        $("#pwdcheck-blank2").text("! 패스워드를 입력해주세요.");
        password_check = false;
        enableSubmit();
    }
    else if(password == true && $("#input-password").val() == $("#password-check").val()) {
        $("#pwdcheck-blank2").css("color", "blue");
        $("#pwdcheck-blank2").text("비밀번호가 일치합니다.");
        password_check = true;
        enableSubmit();
    }else {
        $("#pwdcheck-blank2").css("color", "red");
        $("#pwdcheck-blank2").text("! 비밀번호를 다시 확인해주세요.");
        $("#password-check").val("");
        password_check = false;
        enableSubmit();
    }

});

//==============================이름 확인 ==============================================
$("#input-name").blur(function() {
    let nameCheck = /^[가-힣]{2,20}$/;

    if ($("#input-name").val() == "") {
        $("#namecheck-blank").css("color", "red");
        $("#namecheck-blank").text("! 이름은 필수 입력입니다.");
        name = false;
        enableSubmit();
    } else if (!nameCheck.test($("#input-name").val())) {
        $("#namecheck-blank").css("color", "red");
        $("#namecheck-blank").text("! 이름은 한글로 이루어져야 하며, 2자에서 20자 사이여야 합니다.");
        name = false;
        enableSubmit();
    } else {
        $("#namecheck-blank").css("color", "blue");
        $("#namecheck-blank").text("유효한 이름입니다.");
        name = true;
        enableSubmit();
    }
});

//================================성 별 =========================================

$("#input-gender-m").change(function (){
    gender = true;
    enableSubmit();
})

$("#input-gender-w").change(function (){
    gender = true;
    enableSubmit();
})