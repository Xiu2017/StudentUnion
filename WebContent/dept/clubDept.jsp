<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>社团部主页</title>
	<link rel="stylesheet" type="text/css" href="../css/liveDept/liveDept.css" />
	<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
</head>
<body>


	<!-- 顶部 -->
	<div class="top">
		<div class="logo"><span>学生会管理系统</span></div>
		<div class="datetime">
			0000-00-00
		</div>
		<div class="user">您好，<span>${sessionScope.info.sdname}</span>[<span>${sessionScope.info.sname}</span>]</div>
	</div>
	
	
	<!-- 主体（包含侧边栏和主框架） -->
	<div class="main">
	
		<!-- 侧边栏 -->
		<div class="menu">
				<div><span>&#xf105;</span> 社团管理</div>
					<ul>
						<li onclick="linkTo(0,this)">出勤登记</li>
						<li onclick="linkTo(1,this)">记录查询</li>
					</ul>
				<div><span>&#xf105;</span> 公告管理</div>
					<ul>
						<li onclick="linkTo(2,this)">查看公告&nbsp;</li>
						<li onclick="linkTo(3,this)">发布公告&nbsp;</li>
						<li onclick="linkTo(4,this)">管理公告&nbsp;</li>
					</ul>
				<div><span>&#xf105;</span> 个人设置</div>
					<ul>
						<li onclick="linkTo(5,this)">密码修改&nbsp;</li>
						<li onclick="linkTo(6,this)">身份切换&nbsp;</li>
						<li onclick="linkTo(7,this)">注销登录&nbsp;</li>
					</ul>
		</div>
		
		
		<!-- 主框架 -->
		<div class="table">
			<a href="javascript:fold()">&#xf104;</a>
			<iframe src="../NoticeServlet?opt=getNotice&page=1"></iframe>
		</div>
		
		
	</div>
</body>
	<script src="../js/clubDept/clubDept.js"></script>
</html>