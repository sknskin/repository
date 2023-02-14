<%@page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"%>
    	
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Project Demo Web</title>

  <jsp:include page="/WEB-INF/views/modules/common-css.jsp" />
  
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Navbar -->
  <jsp:include page="/WEB-INF/views/modules/navbar.jsp" />
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <jsp:include page="/WEB-INF/views/modules/sidebar.jsp" />
  <!-- /.Main Sidebar Container -->
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:include page="/WEB-INF/views/modules/content-header.jsp" />
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        
        
        
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <!-- footer -->
  <jsp:include page="/WEB-INF/views/modules/footer.jsp" />
  <!-- /.footer -->

  <!-- Control Sidebar -->
  <!-- /.control-sidebar -->
  
</div>
<!-- ./wrapper -->

<jsp:include page="/WEB-INF/views/modules/common-js.jsp" />

</body>
</html>













