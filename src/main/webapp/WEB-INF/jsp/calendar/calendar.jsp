<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.js'></script>
<script
	src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/locales/ko.global.min.js"></script>

<style>
/* mobile 전용 */
@media (max-width: 768px) {
 .fc-view-harness { 
    height: 60vh !important;   
  }
}
.slider-track {
  display: flex;
  transition: transform 0.3s ease;
  flex-direction:row;
  justify-content:flex-start;
  align-items:flex-start;
}

.slider-track > .event-list-box {
  border-radius: var(--krds-rd-8);
  border: 0.1rem solid #EBEBEB;
  gap:10px;
}

.slider-prev,
.slider-next {
  background-color: #fff;
  border: 1px solid #ccc;
  padding: 0;
  z-index: 10;
  border-radius: 3px;
  cursor: pointer;
}

.slider-prev {
  left: 0;
}
.slider-next {
  right: 0;
}

/* 공통 스타일 */
.event-list-tit {
	display: inline-block;
	padding-left: 0;
	background-repeat: no-repeat;
	background-size: 20px 20px;
	background-position: left center;
	font-weight: bold;
	min-height: 20px;
}

.fc-event:hover {
	filter: none !important;
	opacity: 1 !important;
}

.organizer-default {
	background-image:
		url('${pageContext.request.contextPath}/img/component/common/ico-event-gini.png');
}

.search-list .card-body .c-text:hover {
	background-color: transparent !important;
	box-shadow: none !important;
	border: none !important;
	outline: none !important;
	text-decoration: none !important;
}
.c-tit{

    width: 100% !important;
    overflow: hidden  !important; 

}
.btn-box{
    display: flex
;
    justify-content: flex-end;
    align-items: center;
}
.gini-cont-wrap .cont-tit-large.text-line{
margin-bottom:0;
}
#upcomingPageInfo , #ongoingPageInfo{
margin-right: 10px;

}
</style>
<div id="container">
	<!-- breadcrumb -->
	<div class="page-title-wrap visual img-members">
		<div class="visual-filter"></div>
		<div class="inner">
			<!-- breadcrumb -->
			<nav class="breadcrumb-wrap" aria-label="브레드크럼">
				<ol class="breadcrumb">
					<li class="home"><a href="${pageContext.request.contextPath}/index.do" class="txt">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/calendar.do" class="txt">Programs</a></li>
					<li><a href="${pageContext.request.contextPath}/calendar.do" class="txt">Events</a></li>
				</ol>
			</nav>
			<!-- breadcrumb -->
			<div class="visual-area">
				<h2 class="h-tit">Events</h2>
				<p>Schedule of Events</p>
			</div>
		</div>
	</div>

	<!-- 컨텐츠 영역 -->
	<div class="inner">
		<!-- 페이지 타이틀 영역 -->
		<!-- //페이지 타이틀 영역 -->
		<!-- 행사 일정 캘린더 영역 시작 -->
		<div class="Schedule-wrap">
			<div id="calendar" class="Schedule-left-box">
				<div class="calender-top_area"></div>
				<div class="calender-bottom-area">달력 영역</div>
			</div>
			<div class="Schedule-right-box">
				<div class="today-tit-wrap">
					<h2>
						<span id="todayFullDate" class="tit"></span> <span id="todayDay"
							class="tit"></span> <span id="todayDate" class="tit-date"></span>
					</h2>

				</div>
				<div class="event-list-wrap">
					<div class="gini-cont-wrap clickCalendar"
						style="display: block !important;"></div>
					<div class="gini-cont-wrap ongoingCont">
					  <h4 class="cont-tit-large green-circle text-line">Ongoing</h4>
					
					  <div class="slider-header">
					  <div class="btn-box">
					    <span class="slider-page-indicator" id="ongoingPageInfo">1 / 1</span>
					      <button class="slider-prev" data-target="ongoing-slider"></button>
					      <button class="slider-next" data-target="ongoing-slider"></button>
					    </div>
					    <div class="slider-controls">
					    </div>
					  </div>
					
					  <div class="slider-container">
					    <div class="slider-track ongoing-slider" id="ongoingSliderTrack">
					      <!-- 카드 렌더링됨 -->
					    </div>
					  </div>
					
					  <div class="no-event no-ongoing-event" style="display: none;">
					    <p class="no-event-ico">There are no ongoing event schedules.</p>
					  </div>
					</div>
					
					<div class="gini-cont-wrap upcomingCont">
					  <h4 class="cont-tit-large light-green-circle text-line">Upcoming</h4>
					
					  <div class="slider-header">
					  <div class="btn-box">
					  
					    <span class="slider-page-indicator" id="upcomingPageInfo">1 / 1</span>
					      <button class="slider-prev" data-target="upcoming-slider"></button>
					      <button class="slider-next" data-target="upcoming-slider"></button>
					    </div>
					    <div class="slider-controls">
					    </div>
					  </div>
					
					  <div class="slider-container">
					    <div class="slider-track upcoming-slider" id="upcomingSliderTrack">
					      <!-- 카드 렌더링됨 -->
					    </div>
					  </div>
					
					  <div class="no-event no-upcoming-event" style="display: none;">
					    <p class="no-event-ico">There are no upcoming event schedules.</p>
					  </div>
					</div>
					
				<%-- <div>${userYn} ::::::::::::${userType}</div> --%>
				<div class="btn-wrap">
					<c:if test="${userYn == 'Y' or userType == 'A'}">
						<button type="button" class="btn primary"
							onclick="location.href='${pageContext.request.contextPath}/calendarCreate.do'" style="margin-top:4rem;">Create Event</button>
					</c:if>

				</div>
			</div>


		</div></div>
		<!-- //검색 결과 : 리스트 상단 -->

		<!-- 한달 전체 행사 리스트  -->
		<ul class="search-list type1">
		</ul>
		<!-- //한달 전체 행사 리스트 -->

		<!-- paging : 웹 -->
		<div class="pagination w-page paging">
			<div class="row justify-content-center" id="divPagingArea"></div>
		</div>
		<!-- //paging : 웹 -->

		<!-- paging : 모바일 -->
<!-- 		<div class="pagination m-page">
			<div class="page-links page-navis">
				<a class="page-navi prev" href="#">이전</a>
				<span class="page-link link-dot"></span>
				<a class="page-navi page-num" href="#">1</a>
				<a class="page-navi page-num" href="#">2</a>
				<a class="page-navi page-num" href="#">3</a>
				<span class="page-link link-dot"></span>
				<a class="page-navi next" href="#">다음</a>
			</div>
		</div> -->

	
	<div id="modalOverlay"
		style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: #000; opacity: 0.5; z-index: 999;"></div>

	<!-- //paging : 모바일 -->
</div>
<!-- //컨텐츠 영역 -->
</div>
<!-- 일정 수정 모달 -->


<!-- //paging : 모바일 -->
</div>
<!-- //컨텐츠 영역 -->

</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	let calendar;
	let allEvents = [];
	let currentMonthStart = null;
	let currentMonthEnd = null;
	
	/* 페이지 로드 시,현재 날짜를 YYYY.MM.DD 형식으로 포맷팅 함수 */
	document.addEventListener("DOMContentLoaded", function() {
		  const dayNames = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
		  const today = new Date();

		  const year = today.getFullYear();
		  const month = (today.getMonth() + 1).toString().padStart(2, '0');
		  const date = today.getDate().toString().padStart(2, '0');
		  const day = dayNames[today.getDay()];

		  const fullDateStr = year + "." + month + "." + date + " (" + day + ")";
		  document.getElementById("todayFullDate").textContent = fullDateStr;
	});
	/* Date 객체   YYYY-MM-DD 형식으로 변환 함수 */
	function formatDateToYYYYMMDD(date) {
		  const yyyy = date.getFullYear();
		  const mm = (date.getMonth() + 1).toString().padStart(2, '0');
		  const dd = date.getDate().toString().padStart(2, '0');
		  return `\${yyyy}-\${mm}-\${dd}`;
	}

	/* Date나 문자형식 날짜를 "YYYY.MM.DD" 포맷으로 변환 함수   */
	function formatDateString(dateInput) {
		  const date = new Date(dateInput);
		  if (isNaN(date)) return "-";
		  const year = date.getFullYear();
		  const month = (date.getMonth() + 1).toString().padStart(2, "0");
		  const day = date.getDate().toString().padStart(2, "0");
		  return `\${year}.\${month}.\${day}`; 
	}
	
	/* FullCalendar 달력 출력 함수  */
	document.addEventListener('DOMContentLoaded', function () {
		  const calendarEl = document.getElementById('calendar');

		 // FullCalendar 생성
		    calendar = new FullCalendar.Calendar(calendarEl, {
		        locale: 'en',  // 달력 언어 
		        initialView: 'dayGridMonth',  // 초기 표시 뷰  
		        selectable: false,  // drag 속성
		        editable: false,  // 드래그/수정 속성
		        eventDisplay: 'block',  // 이벤트를 블럭 형태로 고정 (dot or block)
		        headerToolbar: {  // 상단 툴바 설정
		            left: 'title',            // 왼쪽 영역: 달력 제목
		            right: 'prev today next'  // 오른쪽 영역: 이전 달, 오늘, 다음 달 버튼
		        },
	    datesSet: function (info) {
	    	  const viewDate = info.view.currentStart;

	    	  const year = viewDate.getFullYear();
	    	  const month = viewDate.getMonth(); 

	    	  const start = new Date(year, month, 1);
	    	  const end = new Date(year, month + 1, 0); 

	    	  currentMonthStart = formatDateToYYYYMMDD(start);
	    	  currentMonthEnd = formatDateToYYYYMMDD(end);

	    	  //console.log("날짜 범위 체킁", currentMonthStart, "-", currentMonthEnd); 
	    	  loadCalendarList(1);
	      
	      
	    },
	    eventContent: function(arg) {
	        return {
	          html: `<span style="overflow: hidden;">\${arg.event.title}</span>`
	        };
	      },
	    events: function (info, successCallback) {
	      fetch('${pageContext.request.contextPath}/calendar/events.do')
	        .then(function (response) {
	          return response.json();
	        })
	        .then(function (data) {
	            const today = new Date();
	            today.setHours(0, 0, 0, 0);
	            const mappedEvents = data.map(function (ev) {
	            	const now = new Date();

	            	var sStr = ev.start || '';
	            	var eStr = ev.end || '';

	            	var s = new Date(ev.start);
	            	var e = ev.end ? new Date(ev.end) : null;

	            	var isDateOnly = function (v) { 
	            	  return typeof v === 'string' && /^\d{4}-\d{2}-\d{2}$/.test(v); 
	            	};
	            	var allDayLike = (ev.allDay === true) || isDateOnly(sStr) || isDateOnly(eStr);

	            	if (!e) {
	            	  e = new Date(s);
	            	  if (allDayLike) { 
	            	    e.setDate(e.getDate() + 1); 
	            	  }
	            	}

	            	// 날짜 단위 비교용
	            	var today = new Date(); 
	            	today.setHours(0,0,0,0);

	            	var s0 = new Date(s);
	            	s0.setHours(0,0,0,0);

	            	var e0 = new Date(e);
	            	e0.setHours(0,0,0,0);

	            	// 상태 계산 
	            	let status;
	            	if (today < s0) {
	            	  status = "UPCOMING";
	            	} else if (today >= s0 && today <= e0) {
	            	  status = "ONGOING";
	            	} else {
	            	  status = "COMPLETED";
	            	}

	            	let eventClass = "";
	            	if (status === "ONGOING") eventClass = "view-on";
	            	else if (status === "UPCOMING") eventClass = "view-up";
	            	else eventClass = "veiw-comp";

	            	  const fileList = ev.vo.fileGroupVO && ev.vo.fileGroupVO.fileDetailVOList ? ev.vo.fileGroupVO.fileDetailVOList : [];

	            	  return {
	            	    id: ev.id,
	            	    title: ev.title,
	            	    start: new Date(ev.start),
	            	    end: new Date(ev.end),
	            	    classNames: [eventClass],
	            	    extendedProps: {
	            	      eventDescription: ev.description,
	            	      organizer: ev.organizer,
	            	      eventStatus: status, 
	            	      location: ev.location,
	            	      contact: ev.contact,
	            	      fileDetailVOList: fileList,
	            	      externalLink : ev.externalLink
	            	    }
	            	  };
	            	});
	          allEvents = mappedEvents;
	          successCallback(mappedEvents);
	          displayTodayEvents();
	        });
	    }
	  });

	  calendar.render();
	});
	
	function formatDateTimeToString(date) {
		  function pad(n) {
		    return n.toString().padStart(2, '0');
		  }
		  var yyyy = date.getFullYear();
		  var MM = pad(date.getMonth() + 1);
		  var dd = pad(date.getDate());
		  var hh = pad(date.getHours());
		  var mm = pad(date.getMinutes());
		  var ss = pad(date.getSeconds());
		  return yyyy + '-' + MM + '-' + dd + ' ' + hh + ':' + mm + ':' + ss;
		}


	function loadCalendarListByMonth(startDateISO, endDateISO) {
		  axios.post('${pageContext.request.contextPath}/calendar/listAjax.do', {
		    startDate: startDateISO,
		    endDate: endDateISO,
		    currentPage: 1
		  }).then(function(res) {
		   // console.log("월간 일정 조회 결과", res.data);
		  }).catch(function(err) {
		    console.error("loadCalendarListByMonth 오류:", err);
		  });
	}
	
	/* 날짜의 시/분/초를 제거하고 00:00:00 상태의 Date 객체를 반환 */
	function stripTime(date) {
		  return new Date(date.getFullYear(), date.getMonth(), date.getDate());
	}
	
		
	/* 리스트 형태 일정 출력  함수   */ 
	function loadCalendarList(currentPage) {
		  if (typeof currentPage === 'undefined') currentPage = 1;

		  if (!currentMonthStart || !currentMonthEnd) return;
	
		 if (typeof currentPage === 'undefined') currentPage = 1;
		
		 if (!currentMonthStart || !currentMonthEnd) return;
		 const startDate = new Date(currentMonthStart);
		 startDate.setHours(0, 0, 0, 0);  
		
		 const endDate = new Date(currentMonthEnd);  
		 endDate.setHours(23, 59, 59, 0);           
		
		 const data = {
		   currentPage: currentPage,
		   startDate: formatDateTimeToString(startDate),
		   endDate: formatDateTimeToString(endDate)
		 };
			  
		  axios.post('${pageContext.request.contextPath}/calendar/listAjax.do', data)
		    .then(function(response) {
		      const res = response.data.list || response.data;
		      const list = res.content;
		      const container = document.querySelector(".search-list");
		      container.innerHTML = '';
				//console.log("response",response)
		      if (!Array.isArray(list) || list.length === 0) {
		    	  container.innerHTML = '<li class="li"><p>There are no registered schedules.</p></li>';
		    	  
		    	  document.querySelector("#divPagingArea").innerHTML = '';

		    	  return;
		    	}

		      list.forEach(function(event) {
		      const fileList = event.fileGroupVO && event.fileGroupVO.fileDetailVOList ? event.fileGroupVO.fileDetailVOList : [];
		        //console.log("fileList", fileList);
		        let fileDownloadButton = "";
		        	  let hyperLink = "";
		        if (fileList.length > 0) {
		        	const fileInfoList = fileList.map(function(f) {
		        		  return {
		        		    name: f.fileOriginalName,
		        		    path: f.fileSaveLocate
		        		  };
		        		});
		          const encodedFileInfoList = encodeURIComponent(JSON.stringify(fileInfoList));
		          fileDownloadButton =
		      	    '<button type="button" class="btn sm btn-txt ico-down ico-before" ' +
		      	    'title="attachment download" onclick="fn_dw(this)" ' +
		      	    'data-file-list="'+encodedFileInfoList+'">' +
		      	    'Attachment</button>';
		        }
		        
		        if(event.externalLink && event.externalLink.trim() !== "" ){
					  hyperLink =
						  '<button type="button" class="btn sm btn-txt ico-go ico-before" title="Hyperlink" onclick="window.open(\'' + event.externalLink + '\', \'_blank\')">Hyperlink</button>' 
						 
				  }
		        
		        const startDateObj = new Date(event.startDatetime);
		        const endDateObj   = event.endDatetime ? new Date(event.endDatetime) : null;
		        const now = new Date();

		        var sStr2 = event.startDatetime || '';
		        var eStr2 = event.endDatetime || '';
		        var isDateOnly2 = function (v) { return typeof v === 'string' && /^\d{4}-\d{2}-\d{2}$/.test(v); };
		        var allDayLike2 = isDateOnly2(sStr2) || isDateOnly2(eStr2);

		        var e2 = endDateObj ? new Date(endDateObj) : new Date(startDateObj);
		        var today = new Date();
		        today.setHours(0,0,0,0);

		        var sDate = new Date(startDateObj);
		        sDate.setHours(0,0,0,0);

		        var eDate = endDateObj ? new Date(endDateObj) : new Date(startDateObj);
		        eDate.setHours(0,0,0,0);

		        // endDate가 없고 allDayLike이면 하루 추가
		        if (!endDateObj && allDayLike2) {
		          eDate.setDate(eDate.getDate() + 1);
		        }

		        // 상태 계산 (날짜 기준)
		        let status = "";
		        if (today < sDate) {
		          status = "UPCOMING";
		        } else if (today >= sDate && today <= eDate) {
		          status = "ONGOING";
		        } else {
		          status = "COMPLETED";
		        }


		        let badgeClass = (status === "ONGOING") ? "bg1" : (status === "UPCOMING") ? "bg3" : "bg2";

		        const startDate = startDateObj.toLocaleDateString();
		        const endDate = endDateObj.toLocaleDateString();
		        const id = event.id;
				//console.log("listevent!!" , event)
		        const li = document.createElement("li");
		        li.className = "li";
		        li.innerHTML =
		            '<div class="in">' +
		                '<div class="card-top">' +
		                    '<span class="krds-badge ' + badgeClass + '">' + status + '</span>' +
		                '</div>' +
		                '<div class="card-body" style="cursor:pointer;">' +
		                    '<div class="c-text">' +
		                        '<p class="c-tit" onclick="location.href=\'' + '${pageContext.request.contextPath}' + '/calendar/detail.do?id=' + id + '\'">' +
		                            '<span class="span">' + event.eventTitle + '</span>' +
		                        '</p>' +
		                        '<p class="c-txt">' + (event.eventDescription || '') + '</p>' +
		                        '<p class="c-date">' +
		                            '<strong class="key"></strong>' +
		                            '<span class="value">' + startDate + ' - ' + endDate + '</span>' +
		                        '</p>' +
		                    '</div>' +
		                    '<div class="card-btn">' +
		                        fileDownloadButton +
		                        hyperLink +
		                    '</div>' +
		                '</div>' +
		            '</div>';

		        container.appendChild(li);
		      });

		      document.querySelector("#divPagingArea").innerHTML = res.pagingArea;
		      bindPagingEvents();
		    })
		    .catch(function(error) {
		      console.error('일정 목록 불러오기 실패:', error);
		    });
	}
	/* 캘린더 우측 템플릿 설정 함수   */ 
	function buildEventTemplate(event) {
	  const title = event.title;
	  const start = event.start;
	  const end = event.end;
	  const id = event.id;
	  const extendedProps = event.extendedProps || {};
	  const description = extendedProps.eventDescription || "";
	  const organizerRaw = (extendedProps.organizer || "").toLowerCase();
	  const organizerRaw2 = extendedProps.organizer || "";
	  const status = (extendedProps.eventStatus || "").toUpperCase();
	  const fileList = extendedProps.fileDetailVOList || [];
	  const link = extendedProps.externalLink;
	  const contextPath = '${pageContext.request.contextPath}';
	
	  let organizerClass = "";
	  switch (organizerRaw) {
	    case "gini": organizerClass = "organizer-gini"; break;
	    case "city of daejeon": organizerClass = "organizer-daejeon"; break;
	    case "city of dortmund": organizerClass = "organizer-dortmund"; break;
	    case "city of málaga": organizerClass = "organizer-malaga"; break;
	    case "county of montgomery, md": organizerClass = "organizer-montgomery"; break;
	    case "city of seattle": organizerClass = "organizer-seattle"; break;
	    case "province of québec": organizerClass = "organizer-quebec"; break;
	    default: organizerClass = "organizer-default"; break;
	  }
	
	  let fileDownloadButton = "";
	  if (fileList.length > 0) {
	    const fileInfoList = fileList.map(f => ({
	      name: f.fileOriginalName,
	      path: f.fileSaveLocate
	    }));
	    const encoded = encodeURIComponent(JSON.stringify(fileInfoList));
	    fileDownloadButton =
	      '<button type="button" class="btn xsm btn-txt ico-down ico-before" ' +
	      'title="attachment download" onclick="fn_dw(this)" ' +
	      'data-file-list="' + encoded + '"></button>';
	  }
	
	  let hyperLink = "";
	  if (link && link.trim() !== "") {
	    hyperLink =
	      '<button type="button" class="btn xsm btn-txt ico-go ico-before" title="Hyperlink" onclick="window.open(\'' + link + '\', \'_blank\')"></button>';
	  }
	
	  return (
	    '<div class="event-list-box"  style="width:100%">' +
	      '<div class="event-list ' + status.toLowerCase() + ' top" style="">' +
	        '<p class="event-list-tit ' + organizerClass + '" style=" width: 100vw ">' + organizerRaw2 + '</p>' +
	        '<div class="event-icon-box card-btn">' +
	          fileDownloadButton + hyperLink +
	        '</div>' +
	      '</div>' +
	      '<div class="event-list ' + status.toLowerCase() + ' bottom">' +
	        '<div class="side-bg card-body">' +
	          '<a href="#" class="c-text" style="cursor:pointer !important;" onclick="location.href=\'' + contextPath + '/calendar/detail.do?id=' + id + '\'">' +
	            '<p class="c-tit"><span class="span">' + title + '</span></p>' +
	            (description ? '<p class="c-txt">' + (description.length > 50 ? description.substring(0, 50) + '...' : description) + '</p>' : '') +
	            '<p class="c-date"><strong class="value">' + formatDateString(start) + ' - ' + formatDateString(end) + '</strong></p>' +
	          '</a>' +
	        '</div>' +
	      '</div>' +
	    '</div>'
	  );
	}



	/* 비동기 페이징 동적 적용 함수   */ 
	function bindPagingEvents() {
	  document.querySelectorAll(".clsPagingArea").forEach(function (el) {
	    el.addEventListener("click", function (e) {
	      e.preventDefault();
	      const currentPage = parseInt(this.dataset.currentPage);
	      loadCalendarList(currentPage);
	    });
	  });
	}
	
	/* 캘린더 우측  ongoing / upcomming 리스트 출력 함수   */ 
	function displayTodayEvents() {
	  const onlistBox = document.querySelector(".ongoing-slider");
	  const uplistBox = document.querySelector(".upcoming-slider");
	  const ongoingCont = document.querySelector(".ongoingCont");
	  const upcomingCont = document.querySelector(".upcomingCont");
	  const clickCalendar = document.querySelector(".clickCalendar");
	  const noOngoingBox = document.querySelector(".no-ongoing-event");
	  const noUpcomingBox = document.querySelector(".no-upcoming-event");
	  
	  const today = new Date();
	  today.setHours(0, 0, 0, 0);
	  onlistBox.innerHTML = '';
	  uplistBox.innerHTML = '';
	  clickCalendar.style.setProperty('display', 'none', 'important');
	  noOngoingBox.style.display = 'none';
	  noUpcomingBox.style.display = 'none';
	
	  let hasOngoing = false;
	  let hasUpcoming = false;
	  let anyRendered = false;
	  let nearestUpcoming = null;
	
	  allEvents.forEach(function (event) {
		  const start = new Date(event.start);
		  const end = new Date(event.end);
		  const status = (event.extendedProps.eventStatus || "").toUpperCase();

		  if (status === "UPCOMING" && start > today) {
		    const template = buildEventTemplate(event);
		    uplistBox.insertAdjacentHTML("beforeend", template);
		    hasUpcoming = true;
		  }

		  if (status === "ONGOING") {
		    const template = buildEventTemplate(event);
		    onlistBox.insertAdjacentHTML("beforeend", template);
		    hasOngoing = true;
		  }
		});

		if (hasOngoing) {
		  ongoingCont.style.setProperty('display', 'block', 'important');
		} else {
		  noOngoingBox.style.display = 'block';
		}

		if (hasUpcoming) {
		  upcomingCont.style.setProperty('display', 'block', 'important');
		} else {
		  noUpcomingBox.style.display = 'block';
		}

		initSliders();
	}
	/* function initSliders() {
		  const sliderGroups = [
		    { trackClass: "ongoing-slider", pageId: "ongoingPageInfo" },
		    { trackClass: "upcoming-slider", pageId: "upcomingPageInfo" }
		  ];

		  sliderGroups.forEach(function (group) {
			    var track = document.querySelector("." + group.trackClass);
			    var container = track ? track.parentElement : null;
			    var prevBtn = container && container.parentElement ? container.parentElement.querySelector(".slider-prev") : null;
			    var nextBtn = container && container.parentElement ? container.parentElement.querySelector(".slider-next") : null;
			    var pageInfo = container && container.parentElement ? container.parentElement.querySelector("#" + group.pageId) : null;
			    var items = track ? track.querySelectorAll('.event-list-box') : [];

		    if (!track || !prevBtn || !nextBtn || !pageInfo) return;

		    const cardFullWidth = 378.52;
		    const totalItems = track.children.length;
		    const containerWidth = container.offsetWidth;
		    const itemsPerPage = Math.floor(containerWidth / cardFullWidth) || 1;
		    const totalPages = Math.ceil(totalItems / itemsPerPage);
		    let currentPage = 1;

		    function updateSlider() {
		      if (totalItems === 0 || totalPages <= 1) {
		        track.style.transform = `translateX(0px)`;
		        prevBtn.style.display = 'none';
		        nextBtn.style.display = 'none';
		        pageInfo.style.display = 'none'; 
		      } else {
		        prevBtn.style.display = ''; 
		        nextBtn.style.display = ''; 
		        pageInfo.style.display = ''; 
		        const offset = (currentPage - 1) * cardFullWidth * itemsPerPage;
		        track.style.transform = `translateX(-\${offset}px)`;
		        pageInfo.textContent = `\${currentPage} / \${totalPages}`;
		        prevBtn.disabled = currentPage === 1;
		        nextBtn.disabled = currentPage === totalPages;
		      }
		    }

		    if (prevBtn) {
		        prevBtn.addEventListener("click", function () {
		            if (currentPage > 1) {
		                currentPage--;
		                updateSlider();
		            }
		        });
		    }

		    if (nextBtn) {
		        nextBtn.addEventListener("click", function () {
		            if (currentPage < totalPages) {
		                currentPage++;
		                updateSlider();
		            }
		        });
		    }

		    updateSlider();
		  });
		} */
		function initSliders() {
			  var sliders = [
			    { trackClass: "ongoing-slider", pageId: "ongoingPageInfo" },
			    { trackClass: "upcoming-slider", pageId: "upcomingPageInfo" }
			  ];

			  sliders.forEach(function (s) {
			    var track = document.querySelector('.' + s.trackClass);
			    var container = track ? track.parentElement : null;
			    var wrapper = container ? container.parentElement : null; 
			    var prevBtn = wrapper ? wrapper.querySelector('.slider-prev') : null;
			    var nextBtn = wrapper ? wrapper.querySelector('.slider-next') : null;
			    var pageInfo = wrapper ? wrapper.querySelector('#' + s.pageId) : null;
			    var btnBox = wrapper ? wrapper.querySelector('.btn-box') : null; 

			    if (!track || !container || !prevBtn || !nextBtn || !pageInfo) return;

			    var currentPage = 0;

			    function update() {
			      var totalItems = track.children.length;

			      if (totalItems <= 1) {
			        prevBtn.style.display = 'none';
			        nextBtn.style.display = 'none';
			        pageInfo.style.display = 'none';
			        if (btnBox) btnBox.style.display = 'none';
			        track.style.transform = 'translateX(0)'; 
			        return;
			      }

			      prevBtn.style.display = '';
			      nextBtn.style.display = '';
			      pageInfo.style.display = '';
			      if (btnBox) btnBox.style.display = '';

			      var firstCard = track.querySelector('.event-list-box');
			      var cardWidth = firstCard ? firstCard.getBoundingClientRect().width : 380;
			      var visibleCount = Math.floor(container.clientWidth / cardWidth) || 1;
			      var totalPages = Math.ceil(totalItems / visibleCount);
			      var offset = currentPage * container.clientWidth;

			      track.style.transform = 'translateX(-' + offset + 'px)';
			      pageInfo.textContent = (currentPage + 1) + ' / ' + totalPages;

			      prevBtn.disabled = currentPage === 0;
			      nextBtn.disabled = currentPage === totalPages - 1;
			    }

			    prevBtn.onclick = function () {
			      if (currentPage > 0) {
			        currentPage--;
			        update();
			      }
			    };

			    nextBtn.onclick = function () {
			      var firstCard = track.querySelector('.event-list-box');
			      var cardWidth = firstCard ? firstCard.getBoundingClientRect().width : 380;
			      var visibleCount = Math.floor(container.clientWidth / cardWidth) || 1;
			      var totalPages = Math.ceil(track.children.length / visibleCount);
			      if (currentPage < totalPages - 1) {
			        currentPage++;
			        update();
			      }
			    };

			    window.addEventListener('resize', update);
			    update();
			  });
			}



	/*  파일 다운로드 함수 */ 
	function fn_dw(el) {
		 const contextPath = '${pageContext.request.contextPath}';
		  const fileListStr = el.dataset.fileList;
		  if (!fileListStr) {
		    alert("There is no file to download.");
		    return;
		  }

		  let fileList;
		  try {
		    fileList = JSON.parse(decodeURIComponent(fileListStr));
		  } catch (e) {
		    alert("File information parsing error.");
		    console.error(e);
		    return;
		  }

		  if (!Array.isArray(fileList) || fileList.length === 0) {
		    alert("There is no file information.");
		    return;
		  }

		  if (fileList.length >= 2) {
		    const params = fileList.map(function(f) {
		      return 'fileNames=' + encodeURIComponent(f.path);
		    }).join("&");
		    const url = contextPath + '/downloadMulti.do?' + params;
		    location.href = url;
		  } else {
		    const file = fileList[0];
		    const url = contextPath +
		      '/download.do?fileName=' + encodeURIComponent(file.path) +
		      '&originalName=' + encodeURIComponent(file.name);
		    location.href = url;
		  }
	}

</script>

