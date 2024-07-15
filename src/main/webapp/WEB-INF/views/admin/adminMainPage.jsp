<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/adminPage.css">
  <title>관리자페이지-관리자 메인</title>

</head>
<body>
  <header>
  <main>

      <div class="admin">
        <a href="/admin/main.do">
         <img src="/image/home.png" alt="홈으로">
        </a>

         <div> 관리자 페이지 </div>
      </div>
<!-- 사이드 바 -->
    <div>
      <div class="side">
        <img src="/image/user.png" alt="사용자">
          <div> admin</div>

            <ul style="list-style-type: none;">
               <li> <a href="/admin/main.do"> 관리자 메인 </a> </li>
               <li> <a href="/admin/userList.do">회원 관리 </a> </li>
               <li><a href="/admin/teamList.do">팀 관리 </a> </li>
               <li><a href="/admin/gameList.do">경기 관리 </a></li>
               <li><a href="/mainBoard"> 게시판 </a></li>
            </ul>

        <img class="logoutimg" src="/image/logout.png" alt="로그아웃" onclick="logoutFunction()" >
        <script>
        function logoutFunction() {
            // 로그아웃을 수행할 URL 설정
            var logoutUrl = "/logout"; // 예시 URL, 실제 프로젝트에 맞게 수정

            // 로그아웃을 요청하고 페이지 이동
            window.location.href = logoutUrl;
        }
        </script>


    </div>

<!-- 최근 회원 목록 테이블-->

    <div class="usertablecontainer">
      <h2> 최근 회원 목록</h2>
    <table class="user-list">
      <thead>
          <tr>
              <th>ID</th>
              <th>이름</th>
              <th>생년월일</th>
              <th>성별</th>
              <th>이메일</th>

          </tr>
      </thead>


      <tbody>
      <c:forEach items="${newUser}" var="user">
        <tr>
          <td>  ${user.uID}   </td>
          <td>  ${user.uName}  </td>
          <td>  ${user.uBday}   </td>
          <td>   ${user.uGender} </td>
          <td>   ${user.uEmail}  </td>
        </tr>
       </c:forEach>

      </tbody>
    </table>
    <a href="/admin/userList.do"> <button> 회원 전체보기</button> </a>
  </div>
</div>

<!-- 최근 팀 목록 테이블-->

<div class="teamtablecontainer">
  <h2> 최근 팀 목록 </h2>
<table class="user-list">
  <thead>
      <tr>
        <th>팀ID</th>
        <th>팀 명</th>
        <th>지역</th>
        <th>랭크</th>
        <th>인원</th>

      </tr>
  </thead>

  <tbody>
    <tr>
        <c:forEach items="${newTeam}" var="team">
          <tr>
            <td width="130"> ${team.tID} </td>  <!-- 팀아이디 -->
            <td> ${team.tName}  </td>
            <td> ${team.tRegion}  </td>
            <td> ${team.tRankScore}    </td>
            <td> ${team.tMember}  </td>
          </tr>
        </c:forEach>

    </tr>
  </tbody>
</table>

<a href="/admin/teamList.do"> <button> 팀 전체보기</button> </a>
  </div>
</div>

</main>

</body>
</html>