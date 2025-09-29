<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function reportTitleAlertView_close(){
	$('#reportTitleAlertView').hide();
}

function fn_reportTitleAlertViewSave(){
	
	if($('#reportTitle').val() == ""){
		alertMsg("보고서 제목을 입력해주세요.");
		$('#reportTitle').focus();
		return false;
	}
	$('#reportTitleAlertView').hide();
	fn_updateAplplDataCnd();
}
</script>
<style>
.ext_tit input {
    color: #202020;
    font-size: 2rem;
    padding: 1.2rem 1rem;
    border: 1px solid #d5d5d5;
    margin-bottom: 1rem;
    border-radius: 0.5rem;
    width: 370px;
}
.ext_tit{
	padding:10px;
}

</style>

<div class="modal_wrap act" id="reportTitleAlertView" style="display: none; z-index:9988;">        
    <div class="alert">
        <button class="btn_close btn_close_bk" onclick="reportTitleAlertView_close();">닫기</button>
        <div class="alert_inner">
            <div>
                <h6><span style="display: flex"><img src="<c:url value='/images/ico/ico_report.png'/>" style="padding-right: 8px;">보고서생성</span></h6>
                 <div class="ext_tit">
	                  <input type="text" id="reportTitle" placeholder="보고서 제목을 입력해주세요." value="${result.title}">
	             </div>
                <p id="alertMsg"></p>   
                <div class="btn_wrap" style="margin-top:0;">
                <button class="btn" onclick="fn_reportTitleAlertViewSave();">확인</button>        
                </div>
            </div>
        </div>
    </div>
</div>