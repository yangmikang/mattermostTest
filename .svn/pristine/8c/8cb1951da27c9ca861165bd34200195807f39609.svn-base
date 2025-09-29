<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<!-- 컨테이너 영역 -->
		<div id="container">
			<div class="page-title-wrap visual img-joint">
				<div class="visual-filter"></div>
				<div class="inner">
					<!-- breadcrumb -->
					<nav class="breadcrumb-wrap" aria-label="브레드크럼">
						<ol class="breadcrumb">
							<li class="home"><a href="#" class="txt">Home</a></li>
							<li><a href="#" class="txt">Find Your ID</a></li>
						</ol>
					</nav>
					<!-- breadcrumb -->
					<!-- 페이지 타이틀 영역 -->
					<div class="visual-area">
						<h2 class="h-tit">Find Your ID</h2>
<!-- 						<p>This is an informational page summarizing Daejeon&#39;s key facts, strategic industries, major institutions, and contact information.</p> -->
					</div>
				<!-- //페이지 타이틀 영역 -->
					
				</div>
			</div>
			<div class="inner">
				<div class="conts-area">
					<div class="conts-wrap">
						<div class="conts-wrap log-in-box">
							<div class="txt-box bg-white-login">
									<div class="find-id-img"></div>
									<h4 class="box-tit1">Find ID Complete</h4>
									<p>Your ID has been successfully found.</p>
									<div class="box-cnt">
										<div class="box-sec">
											<div class="find-id-box">
												<p>your ID is &#58; 
													<strong class="form-conts">${foundUserId}</strong>
												</p>
											</div>
											<div class="comp-btn-wrap">
												<button type="button" onclick="location.href='${pageContext.request.contextPath}/login.do';" class="btn xlg">Go to Login</button>
											</div>
											<div class="comp-btn-wrap">
												<p>Shall we go recover the password too?</p>
												<button type="button" onclick="location.href='${pageContext.request.contextPath}/findPassword.do';" class="btn xsm secondary">Find Password</button>
											</div>
 
										</div>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>