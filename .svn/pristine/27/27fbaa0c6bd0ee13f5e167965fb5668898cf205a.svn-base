<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
/* 바깥 컨테이너: 중앙 정렬 */
.g-recaptcha-outer {
  display: flex;
  justify-content: center;
}

.g-recaptcha-wrapper {
  transform-origin: top left;
}
</style>  
<!-- 컨테이너 영역 -->
		<div id="container">
			<div class="page-title-wrap visual img-members">
				<div class="visual-filter"></div>
				<div class="inner">
					<!-- breadcrumb -->
				<nav class="breadcrumb-wrap" aria-label="브레드크럼">
					<ol class="breadcrumb">
						<li class="home"><a href="#" class="txt">Home</a></li>
						<li><a href="#" class="txt">About GINI</a></li>
						<li aria-current="page"><a href="#" class="txt">Contact Us</a></li>
					</ol>
				</nav>
				<!-- breadcrumb -->
				 <!-- 페이지 타이틀 영역 -->
					<div class="visual-area">
						<h2 class="h-tit">Contact Us</h2>
						<!-- <p>GINI Logo Concept</p> -->
					</div>
					<!-- //페이지 타이틀 영역 -->
				</div>
			</div>

			<!-- 컨텐츠 영역 -->
			<div class="inner">
				<!-- 섹션 컨텐츠 -->
				 <div class="gini-cont-wrap gap20 cont-box cont-map">
					<!-- contact us form -->
					<div class="gini-cont-box">
						<h3 class="area-tit" style="margin-top: 20px;">Q&A</h3>
						<!-- row -->
						<div class="row">  
							<div class="form-bg-area-box">
								<form action="sendMail.do" method="post" enctype="multipart/form-data">
									<input type="hidden" name="userId" value="${user.id}">
								    <input type="hidden" name="userName" value="${user.username}">
									<input type="hidden" name="toEmail" value="1237@coreit.co.kr">
<!-- 									<input type="hidden" name="toEmail" value="GINISecretariat@gmail.com"> -->
									<div class="form-group">
										<div class="form-tit">
											<label for="send_mail">Sender Email (Reply-To) <span class="req">&#x2A;</span></label>
										</div>
										<div class="form-conts">
											<input type="email" name="fromEmail" class="form-control" value="${user.email}" id="send_mail" required maxlength="50">
										</div>
									</div>
									<div class="form-group margin-b">
										<div class="form-tit">
											<label for="subject">Subject</label>
										</div>
										<div class="form-conts">
											<input type="text" name="subject" class="form-control" id="subject" required maxlength="50">
										</div>
									</div>
									<div class="form-group margin-b">
										<!-- 메세지 안내 영역 -->
										<div class="information-box">
											<span class="urgent-badge info" title="Information"></span>
											<p class="urgent-txt">Please include your name and affiliation when leaving a message.</p>
										</div>
										<!-- //메세지 안내 영역 -->
										<div class="form-tit">
											<label for="msg">Message</label>
										</div>
										<div class="form-conts">
											<textarea name="content" rows="10" class="form-control" cols="50" id="msg" required maxlength="3000"></textarea>
										</div> 
									</div>
									<!-- 기존 파일 업로드 -->
									<div class="form-group margin-b">						
										<!-- <div class="form-tit"> 
											<label for="msg">Attachments</label>
										</div> -->
	<!--  									<div class="form-conts">
											<input type="file" class="" name="attachments" multiple />
										</div> -->
										<div class="form-tit"> 
											<label for="msg">Attachments</label>
										</div>
										<div class="file-upload small" id="dropArea" role="region" aria-label="File upload area">
											<p class="txt">Drag a file here or click to upload.</p>
											<button type="button" class="btn tertiary ico-before ico-upload sm" onclick="document.getElementById('fileInput').click()">Select File</button>
											<input type="file" id="fileInput" name="attachments" multiple style="display: none;" aria-describedby="fileInputDesc"/>
											<ul id="fileList" class="cont-file-list"></ul>
											<div id="fileError"></div>
										</div> 
									</div>
									<div class="g-recaptcha-parent">
									  <div class="g-recaptcha-wrapper">
									    <div class="g-recaptcha"
									         data-sitekey="6LcVc5YrAAAAAIdq-sjoYkRs7jrKIj8DurhwD09v"
									         data-callback="onRecaptchaSuccess"
									         data-expired-callback="onRecaptchaExpired"></div>
									  </div>
									</div>
									
									<!-- //기존 파일 업로드 -->
									<div class="comp-btn-wrap">
<%-- 										<button type="button" class="btn secondary" onclick="location.href='${pageContext.request.contextPath}/login.do';">Go to Login</button> --%>
										<button type="submit" class="btn primary" id="sendMailBtn" disabled>Send Mail</button>  
									</div>
								</form>
							</div>
							<!-- right area -->
							<div class="contact-us-img-box">
								<div class="gini-cont-box gini-secretariat">
									<h3 class="area-tit organizer-gini">GINI Secretariat</h3>
									<p>If you need more information about us or have any questions regarding membership or event participation, feel free to contact us anytime. For any proposals or suggestions, please reach out to us by email or phone. We will get back to you as soon as possible.</p>
									<!-- <div class="cont-address">
										<p class="desc ico ico-location">(34430) #504, A Building, campus Innovation Park, 70 Hannam-ro, Daedeok-gu, Daejeon, South Korea</p>
										<ul class="info-list">
											<li><span class="title ico ico-tel">TEL</span><span class="value">+82-42-865-0550</span></li>
											<li><span class="title ico ico-fax">FAX</span><span class="value">+82-42-861-4309</span></li>
										</ul>
									</div> -->
									<div class="f-cnt-box column">
<!-- 										<div class="f-cnt top">
											<div class="link-sns">
												<a href="#" class="instagram" target="_blank"><span class="sr-only">Instagram</span></a>
												<a href="#" class="youtube" target="_blank"><span class="sr-only">YouTube</span></a>
												<a href="#" class="twitter" target="_blank"><span class="sr-only">트위터</span></a>
												<a href="#" class="facebook" target="_blank"><span class="sr-only">Facebook</span></a>
												<a href="#" class="blog" target="_blank"><span class="sr-only">블로그</span></a>
											</div>
										</div> -->
										<div class="f-cnt bottom">
											<div class="f-info">
<!-- 												<ul class="info-cs">
													<li><a href="tel:+82428650501"><strong class="strong ico ico-tel">+82-42-865-0550</strong></a></li>
													<li><strong class="strong ico ico-fax">+82-42-861-4309</strong><span class="span"></span></li>
												</ul> -->
												<p class="info-addr ico ico-location">(34430) GINI Secretariat, DiSTEP, 70 Hannam-ro, Daedeok-gu, Daejeon, South Korea</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- //right area -->
						</div>
						<!-- //row -->
<!-- 						<div class="comp-btn-wrap">
							<button type="submit" class="btn primary">Send Mail</button>  
						</div> -->
					</div>
					<!-- //contact us form -->
				</div>
			<!-- //섹션 컨텐츠 -->
		</div>
		<!-- //컨텐츠 영역 -->
	</div>
	<!-- //컨테이너 영역 -->
<script>
document.addEventListener('DOMContentLoaded', () => {
    let selectedFiles = [];
    const dropArea = document.getElementById('dropArea');
    const fileInput = document.getElementById('fileInput');
    const fileList = document.getElementById('fileList');
    const fileError = document.getElementById('fileError');
    const sendMailBtn = document.getElementById('sendMailBtn');

    if (!dropArea || !fileInput || !fileList || !fileError || !sendMailBtn) return;

    // 파일 리스트 UI 업데이트
    function updateFileList() {
        fileList.innerHTML = '';
        selectedFiles.forEach((file, index) => {
            const li = document.createElement('li');
            li.setAttribute('role', 'listitem');

            const span = document.createElement('span');
            const maxLength = 30; // 화면 표시용
            let displayName = file.name;
            if (displayName.length > maxLength) displayName = displayName.slice(0, maxLength) + '...';
            span.textContent = displayName;
            span.title = file.name;

            const deleteBtn = document.createElement('button');
            deleteBtn.type = 'button';
            deleteBtn.className = 'btn btn-txt ico-before ico-del sm h-auto';
            deleteBtn.addEventListener('click', () => {
                selectedFiles.splice(index, 1);
                const dt = new DataTransfer();
                selectedFiles.forEach(f => dt.items.add(f));
                fileInput.files = dt.files;
                updateFileList();
            });

            li.appendChild(span);
            li.appendChild(deleteBtn);
            fileList.appendChild(li);
        });
    }

    // 파일 유효성 검사
    function validateFiles(newFiles) {
        let totalSize = selectedFiles.reduce((sum, f) => sum + f.size, 0);
        for (let file of newFiles) {
            if (file.size > 30 * 1024 * 1024) return 'Each file must be smaller than 30MB.';
            if (file.name.length > 100) return 'File name must be shorter than 100 characters.';
            totalSize += file.size;
            if (totalSize > 100 * 1024 * 1024) return 'Total file size must not exceed 100MB.';
        }
        return null;
    }

    // 드래그 이벤트
    dropArea.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropArea.classList.add('dragover');
    });

    dropArea.addEventListener('dragleave', () => {
        dropArea.classList.remove('dragover');
    });

    dropArea.addEventListener('drop', (e) => {
        e.preventDefault();
        dropArea.classList.remove('dragover');

        const newFiles = Array.from(e.dataTransfer.files);

        for (let newFile of newFiles) {
            if (selectedFiles.some(f => f.name === newFile.name)) {
                fileError.textContent = 'A file with the same name already exists.';
                fileError.classList.add('cont-file-error');
                return;
            }
        }
        
        const errorMsg = selectedFiles.length + newFiles.length > 3
            ? 'You can upload up to 3 files.'
            : validateFiles(newFiles);

        if (errorMsg) {
            fileError.textContent = errorMsg;
            fileError.classList.add('cont-file-error');
            return;
        } else {
            fileError.textContent = '';
            fileError.classList.remove('cont-file-error'); // CSS 제거
        }

        selectedFiles = selectedFiles.concat(newFiles);
        const dt = new DataTransfer();
        selectedFiles.forEach(f => dt.items.add(f));
        fileInput.files = dt.files;

        updateFileList();
        fileError.textContent = '';
    });

    // 파일 선택
    fileInput.addEventListener('change', (e) => {
        const newFiles = Array.from(fileInput.files);

        for (let newFile of newFiles) {
            // 중복 파일명 체크
            if (selectedFiles.some(f => f.name === newFile.name)) {
                fileError.textContent = 'A file with the same name already exists.';
                fileError.classList.add('cont-file-error');
                e.target.value = '';
                return;
            }
        }
        
        const errorMsg = selectedFiles.length + newFiles.length > 3
            ? 'You can upload up to 3 files.'
            : validateFiles(newFiles);

        if (errorMsg) {
            fileError.textContent = errorMsg;
            fileError.classList.add('cont-file-error');
            e.target.value = '';
            return;
        } else {
            fileError.textContent = '';
            fileError.classList.remove('cont-file-error'); // CSS 제거
        }

        selectedFiles = selectedFiles.concat(newFiles);
        const dt = new DataTransfer();
        selectedFiles.forEach(f => dt.items.add(f));
        fileInput.files = dt.files;

        updateFileList();
        fileError.textContent = '';
    });

    // 메일 전송 버튼 중복 클릭 방지
    sendMailBtn.addEventListener('click', (e) => {
        if (sendMailBtn.disabled) {
            e.preventDefault();
            return;
        }
        sendMailBtn.disabled = true;
        sendMailBtn.textContent = 'Sending...';
        sendMailBtn.closest('form').submit();
    });
});

// reCAPTCHA 성공/만료 콜백
function onRecaptchaSuccess(token) {
    const btn = document.getElementById('sendMailBtn');
    if (btn) btn.disabled = false;
}

function onRecaptchaExpired() {
    const btn = document.getElementById('sendMailBtn');
    if (btn) btn.disabled = true;
}

// reCAPTCHA 크기 조정
function scaleCaptcha() {
    const container = document.querySelector('.g-recaptcha-parent');
    const wrapper = document.querySelector('.g-recaptcha-wrapper');

    if (!container || !wrapper) return;

    const recaptchaWidth = 302; // 구글 기본 width
    const containerWidth = container.offsetWidth;

    const scale = containerWidth < recaptchaWidth ? containerWidth / recaptchaWidth : 1;
    wrapper.style.transform = 'scale(' + scale + ')';
    wrapper.style.height = (78 * scale) + 'px'; // 기본 height 기준
}

window.addEventListener('load', scaleCaptcha);
window.addEventListener('resize', scaleCaptcha);
</script>
<script src="https://www.google.com/recaptcha/api.js?hl=en" async defer></script>

</html>