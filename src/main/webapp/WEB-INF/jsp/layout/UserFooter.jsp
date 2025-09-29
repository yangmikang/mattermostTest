<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 푸터 영역 -->
		<footer id="footer">
			<!-- <div class="foot-bg"></div> -->
<!-- 			<div class="foot-quick">
				<div class="inner">
					<button type="button" class="link" title="연계기관 레이어"
							onclick="common.popupEvent('#popFootLink');">KISTEP</button>
					<button type="button" class="link" title="연계기관 레이어">STEPI</button>
					<button type="button" class="link" title="연계기관 레이어">MSS</button>
					<button type="button" class="link" title="정부기관 레이어">MSIT</button>
				</div>
			</div> -->
			<div class="inner">
				<div class="f-cnt-wrap">
					<div class="f-cnt-box column">
						<div class="f-cnt top">
							<div class="f-logo">
								<img src="../${pageContext.request.contextPath}/img/pattern/layout/footer_logo.svg" class="logo-im" alt="지니">
							</div>
							<div class="link-sns">
								<a href="#" class="linkedin" target="_blank"><span class="sr-only">linkedin</span></a>
<!-- 								<a href="#" class="instagram" target="_blank"><span class="sr-only">Instagram</span></a>
								<a href="#" class="youtube" target="_blank"><span class="sr-only">YouTube</span></a>
								<a href="#" class="twitter" target="_blank"><span class="sr-only">트위터</span></a>
								<a href="#" class="facebook" target="_blank"><span class="sr-only">Facebook</span></a>
								<a href="#" class="blog" target="_blank"><span class="sr-only">블로그</span></a> -->
							</div>
						</div>
						<div class="f-cnt bottom">
							<div class="f-info">
<!-- 								<ul class="info-cs">
									<li><a href="tel:+82428650501"><strong class="strong ico ico-tel">+82-42-865-0550</strong></a></li>
									<li><strong class="strong ico ico-fax">+82-42-861-4309</strong><span class="span"></span></li>
								</ul> -->
								<p class="info-addr ico ico-location">(34430) GINI Secretariat, DiSTEP, 70 Hannam-ro, Daejeon, South Korea</p>
							</div>
						</div>
					</div>
					<div class="f-link">
						<p class="title">Navigation</p>
						<div class="link-go">
							<a href="${pageContext.request.contextPath}/overview.do" class="btn btn-txt sm ico-arr">About GINI</a>
							<a href="${pageContext.request.contextPath}/networkMain.do" class="btn btn-txt sm ico-arr">Network</a>
							<a href="${pageContext.request.contextPath}/calendar.do" class="btn btn-txt sm ico-arr">Programs</a>
							<a href="${pageContext.request.contextPath}/board/boardList.do?boardMasterCode=N01" class="btn btn-txt sm ico-arr">News</a>
							<a href="${pageContext.request.contextPath}/board/boardList.do?boardMasterCode=N05" class="btn btn-txt sm ico-arr">Members Area</a>
						</div>
					</div>
				</div>
				<div class="f-btm">
					<div class="f-btm-text">
						<div class="f-menu">
							<a href="#" class="point">Privacy Policy</a>
							<a href="#">Copyright Policy</a>
							<!-- <a href="#">웹 접근성 품질인증 마크 획득</a> -->
						</div>
						<p class="f-copy">ⓒ 2025. Daejeon Institute of Science & Technology for Enterprise & People. All rights reserved.</p>
					</div>
				</div>
			</div>
			
		</footer>
		<!-- //푸터 영역 -->
		<script>
		// 비주얼 배너
     		const visualSwiper = new Swiper('.vb-swiper .swiper', {
			slidesPerView: 1,
			spaceBetween: 0,
			speed: 2500,
			loop: false,
			stopAutoOnHover: false,
			//autoplay: {
				//delay: 1500, 
				//stopOnLastSlide: true,
				//disableOnInteraction: true,
			//},
			navigation: {
				nextEl: '.vb-swiper .swiper-button-next',
				prevEl: '.vb-swiper .swiper-button-prev',
			},
			pagination: {
				el: ".vb-swiper .swiper-pagination",
				type: "bullets",
				clickable: true,
			},
		});   
		// 멤버 배너
		const memberSwiper = new Swiper('.member-swiper .swiper', {
			slidesPerView: 1,
			spaceBetween: 0,
			speed: 1000,
			loop: true,
			stopAutoOnHover: true,
			//autoplay: {
				//delay: 1000, 
				//stopOnLastSlide: true,
				//disableOnInteraction: false,
			//},
			navigation: {
				nextEl: '.member-swiper .swiper-button-next',
				prevEl: '.member-swiper .swiper-button-prev',
			},
			pagination: {
				el: ".member-swiper .swiper-pagination",
				type: "bullets", //progressbar,fraction
				clickable: true,
			},
		});
		
/* 		//뉴스
		const ncontSwiper = new Swiper('.n-cont .swiper', {
			slidesPerView: 1,
			spaceBetween: 0,
			speed: 400,
			loop: true,
			navigation: {
				nextEl: '.n-cont .swiper-button-next',
				prevEl: '.n-cont .swiper-button-prev',
			},
			pagination: {
				el: ".n-cont .swiper-pagination",
				type: "bullets", //progressbar,fraction
				clickable: true,
			},
		});
		//미디어
		const mcontSwiper = new Swiper('.m-cont .swiper', {
			slidesPerView: 1,
			spaceBetween: 0,
			speed: 400,
			loop: true,
			navigation: {
				nextEl: '.m-cont .swiper-button-next',
				prevEl: '.m-cont .swiper-button-prev',
			},
			pagination: {
				el: ".m-cont .swiper-pagination",
				type: "bullets", //progressbar,fraction
				clickable: true,
			},
		}); */
		
		//푸터 기관
		const serviceSwiper = new Swiper('.service-list .swiper', {
			slidesPerView: 6,
			spaceBetween: 24,
			speed: 400,
			loop: true,
			stopAutoOnHover: true,
			 	autoplay: {
			 	delay: 1000, // 3초마다 자동 전환
			 	disableOnInteraction: false, // 유저 조작 후에도 계속 자동 재생
			 },
			breakpoints: {
				200: {
					slidesPerView: 2,
				},
				300: {
					slidesPerView: 2,
				},
				600: {
					slidesPerView: 4,
				},
				1024: {
					slidesPerView: 6,
				},
				1400: {
					slidesPerView: 6,
				},
			},
			navigation: {
				nextEl: '.service-list .swiper-button-next',
				prevEl: '.service-list .swiper-button-prev',
			},
			pagination: {
				el: ".service-list .swiper-pagination-fraction",
				type: "fraction",
			},
		});
/* 		
		const $serviceSwiperPlay = document.querySelector('.service-list .swiper-button-play');
		const $serviceSwiperStop = document.querySelector('.service-list .swiper-button-stop');

		$serviceSwiperPlay.style.display = "none";

		$serviceSwiperPlay.addEventListener("click", () => {
			serviceSwiper.autoplay.start();
			$serviceSwiperStop.style.display = "";
			$serviceSwiperPlay.style.display = "none";
		});

		$serviceSwiperStop.addEventListener("click", () => {
			serviceSwiper.autoplay.stop();
			$serviceSwiperStop.style.display = "none";
			$serviceSwiperPlay.style.display = "";
		}); */
	</script>
</body>

</html>  