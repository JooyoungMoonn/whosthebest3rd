<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="/css/adminPage.css">
  <title>게임 상세보기</title>
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


    <div class="gameDetail">
      <table class="gameInfo">
        <tr>
          <td>경기 ID</td>
          <td> ${gameInfo.gID} </td>
          <td> 경기 이름 </td>
          <td> ${gameInfo.gTitle} </td>
        </tr>

        <tr>
          <td> 홈 팀 </td>
          <td> ${gameInfo.myTeam} </td>
          <td> 어웨이 팀 </td>
          <td> ${gameInfo.awayTeam} </td>
        </tr>

        <tr>
          <td>경기 일시</td>
          <td> ${gameInfo.gResDate} </td>
          <td>경기 시간</td>
          <td>  ${gameInfo.gTime} 시간</td>
        </tr>

        <tr>
          <td>경기장</td>
          <td> ${gameInfo.sName} </td>
          <td> 생성자 </td>
          <td> ${gameInfo.createdID} </td>
        </tr>

        <tr>
          <td> 경기 상태</td>
           <td>
              <c:choose>
                  <c:when test="${gameInfo.gStatus == 0}">
                      경기 생성
                  </c:when>
                  <c:when test="${gameInfo.gStatus ==1}">
                     <span class="red-text"> 경기 종료   </span>
                  </c:when>
              </c:choose>
           </td>
          <td> 경기 정보</td>
          <td width="400" height="70"> ${gameInfo.gInfo} </td>
        </tr>
      </table>
      <div> 경기 결과</div>

      <table class="gameResult">
        <thead>
        <tr>
          <th  width="230" height="40"> 경기 일시</th>
          <th width="200"> 홈 팀 </th>
          <th colspan="3"> 득점 </th>
          <th  width="200"> 어웨이 팀</th>
          <th width="200"> 경기장</th>
        </tr>
      </thead>


      <tbody>
       <c:choose>
           <c:when test="${empty gameResult}">
               <tr>
                   <td colspan="7" style="text-align:center;">경기 내역이 없습니다</td>
               </tr>
           </c:when>
       </c:choose>
        <c:forEach items="${gameResult}" var="result">
           <tr>
             <td> ${result.gResDate} </td>
             <td> ${result.myTeam} </td>
             <td> ${result.homeGoal}  </td>
             <td> : </td>
             <td> ${result.awayGoal} </td>
             <td> ${result.awayTeam} </td>
             <td> ${result.sName} </td>
           </tr>
        </c:forEach>
      </tbody>
     </table>
    </div>

     <button  onclick="window.history.back();"> 뒤로가기</button>

  </div>
</main>

</body>
</html>

