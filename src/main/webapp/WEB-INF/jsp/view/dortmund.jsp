<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 컨테이너 영역 -->
		<div id="container">
		<div class="page-title-wrap visual img-members">
			<div class="visual-filter"></div>
			<div class="inner">
				<!-- breadcrumb -->
				<nav class="breadcrumb-wrap" aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="home"><a href="#" class="txt">Home</a></li>
						<li><a href="#" class="txt">Network</a></li>
						<li><a href="#" class="txt">City of Dortmund</a></li>
					</ol>
				</nav>
				<!-- breadcrumb -->
				<!-- 페이지 타이틀 영역 -->
				<div class="visual-area">
					<h2 class="h-tit">City of Dortmund</h2>
					<p>Members</p>
				</div>
				<!-- //페이지 타이틀 영역 -->
			</div>
		</div>
	
			<!-- 컨텐츠 영역 -->
			<div class="inner">
				<!-- 섹션 컨텐츠 -->
				 <div class="gini-cont-wrap gap20 cont-box cont-network">
					<div class="gini-cont-box">
						<h3 class="area-tit ico ico-location-2">City of Dortmund</h3>
						<div class="row cont100">
							<div class="cont-left cont50 memb_view">
								<div class="city-view-box">
									<div class="city-view-box-in">
										<div class="city-view-ico">
											<i class="item-ico ico-germ">
												<img src="${pageContext.request.contextPath}/img/component/common/ico-symbol-dortmund.png" title="symbol dortmund">
											</i>
										</div>
										<div class="city-view-img-box">
											<span class="city-view-img">
												<img src="${pageContext.request.contextPath}/img/component/common/membe_dort.jpg" title="dortmund">
											</span>
										</div>
									</div>
								</div>
								<!-- 해당 도시에 따라 아이디를 넣어주세요 deajeon : .img-deajeon, dortmund : .img-dortmund, malaga : .img-malaga, County of Montgomery, MD : .img-montgomery, City of Seattle : .img-seattle, Quebec Province : .img-quebec, Hisnchu : .img-hisnchu -->
							</div>
							<div class="cont-right cont50">
								<div class="tbl_wrap">
									<ul class="list-tableType type2">
										<li><span class="title ico-population">Country</span><span class="value">Germany</span></li>
										<li><span class="title ico-population">Population</span><span class="value">612,065 (City); 11.3M (Metro, Rhine-Ruhr)</span></li>
										<li><span class="title ico-grdp">GRDP</span><span class="value">€44,816 per capita (2022, +5.93%)</span></li>
										<li><span class="title ico-key-industries">Key Industries</span><span class="value">ICT, Micro/Nanotechnology, Biomedicine, Logistics, Robotics, Hydrogen, Productiontechnoloy</span></li>
										<li><span class="title ico-transport">Transport</span><span class="value">Major rail and road hub, international airport, and port linking to the North Sea</span></li>
										<li><span class="title ico-education">Education & Research</span><span class="value">50,000 students at 7 universities, 19 research institutes, including Max-Planck, Leibniz, and Fraunhofer Institutes</span></li>
										<li><span class="title ico-innovative-ecosystem">Innovative Ecosystem</span><span class="value">Home to TZDO with 200 startups and Ruhrvalley tech companies, European Capital of Innovation (iCapital 2021), comprehensive start-up support ecosystem</span></li>
										<li><span class="title ico-heritage">Heritage</span><span class="value">Rich industrial history in steel, coal, and beer; ranked as one of Germany's most livable cities; cultural hotspot and sports city (BVB soccer club)</span></li>
									</ul>
								</div>	
							</div>
						</div>
						
						<div class="gini-cont-box gap-16">
							<h3 class="area-tit">Strategic Industries</h3>
							<div class="grid cols-4">
						        <article class="card city IT">
						          <%-- <div class="icon"><img src="${pageContext.request.contextPath}/img/component/common/ico-idea-exchange.svg" title="Business Exchange"></div> --%>
						          <div>
						            <b>IT</b>
						          </div>
						        </article>
						        <article class="card city biohealth">
						          <div>
						            <b>Biotechnology</b>
						          </div>
						        </article>
						        <article class="card city hydrogen">
						          <div>
						            <b>Hydrogen</b>
						          </div>
						        </article>
						         <article class="card city production">
						          <div>
						            <b>Production technology</b>
						          </div>
						        </article>
					      	</div>
				      	</div>
				      	<div class="gini-cont-box">
				      		<h3 class="area-tit ico ico-location-2">Competence Centres</h3>
							<div class="row cont100">
								<div class="cont-left cont50">
									<div class="city-media">
										<img src="${pageContext.request.contextPath}/img/component/common/membe_dortmund.png" title="dortmund 지도" style="aspect-ratio:16/11;">
									</div>
									<div class="grid cols-3">
										<article class="card">
											<img src="${pageContext.request.contextPath}/img/component/common/stadt-dortmund-logo-rechts.png" title="Business Exchange">
							          	</article>
										<article class="card">
											<img src="${pageContext.request.contextPath}/img/component/common/img-dortmund-th.png" title="Business Exchange">
							          	</article>
							          	<article class="card">
											<img src="${pageContext.request.contextPath}/img/component/common/FHDO-Logo-large.svg" title="Business Exchange">
							          	</article>
									</div>
								</div>
								<div class="cont-right cont50">
									<ul class="list-line">   
										<li><span class="title">6 Competence Centers: Over 200 Companies, 100,000 Square meters</span></li>
										<li><span class="title">Technology Centre</span></li>
										<li><span class="title">Tech Start-up Hub; Biomedice and drug research</span></li>
										<li><span class="title">BMZ; Software</span></li>
										<li><span class="title">B1st; Logistics</span></li> 
										<li><span class="title">e-port Production technology</span></li>
										<li><span class="title">ZfP; Micro and Nano technology</span></li>
										<li><span class="title">MST factory</span></li>
									</ul>
								</div>
							</div>
				      	</div>
				      	<div class="gini-cont-box">
					      	<ul class="card-list">
								<li>
									<div class="img-wrap">
										<img src="${pageContext.request.contextPath}/img/pattern/content/u-tower.jpg" title="U Tower">
										<div class="list-conts" style="height:auto;">
											<h4 class="card_tit">U Tower</h4>
										</div>
									</div>
								</li>
								<li>
									<div class="img-wrap">
										<img src="${pageContext.request.contextPath}/img/pattern/content/img-dort-image-1.jpg" title="Phoenix Lake">
										<div class="list-conts" style="height:auto;">
											<h4 class="card_tit">Phoenix Lake</h4>
										</div>
									</div>
								</li>
								<li>
									<div class="img-wrap">
										<img src="${pageContext.request.contextPath}/img/pattern/content/img-dort-image-2.jpg" title="Westfalen park">
										<div class="list-conts" style="height:auto;">
											<h4 class="card_tit">Westfalen park</h4>
										</div>
									</div>
								</li>
								<li>
									<div class="img-wrap">
										<img src="${pageContext.request.contextPath}/img/pattern/content/img-dort-image-3.jpg" title="Signal Iduna Park">
										<div class="list-conts" style="height:auto;">
											<h4 class="card_tit">Signal Iduna Park</h4>
										</div>
									</div>
								</li>
								<li>
									<div class="img-wrap">
										<img src="${pageContext.request.contextPath}/img/pattern/content/img-dort-image-4.jpg" title="Zeche Zollern">
										<div class="list-conts" style="height:auto;">
											<h4 class="card_tit">Zeche Zollern</h4>
										</div>
									</div>
								</li>
								<li>
									<div class="img-wrap">
										<img src="${pageContext.request.contextPath}/img/pattern/content/img-dort-image-5.jpg" title="Digital Week Dortmund">
										<div class="list-conts" style="height:auto;">
											<h4 class="card_tit">Digital Week Dortmund</h4>
										</div>
									</div>
								</li>
							</ul>
			      		</div>
					</div>
				 </div>
				<!-- //섹션 컨텐츠 -->  
			</div>
			<!-- //컨텐츠 영역 -->
		</div>
		<!-- //컨테이너 영역 --> 
</html>
 
