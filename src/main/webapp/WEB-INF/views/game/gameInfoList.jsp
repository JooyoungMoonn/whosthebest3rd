<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
<script>
    <!-- alert('${gameList[0].gTitle}'); -->
    var contextPath = '${contextPath}'; // JavaScript 변수로 설정
</script>
<title>경기 목록</title>
</head>
<body>
  <header>
<div class="logo">
      <a href="/serviceMain">
        <img src="${contextPath}/image/logo.png">
      </a>
    </div>
    <div>
      <span><a href="/logout">로그아웃</a>&nbsp;&nbsp;|&nbsp;</span>
      <span><a href="/myPage">마이페이지</a></span>
    </div>
    <div>
      <ul>
        <li>
          <a href="/teamList">팀</a>
          <div>
            <ul>
              <li><a href="/teamList">팀 목록</a></li>
              <li><a href="/myTeam">나의 팀</a></li>
            </ul>
          </div>
        </li>
        <li><a href="${contextPath}/game/gameInfoList.do">경기 목록</a></li>
        <li><a href="${contextPath}/game/stadiumList.do">경기장 목록</a></li>
        <li><a href="/ranking">랭킹</a></li>
        <li><a href="/mainBoard">게시판</a></li>
      </ul>
    </div>
  </header>
  <main id="main">
    <div>　</div>
    <div class="gameListAllContent">
      <div class="gameList-side">
        <div>
          <div>　</div>
          <button id="selectAllGame" name="selectAllGame">전체 경기</button>
          <button id="selectIMakeGame" name="selectIMakeGame" value="${uID}">내가 만든 경기</a>
          <button id="selectIPartiGame" name="selectIPartiGame" value="${uID}">내가 참여 중인 경기</a>
        </div>
      </div>
      <div class="gameList-content" id="gameList-content">
          <form class="gameList-findForm" id="gameList-findForm">
            <select name="sRegion" id="sRegion">
                <option value="">전체</option>
                <option value="A">서울</option>
                <option value="B">경기</option>
                <option value="C">인천</option>
                <option value="D">강원</option>
                <option value="E">충남</option>
                <option value="F">충북</option>
                <option value="G">대전</option>
                <option value="H">경북</option>
                <option value="I">대구</option>
                <option value="J">경남</option>
                <option value="K">울산</option>
                <option value="L">부산</option>
                <option value="M">광주</option>
                <option value="N">전북</option>
                <option value="O">전남</option>
                <option value="P">제주</option>
            </select>
            <input type="text" class="search" name="search" id="search" placeholder="경기장 이름 검색">
            <input type="submit" class="findFormSubmit" value="검색" >
          </form>
        <!-- 경기만들기 이동 링크 시작 -->
        <div class="makeGameBtnContainer">
          <a href="${contextPath}/game/gameMake.do">경기 만들기</a>
        </div>
        <!-- 리스트 container 시작 -->
        <div id="gameListContainer" class="gameListContainer">
            <!-- 카드 시작 -->
            <c:forEach var="gameListVO" items="${gameList}">
            <div class="cardContainer" id="cardContainer">
              <!-- 카드 몸체 시작 -->
              <div class="card">
                <!-- profile 시작 -->
                <div onclick="openModal()" class="teamProfileContainer" id="teamProfileContainer" name="teamProfileContainer" >
                  <image class="tLogo" id="tLogo" name="tLogo" src="image/teamLogo.png"></image>
                  <div class="tName" id="tName" name="tName" value="${gameListVO.tName}">${gameListVO.tName}</div>
                </div>
                <!-- profile 종료 -->
                <!-- 프로필과 카드 본문 나누는 세로선 -->
                <div class="verticalLine"></div>
                <!-- 본문 테이블 시작-->
                <div class="tableContainer">
                  <table>
                    <tr>
                      <td>경기명 : </td>
                      <td>${gameListVO.gTitle}</td>
                      <td>
                        생성일 :
                      </td>
                      <td>
                        ${gameListVO.gCreatedDate}
                      </td>
                    </tr>
                    <tr>
                      <td>경기장 : </td>
                      <td>${gameListVO.sName} ${gameListVO.sNum} 경기장</td>
                    </tr>
                    <tr>
                      <td>주소 : </td>
                      <td>${gameListVO.sAddr}</td>
                    </tr>
                    <tr>
                      <td>경기일시 : </td>
                      <td>
                          <c:set var="formattedGameListResDate" value="${fn:substring(gameListVO.gResDate, 0, 16)}" />
                          ${formattedGameListResDate} (${gameListVO.gTime} 시간)
                      </td>
                    </tr>
                  </table>
                </div>
                <!-- 본문 테이블 종료-->
              </div>
              <!-- 카드 몸체 종료 -->
              <!-- 카드 submit 시작 -->
              <div>
                <form method="get" action="${contextPath}/game/gameInfo.do">
                  <input type="hidden" name="gID" value="${gameListVO.gID}" />
                  <input type="submit" class="cardSubmit" value="경기 상세 보기"/>
                </form>
                <!-- <a href="gameInfo.html">경기 상세 보기</a> -->
              </div>
              <!-- 카드 submit 종료 -->

            </div>
            </c:forEach>
            <!-- 카드 종료 -->
        </div>
        <!-- 리스트 container 종료 -->

        <!-- pagination 시작 -->
        <div class="paginationContainer">
          <ul class="pagination">
            <li>
            <button id="gameList-prevPage" type="button" onclick="#">이전</button>
            </li>
            <li id="gameList-pageNumBtnLI" name="gameList-pageNumBtnLI">
            </li>
            <li>
                <button id="gameList-nextPage" type="button" onclick="#">다음</button>
            </li>
          </ul>
        </div>
        <!-- pagination 종료 -->
      </div>
      <!-- 컨텐트 종료 지점 -->
    </div>
    <!-- 이름없는 div 종료 -->
  </main>
  <!-- 팀 정보 모달 시작 -->
  <div id="teamInfoModal" class="teamInfoModal">

    <div class="teamprofile-modal">
      <div onclick="closeModal()" class="closebtn">&times;</div>
      <div>
        <img src="image/우사기.jpg">
        <img src="image/축구공.png">
        <div>
          <div>
            <span>팀 이름 : </span>
            <span>문주군단</span>
          </div>
          <div>
            <span>전적 : </span>
            <span>47전 33승 14패</span>
          </div>
          <div>
            <span>팀원 수 : </span>
            <span>50명</span>
          </div>
          <div>
            <span>창단일 : </span>
            <span>2024.05.05</span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 팀 정보 모달 끝 -->
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