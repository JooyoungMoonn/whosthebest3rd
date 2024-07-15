<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="/css/adminPage.css">
  <title>회원상세보기</title>
</head>
<body>
  <header>
  <main>

       <div class="admin">
         <a href="/admin/main.do">
          <img src="../image/home.png" alt="홈으로">
         </a>

          <div> 관리자 페이지 </div>
       </div>
 <!-- 사이드 바 -->
     <div>
       <div class="side">
         <img src="../image/user.png" alt="사용자">
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

     <div class="teamDetail">
          <table class="info">
                <tr>
                  <td> 팀 ID </td>
                  <td>${teamInfo.tID}</td>
                  <td> 팀 이름 </td>
                  <td>${teamInfo.tName}</td>
                </tr>

                <tr>
                  <td>팀 인원</td>
                  <td>  ${teamInfo.tMember} </td>
                  <td> 팀 최대인원 </td>
                  <td>  ${teamInfo.tMaxMember}</td>
                </tr>

                <tr>

                  <td>팀 소개 </td>
                  <td>  ${teamInfo.tInfo} </td>
                  <td> 랭크 </td>
                  <td> ${teamInfo.tRankScore} </td>
                </tr>

                <tr>
                  <td> 팀가입 최소나이 </td>
                  <td>  ${teamInfo.tMinAge} </td>
                  <td> 팀가입 최대나이 </td>
                  <td>   ${teamInfo.tMaxAge} </td>
                </tr>

                <tr>
                  <td> 주장 </td>
                  <td> ${teamInfo.createdID} </td>
                  <td> 개설일자 </td>
                  <td> ${teamInfo.createdDate}</td>
                </tr>
          </table>
          <div> 경기내역 </div>
          <div class="playTableContainer">
          <table class="playTable">
              <thead>
               <tr>
                  <th width="210"> 경기 일시 </th>
                  <th width="200"> 나의팀</th>
                  <th width="3" colspan="3"> 득점 </th>
                  <th width="200"> 상대팀 </th>
                  <th width="200"> 경기 장소</th>
               </tr>
              </thead>

             <tbody>
                 <c:choose>
                     <c:when test="${empty teamGame}">
                         <tr>
                             <td colspan="7" style="text-align:center;">경기 내역이 없습니다</td>
                         </tr>
                     </c:when>
                     <c:otherwise>
                         <c:forEach items="${teamGame}" var="tGame">
                             <tr>
                                 <td height="40">${tGame.gResDate}</td>
                                 <td>${tGame.myTeam}</td>
                                 <td> ${tGame.homeGoal}</td>
                                 <td>:</td>
                                 <td> ${tGame.awayGoal}</td>
                                 <td> ${tGame.awayTeam}</td>
                                 <td> ${tGame.sName}</td>
                             </tr>
                         </c:forEach>
                     </c:otherwise>
                 </c:choose>
             </tbody>
          </table>
        </div>
          <button  onclick="window.history.back();" > 뒤로가기 </button>
    </div>
    </main>

    </body>
    </html>