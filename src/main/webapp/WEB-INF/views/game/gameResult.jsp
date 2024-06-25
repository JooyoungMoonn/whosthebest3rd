<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/js.js"></script>
    <title>경기 결과 입력</title>
</head>
<body>
<header>
    <div class="logo">
        <img src="../image/logo.png">
        <!-- <div>누가 잘차</div> -->
    </div>
    <div>
        <span><a href="login.html">로그아웃</a>&nbsp;&nbsp;|&nbsp;</span>
        <span><a href="myPage.html">마이페이지</a></span>
    </div>
    <div>
        <ul>
            <li>
                <a href="teamlist.html">팀</a>
                <div>
                    <ul>
                        <li><a href="teamlist.html">팀 목록</a></li>
                        <li><a href="myteam.html">나의 팀</a></li>
                    </ul>
                </div>
            </li>
            <li><a href="gameList.html">경기 목록</a></li>
            <li><a href="stadiumList.html">경기장 목록</a></li>
            <li><a href="rankWatch.html">랭킹</a></li>
            <li><a href="#">게시판</a></li>
        </ul>
    </div>
</header>
<main>
    <div>　</div>
    <div>
        <!-- content 영역 시작 -->
        <div class="gameResult-content">
            <!-- 경기 제목 영역 시작 -->
            <div class="titeContainer">
                <div>천안</div>
                <div>진짜 뒤지고 싶은 사람~~!</div>
                <div>
                    <a>경기 종료</a>
                </div>
            </div>
            <!-- 경기 제목 영역 종료 -->
            <!-- 경기 평가 영역 시작 -->
            <div class="resultInputContainer">
                <div class="teamNameContainer">
                    <button class="teamName" id="homeTeam" onclick="changeButtons('homeTeam', 'oppanentTeam')">
                        문주군단
                    </button>
                    <button class="teamName" id="oppanentTeam" onclick="changeButtons('oppanentTeam', 'homeTeam')">
                        개발냥발
                    </button>
                </div>
                <div class="scoreInputContianer">
                    <input type="text" placeholder="Score" maxlength="2">
                    <input type="text" placeholder="Score" maxlength="2">
                </div>
                <div class="submitContainer">
                    <form action="gameList.html">
                        <input type="submit" value="결과 제출">
                    </form>
                </div>
            </div>
            <!-- 경기 평가 영역 종료 -->
        </div>
        <!-- 이름없는 div 종료 -->
    </div>
    <!-- content 영역 종료 -->
</main>
<footer>
    <div>
        <ul>
            <li><a href="#">이용약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="#">개인정보처리방침</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="#">오류/건의</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="#">광고/후원문의</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="#">고객센터</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="#">&copy;누가잘차</a></li>
        </ul>
    </div>
    <div>&copy;2024.MunjuGangz All rights reserved.</div>
</footer>
</body>
</html>