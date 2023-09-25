<%--
  Created by IntelliJ IDEA.
  User: LG
  Date: 2023-09-23
  Time: 오후 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.*" %>

<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>멤버생성</title>

</head>
<body>
<div>
    <div style="display: flex; justify-content: center;">
        <div style="border: 1px solid rgba(0,0,0,0.3); border-radius: 7px; margin-top: 30px; padding: 0px 30px;">
            <div style="margin: 40px 0px 40px 0px; display: flex; justify-content: center;">
                <h1>멤버 생성</h1>
            </div>
            <form method="post" id="signup-form" enctype="multipart/form-data">
                <input type="text" name="employeeNumber" style="display: none;" value="202311222">    <%--랜덤으로 사번 줌 앞자리만 년도--%>
                <input type="text" name="authority" style="display: none;" value="user"> <%--사원이냐 사이트 관리지냐 구분 전부 일반임--%>
                <div>
                    <div class="sign-column">
                        <div>
                            <span style="margin-right: 50px;">아이디 *</span>
                        </div>
                        <input type="text" class="sign-input" id="input-id" name="id" value="${employees.id}" maxlength="20" placeholder="id">
                        <input class="check-button" type="button" id="id-Confirm" value="중복확인">
                        <div id="idcheck-blank" style="margin-left: 10px;"></div>
                    </div>
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 35px;">비밀번호 * </span>
                    </div>
                    <input type="password" class="sign-input" id="input-password" name="password" placeholder="영문,숫자,특수문자 조합하여 8~16자리" maxlength="16">
                    <div style="margin-left: 10px;" id="pwdcheck-blank1"></div>
                </div>
                <div class="sign-column">
                    <div>
                        <span>비밀번호 확인 * </span>
                    </div>
                    <input  type="password" class="sign-input" id="password-check" name="password-check" placeholder="위와 동일하게 입력해주세요">
                    <div style="margin-left: 10px;" id="pwdcheck-blank2"></div>
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 65px;">이 름 * </span>
                    </div>
                    <input type="text" class="sign-input" id="input-name" name="name" value="${employees.name}" placeholder="한글 또는 영어로 입력해주세요" maxlength="20">
                    <div style="margin-left: 10px;" id="namecheck-blank"></div>
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 65px;">나 이</span>
                    </div>
                    <input type="text" class="sign-input" id="input-old" name="old" value="${employees.old}" maxlength="20">
                    <div style="margin-left: 10px;" id="oldcheck-blank"></div>
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 70px;">성 별 *</span>
                    </div>
                    <input type="radio" id="input-gender-m" name="gender" value="남" placeholder="">
                    <label for="input-gender-m">남성</label>
                    <input type="radio" id="input-gender-w" name="gender" value="여" placeholder="">
                    <label for="input-gender-w">여성</label>
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 35px;">휴대폰번호 </span>
                    </div>
                    <div style="display: flex;">
                        <input type="text" id="totalphone-num" name="phoneNumber" value="${employees.phoneNumber}">
                    </div>
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 20px;">이메일 주소 *</span>
                    </div>
                    <input type="text" id="totalemail" name="email" value="${employees.email}">
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 20px;">부 서</span>
                    </div>
                    <input type="text" name="department" value="${employees.department}">
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 20px;">부 서 번 호</span>
                    </div>
                    <input type="text" name="departmentNumber" value="${employees.departmentNumber}">
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 20px;">직 급</span>
                    </div>
                    <input type="text" name="position" value="${employees.position}">
                </div>
                <!--주소 등록-->
                <div class="sign-column">
                    <div style="margin-top: 10px; margin-right: 53px">
                        <div>
                            <span style="margin-right: 80px;">주 소</span>
                        </div>
                        <input class="sign-input-ad" type="text" name="address" id="address">
                    </div>
                </div>
                <%--사진등록--%>
                <div class="sign-column">
                    <div style="margin-top: 10px; margin-right: 53px">
                        <div>
                            <span style="margin-right: 80px;">사 진</span>
                        </div>
                        <label for="fileInput" class="form-label"></label>
                        <input class="form-control" type="file" name="files" id="fileInput" accept="image/*">
                    </div>
                </div>

                <div class="member-sign-column">
                    <input disabled type="submit" id="signup-submit" value="회원가입">
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
        integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="/js/employees/register.js"></script>
</body>
</html>
