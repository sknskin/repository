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
var state = naver_id_login.getUniqState();
naver_id_login.setButton("white", 2,40);
naver_id_login.setDomain("http://localhost:8081/demo-x/login");
naver_id_login.setState(state);
naver_id_login.setPopup();
naver_id_login.init_naver_id_login();

$(function() {
	
	
});
</script>

</body>
</html>












