<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>外联部主页</title>
	<link rel="stylesheet" type="text/css" href="../css/ANliDept/ANliDept.css" />
	<script src="../js/jquery-1.9.1.min.js"></script>
</head>
<body>


	<!-- 顶部 -->
	<div class="top">
		<div class="logo"><span>学生会管理系统</span></div>
		<div class="datetime">
			2017-06-12
		</div>
		<div class="user">您好，<span>${sessionScope.info.sdname}</span>[<span>${sessionScope.info.sname}</span>]</div>
	</div>
	
	
	<!-- 主体（包含侧边栏和主框架） -->
	<div class="main">
	
		<!-- 侧边栏 -->
		<div class="menu">
				<div><span>&#xf105;</span> 赞助信息</div>
					<ul>
						<li onclick="linkTo(0,this)">添加信息</li>
						<li onclick="linkTo(1,this)">查询记录</li>
					</ul>
				<div><span>&#xf105;</span> 活动安排</div>
					<ul>
						<li onclick="linkTo(2,this)">添加活动&nbsp;</li>
						<li onclick="linkTo(3,this)">查询记录&nbsp;</li>
					</ul>
				<div><span>&#xf105;</span> 讲座记录</div>
					<ul>
						<li onclick="linkTo(4,this)">新增记录&nbsp;</li>
						<li onclick="linkTo(5,this)">查询记录&nbsp;</li>
					</ul>
				<div><span>&#xf105;</span> 公告管理</div>
					<ul>
						<li onclick="linkTo(6,this)">查看公告&nbsp;</li>
						<li onclick="linkTo(7,this)">发布公告&nbsp;</li>
						<li onclick="linkTo(8,this)">管理公告&nbsp;</li>
					</ul>
				<div><span>&#xf105;</span> 个人设置</div>
					<ul>
						<li onclick="linkTo(9,this)">密码修改&nbsp;</li>
						<li onclick="linkTo(10,this)">身份切换&nbsp;</li>
						<li onclick="linkTo(11,this)">注销登录&nbsp;</li>
					</ul>
		</div>
		
		
		<!-- 主框架 -->
		<div class="table">
			<a href="javascript:fold()">&#xf104;</a>
			<iframe src="../NoticeServlet?opt=getNotice&page=1"></iframe>
		</div>
		
		
	</div>
</body>
	<script src="../js/ANliDept/ANliDept.js"></script>
</html>