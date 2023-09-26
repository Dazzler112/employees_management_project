<%--
  Created by IntelliJ IDEA.
  User: LG
  Date: 2023-09-26
  Time: 오후 11:26
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
    <title>정보 수정</title>

</head>
<body>

<div>
    <div style="display: flex; justify-content: center;">
        <div style="border: 1px solid rgba(0,0,0,0.3); border-radius: 7px; margin-top: 30px; background-color: #fafafa; padding: 0px 30px;">
            <div style="display: flex; justify-content: center;">
                <div style="font-size: 13px; color: #4C84F3; margin: 40px 140px;">정보수정은 비밀번호 / Email / 주소 / 전화번호만 수정가능합니다.</div>
            </div>
            <form id="modifyForm" action="/employees/change" method="post">
                <div class="sign-column">
                    <div>
                        <span>아이디</span>
                    </div>
                    <input type="text" class="sign-input" id="input-id" name="id" value="${emp.id}" maxlength="20" readonly>
                </div>

                <c:if test="${empty emp.oauth }">
                    <div class="sign-column">
                        <div>
                            <span style="margin-right: 35px;">새 비밀번호 * </span>
                        </div>
                        <input type="password" class="sign-input" id="input-password" name="password" placeholder="영문,숫자,특수문자 조합하여 8~16자리" maxlength="16">
                        <div id="pwdcheck-blank1"></div>
                    </div>
                    <div class="sign-column">
                        <div>
                            <span>새 비밀번호 확인 * </span>
                        </div>
                        <input  type="password" class="sign-input" id="password-check" name="password-check" placeholder="위와 동일하게 입력해주세요">
                        <div id="pwdcheck-blank2"></div>
                    </div>
                </c:if>

                <div class="sign-column">
                    <div>
                        <span style="margin-right: 30px;">이메일 주소 *</span>
                    </div>
                    <input id="totalemail" class="sign-input" name="email" value="${emp.email}" type="email">
                </div>

                <div class="sign-column">
                    <div style="margin-top: 10px;">
                        <span>주 소</span>
                    </div>
                    <input class="sign-input" type="text" name="address" id="address" value="${emp.address}" readonly style="background-color: white;">
                    <div class="form-text">변경하지 않을 시 기존의 주소로 설정됩니다.</div>
                </div>

                <div class="sign-column">
                    <span style="margin-right: 35px;">휴대폰번호 *</span>
                    <div>
                        <input type="text" maxlength="13" id="totalphone-num" value="${emp.phoneNumber}">
                        <input type="hidden" class="sign-input" id="phone-num" name="phoneNumber" value="${emp.phoneNumber}">
                        <button disabled class="check-button" type="button" style="outline: none;" id="checkPhoneNumBtn">중복확인</button>
                    </div>
                </div>

                <div class="sign-column">
                    <div>
                        <span style="margin-right: 20px;">부 서</span>
                    </div>
                    <input type="text" name="department" value="${emp.department}">
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 20px;">부 서 번 호</span>
                    </div>
                    <input type="text" name="departmentNumber" value="${emp.departmentNumber}">
                </div>
                <div class="sign-column">
                    <div>
                        <span style="margin-right: 20px;">직 급</span>
                    </div>
                    <input type="text" name="position" value="${emp.position}">
                </div>

                <div style="display: flex;justify-content: flex-end;">
                    <button id="modify-Button" type="button" data-toggle="modal" data-target="#confirmModal" class="btn btn-primary">수정</button>
                    <div>
                        <button type="button" onclick="history.go(-1);" style="margin-left: 40px;" class="btn btn-secondary">취소</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="confirmModal" role="dialog" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">수정 확인</h1>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <label for="inputOldPassword" class="form-label">이전 암호</label>
                <input form="modifyForm" id="inputOldPassword" class="form-control" type="password" name="oldPassword" />
            </div>
            <div class="modal-footer">
                <button type="submit" form="modifyForm" class="btn btn-primary">확인</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
        integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="/js/employees/change.js"></script>
</body>
</html>
