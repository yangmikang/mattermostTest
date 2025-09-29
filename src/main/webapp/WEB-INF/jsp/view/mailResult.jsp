<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Email Result</title>
	</head>
	<body>
		<div id="container" class="sub-cont-bg">
			<div class="page-title-wrap visual img-members">
				<div class="visual-filter"></div>
				<div class="inner">
					<!-- breadcrumb -->
					<nav class="breadcrumb-wrap" aria-label="브레드크럼">
						<ol class="breadcrumb">
							<li class="home"><a href="#" class="txt">Home</a></li>
							<li><a href="#" class="txt">About GINI</a></li>
							<li><a href="#" class="txt">Contact Us</a></li>
							<li><a href="#" class="txt">Email Result</a></li>
						</ol>
					</nav>
					<!-- breadcrumb -->
					<div class="visual-area">
						<h2 class="h-tit">Email Result</h2>
						<p>Overview of Operational Projects</p>
					</div>
				</div>
			</div>
			<div class="inner">
				<div class="conts-area">
					<div class="conts-wrap">
						<div class="conts-wrap log-in-box">
							<div class="txt-box bg-white-login">
								<div class="mail-result-img"></div>
								<h2 class="section-title">Email Result</h2>
								<c:choose>
									<c:when test="${result eq 'mail success'}">
										<p class="section-text sucess">Email has been sent successfully.</p>
									</c:when>
									<c:otherwise>
										<p class="section-text fail">Failed to send email.</p>
									</c:otherwise>
								</c:choose>
								<div class="comp-btn-wrap">
									<button type="submit" class="btn primary md" onclick="location.href='${pageContext.request.contextPath}/contactus.do'">Resend Email</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>