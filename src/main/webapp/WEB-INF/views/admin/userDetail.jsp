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

    <div class="userDetail">
      <table>
            <tr>
              <td> 이름 </td>
              <td>${userInfo[0].uName}</td>
            </tr>

            <tr>
              <td> 아이디</td>
              <td>${userInfo[0].uID}</td>
            </tr>

            <tr>
              <td> 생년월일</td>
              <td>
                 ${userInfo[0].uBday}
               </td>
            </tr>

            <tr>
              <td> 성별 </td>
              <td> ${userInfo[0].uGender} </td>
            </tr>

            <tr>
              <td> 도로명 주소 </td>
              <td>  ${userInfo[0].uAddr1} </td>
            </tr>

            <tr>
              <td> 상세 주소 </td>
              <td>   ${userInfo[0].uAddr2} </td>
            </tr>

            <tr>
              <td> 이메일 </td>
              <td>  ${userInfo[0].uEmail}</td>
            </tr>

            <tr>
              <td>전화번호</td>
              <td> 010-****-**** </td>
            </tr>

            <tr>
               <td> 소속팀1 </td>
               <td>${userInfo[0].tName}  </td>
            </tr>

             <tr>
               <td> 소속팀2 </td>
               <td>  ${userInfo[1].tName}  </td>
            </tr>

             <tr>
               <td> 소속팀3 </td>
               <td> ${userInfo[2].tName}   </td>
            </tr>

              <tr>
                <td> 가입일자 </td>
                <td> ${userInfo[0].createdDate}</td>
            </tr>
      </table>
     <button onclick="window.history.back();"> 뒤로가기</button>
</div>
</main>

</body>
</html>