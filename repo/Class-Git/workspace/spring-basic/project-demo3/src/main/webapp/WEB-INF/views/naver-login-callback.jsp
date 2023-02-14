<%@page import="java.util.Date"%>
<%@page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"%>
    	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Naver Login Demo</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<div class="container">
	<div id="naver_id_login"></div>
</div>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>   
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript">

var naver_id_login = new naver_id_login("0ydX7qHGcXql39iKHwWy", "http://localhost:8081/demo-x/naver-login-callback");

// 네이버 사용자 프로필 조회
naver_id_login.get_naver_userprofile("naverSignInCallback()");
// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
function naverSignInCallback() {
  // alert(naver_id_login.getProfileData('id'));
  // alert(naver_id_login.getProfileData('email'));
  // alert(naver_id_login.getProfileData('nickname'));
  alert('로그인 성공');
  const id = naver_id_login.getProfileData('id');
  const email = naver_id_login.getProfileData('email');
  location.href="naver-login-success?id=" + id + "&email=" + email;
  
}

$(function() {
	
	
});
</script>

</body>
</html>












