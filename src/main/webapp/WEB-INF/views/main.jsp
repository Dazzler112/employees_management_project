<%--
  Created by IntelliJ IDEA.
  User: LG
  Date: 2023-09-27
  Time: 오후 4:47
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
    <title>뉴컴스 관리매니저</title>
    <style>
        .main-container{
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
<header>

</header>

<main>
    <div class="main-container">
        <form action="employees/" method="get" enctype="multipart/form-data">
        <div class="main-container_column">
            <div class="mb-3">
                <c:forEach items="${.fileName }" var="photo">
                    <div>
                        <label for="get-img" class="form-label"></label>
                        <img id="get-img" class="img-fluid img-thumbnail form-control" src="${bucketUrl}/${.id}/${photo}" />
                    </div>
                </c:forEach>
            </div>
            <div>
                <div>
                    ${.name}
                </div>
                <div>
                    ${.department}
                </div>
                <div>
                    ${.position}
                </div>
            </div>
        </div>

        <div class="main-container_column">
            <div>
                <input type="text" value="" placeholder="당일 출석체크">
                <input type="text" value="" placeholder="출석일수">
                <input type="text" value="" placeholder="연장상태확인">
            </div>
        </div>
        </form>
    </div>
</main>

<footer>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
        integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
