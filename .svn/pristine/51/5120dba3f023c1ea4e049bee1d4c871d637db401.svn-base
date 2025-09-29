<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<c:if test="${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
    </script>
</c:if> 
<style>

.tbl-wrap {
  height: 900px !important;      
  overflow: auto !important;     
  margin-bottom: 0 !important;
  padding-bottom: 0 !important;
  position: relative !important;
}

.tbl-wrap .tbl.data thead tr.sticky{
  z-index: 9999;
}
.fixed-header-table .tbl {
  border-collapse: separate !important;
  border-spacing: 0 !important;
}

/* sticky 헤더 레이어 위로 올리고 배경 고정 */
.tbl-wrap thead {
  position: sticky;
  top: 0;
  z-index: 5;           
  background: #fff;      
}

.tbl-wrap tbody { 
  position: relative;
  z-index: 1;
}

.tbl-wrap thead tr { position: static !important; top: auto !important; z-index: auto !important; }

</style>


</style>
<!-- 컨테이너 영역 -->
<div id="container">
	<div class="page-title-wrap visual img-joint">
		<div class="visual-filter"></div>
			<div class="inner">
				<!-- breadcrumb -->  
					<nav class="breadcrumb-wrap" aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="home"><a href="#" class="txt">Home</a></li>
							<li><a href="#" class="txt">Permission Settings</a></li>
						</ol>
					</nav>
					<!-- breadcrumb --> 
					<!-- 페이지 타이틀 영역 -->  
					<div class="visual-area">
						<h2 class="h-tit">Permission Settings</h2>
					</div>  
				<!-- //페이지 타이틀 영역 -->
					
				</div>
			</div>
<!-- 로그인 영역 -->
<div class="inner">
	<div class="conts-area">
		<div class="conts-wrap">
			<div class="box-white">
				    <!-- <h2 class="section-title">Permission Settings</h2> -->
				    <div class="tbl-wrap fixed-header-table"> 
				    	<table class="tbl col data">
	    					<caption>User Management</caption>
						      <colgroup>
						         <col style="width: 90px;">
						         <col style="width: auto;">    
						      </colgroup> 
						    <thead>
						        <tr class="center sticky">  
						            <th colspan="2" rowspan="2">menu</th>
						            <th colspan="2">Non-member</th> 
						            <th colspan="3">General</th> 
						            <th colspan="3">Government</th>
						            <th colspan="3">Institution</th>
						            <th rowspan="2">Active</th>
						        </tr>
						        <tr class="center" style="top:49px; position:sticky; border-collapse: separate; border-spacing: 0; z-index: 5;">
						            <th>View</th>
						            <th>Download</th>  
						            <th>View</th>
						            <th>Create/Update/Delete</th>
						            <th>Download</th>
						            <th>View</th>
						            <th>Create/Update/Delete</th>
						            <th>Download</th>
						            <th>View</th>
						            <th>Create/Update/Delete</th>
						            <th>Download</th>
						        </tr>
						    </thead>
						    <tbody>
					            <c:forEach items="${boardPermissionList}" var="list"  varStatus="status">
								  <tr class="center">   
								  	<c:if test="${status.first}">
								  		<td class="big-depth" rowspan="4">About GINI</td>
								  	</c:if> 
								  	<c:if test="${status.count==5}">
								  		<td class="big-depth" rowspan="7">Network</td>
								  	</c:if> 
								  	<c:if test="${status.count==12}">
								  		<td class="big-depth" rowspan="5">Programs</td>
								  	</c:if>
								  	<c:if test="${status.count==17}">
								  		<td class="big-depth" rowspan="3">News</td>  
								  	</c:if>
								  	<c:if test="${status.count==20}">
								  		<td class="big-depth" rowspan="2">Members Area</td>
								  	</c:if>  
								    <td class="big-depth">  
								    	<div class="td-flex">
											<span id="name_display_${list.board_master_code}">${list.board_master_name}</span>
												<%-- <input type="hidden" id="name_input_${list.board_master_code}" value="${list.board_master_name}"/> --%>
											  <input type="text" id="name_input_${list.board_master_code}" value="${list.board_master_name}" style="display: none; width: 80%;" />
											  <div class="comp-btn-wrap">
											  <button type="button" id="btn_edit_${list.board_master_code}" class="btn xsm"
											          onclick="enableEdit('${list.board_master_code}')" >수정</button>
											  </div>
											  <div class="comp-btn-wrap">
											  <button type="button" id="btn_save_${list.board_master_code}" class="btn xsm"
											          onclick="saveName('${list.board_master_code}')" style="display: none;">저장</button>
											  </div>
								    	</div>
									</td>
								
								    <!-- G_read_auth -->
								    <td>
								    	<div class="td-flex">
									      <c:if test="${list.N_read_auth eq '-'}">-</c:if>
									      <c:if test="${list.N_read_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="N_GINI_view" onclick="updateAuth('${list.board_master_code}', 'N', 'read', this, '${loginUserId}')"
									                 id="chk_n_read_${list.board_master_code}"
									                 <c:if test="${list.N_read_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_n_read_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								
								    <!-- G_update_auth -->
								
								    <!-- G_file_auth -->
								    <td class="border-r">
								    	<div class="td-flex">
									      <c:if test="${list.N_file_auth eq '-'}">-</c:if>
									      <c:if test="${list.N_file_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="N_GINI_file" onclick="updateAuth('${list.board_master_code}', 'N', 'file', this, '${loginUserId}')"
									                 id="chk_n_file_${list.board_master_code}"
									                 <c:if test="${list.N_file_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_n_file_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      	</div>
								    </td>
								    <!-- G_read_auth -->
								    <td>
								    	<div class="td-flex">
									      <c:if test="${list.G_read_auth eq '-'}">-</c:if>
									      <c:if test="${list.G_read_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="G_GINI_view" onclick="updateAuth('${list.board_master_code}', 'G', 'read', this, '${loginUserId}')"
									                 id="chk_g_read_${list.board_master_code}"
									                 <c:if test="${list.G_read_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_g_read_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								    <!-- G_update_auth -->
								    <td>
								    	<div class="td-flex">
									      <c:if test="${list.G_update_auth eq '-'}">-</c:if>
									      <c:if test="${list.G_update_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="G_GINI_update" onclick="updateAuth('${list.board_master_code}', 'G', 'update', this, '${loginUserId}')"
									                 id="chk_g_update_${list.board_master_code}"
									                 <c:if test="${list.G_update_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_g_update_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								
								    <!-- G_file_auth -->
								    <td class="border-r">
								    	<div class="td-flex">
									      <c:if test="${list.G_file_auth eq '-'}">-</c:if>
									      <c:if test="${list.G_file_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="G_GINI_file" onclick="updateAuth('${list.board_master_code}', 'G', 'file', this, '${loginUserId}')"
									                 id="chk_g_file_${list.board_master_code}"
									                 <c:if test="${list.G_file_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_g_file_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      	</div>
								    </td>
								
								    <!-- C_read_auth -->
								    <td>
								    	<div class="td-flex">
									      <c:if test="${list.C_read_auth eq '-'}">-</c:if>
									      <c:if test="${list.C_read_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="C_GINI_view" onclick="updateAuth('${list.board_master_code}', 'C', 'read', this, '${loginUserId}')"
									                 id="chk_c_read_${list.board_master_code}"
									                 <c:if test="${list.C_read_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_c_read_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								
								    <!-- C_update_auth -->
								    <td>
								    	<div class="td-flex">
									      <c:if test="${list.C_update_auth eq '-'}">-</c:if>
									      <c:if test="${list.C_update_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="C_GINI_update" onclick="updateAuth('${list.board_master_code}', 'C', 'update', this, '${loginUserId}')"
									                 id="chk_c_update_${list.board_master_code}"
									                 <c:if test="${list.C_update_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_c_update_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								
								    <!-- C_file_auth -->
								    <td class="border-r">
								    	<div class="td-flex">
									      <c:if test="${list.C_file_auth eq '-'}">-</c:if>
									      <c:if test="${list.C_file_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="C_GINI_file" onclick="updateAuth('${list.board_master_code}', 'C', 'file', this, '${loginUserId}')"
									                 id="chk_c_file_${list.board_master_code}"
									                 <c:if test="${list.C_file_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_c_file_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								
								    <!-- O_read_auth -->
								    <td>
								    	<div class="td-flex">
									      <c:if test="${list.O_read_auth eq '-'}">-</c:if>
									      <c:if test="${list.O_read_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="O_GINI_view" onclick="updateAuth('${list.board_master_code}', 'O', 'read', this, '${loginUserId}')"
									                 id="chk_o_read_${list.board_master_code}"
									                 <c:if test="${list.O_read_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_o_read_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								
								    <!-- O_update_auth -->
								    <td>
								    	<div class="td-flex">
									      <c:if test="${list.O_update_auth eq '-'}">-</c:if>
									      <c:if test="${list.O_update_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="O_GINI_update" onclick="updateAuth('${list.board_master_code}', 'O', 'update', this, '${loginUserId}')"
									                 id="chk_o_update_${list.board_master_code}"
									                 <c:if test="${list.O_update_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_o_update_${list.board_master_code}"></label>
									        </div>
									      </c:if>
								      </div>
								    </td>
								
								    <!-- O_file_auth -->
								    <td class="border-r">  
								    	<div class="td-flex">
									      <c:if test="${list.O_file_auth eq '-'}">-</c:if>
									      <c:if test="${list.O_file_auth ne '-'}">
									        <div class="form-check" style="text-align: center;">
									          <input type="checkbox" name="O_GINI_file" onclick="updateAuth('${list.board_master_code}', 'O', 'file', this, '${loginUserId}')"
									                 id="chk_o_file_${list.board_master_code}"
									                 <c:if test="${list.O_file_auth eq 'Y'}">checked</c:if>>
									          <label for="chk_o_file_${list.board_master_code}"></label>
									        </div>
									      </c:if> 
								      </div>
								    </td>
								    <td>
								    	<div class="td-flex">
										    <div class="form-check" style="text-align: center;">
											  <input type="checkbox" id="chk_use_${list.board_master_code}" onclick="updateUseYn('${list.board_master_code}', this)" <c:if test="${list.board_use_yn eq 'Y'}">checked</c:if> />
											  <label for="chk_use_${list.board_master_code}"></label>
											</div>
										</div>
									</td>
								  </tr>
								</c:forEach>
						    </tbody>
						</table>  
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

function updateAuth(boardId, userType, authType, checkbox, userId) {
    var authValue = checkbox.checked ? 'Y' : 'N';

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "${pageContext.request.contextPath}/admin/updateAuth.do", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.success) {
                    alert("Permission updated successfully.");
                } else {
                    alert("Failed to update permission.");
                }
            } else {
            	alert("Server error: " + xhr.status);
            }
        }
    };

    var data = JSON.stringify({
        boardId: boardId,
        userType: userType,
        authType: authType,
        authValue: authValue,
        userId: userId
    });

    xhr.send(data);
}

function enableEdit(code) {
	  // 기존 표시 숨기고 input + 저장 버튼 보여주기
	  document.getElementById('name_display_' + code).style.display = 'none';
	  document.getElementById('name_input_' + code).style.display = 'inline-block';

	  document.getElementById('btn_edit_' + code).style.display = 'none';
	  document.getElementById('btn_save_' + code).style.display = 'inline-block';
	}

function saveName(code) {
	  var newName = document.getElementById('name_input_' + code).value.trim();

	  // 입력값 검증 (널 또는 공백 체크)
	  if (!newName) {
	    alert('Please enter the board name.');
	    return;
	  }

	  var xhr = new XMLHttpRequest();
	  xhr.open('POST', '${pageContext.request.contextPath}/updateBoardMasterName.do', true);
	  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

	  xhr.onreadystatechange = function () {
	    if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	        var response = JSON.parse(xhr.responseText);
	        if (response.success) {
	          alert('Changes have been saved.');
	          location.reload(); // UI 변경은 리로드로 처리
	        } else {
	          alert('Unable to save changes: ' + (response.message || 'Unknown error'));
	        }
	      } else {
	        alert('Unable to save changes. (HTTP status: ' + xhr.status + ')');
	      }
	    }
	  };

	  var data = {
	    boardMasterCode: code,
	    boardMasterName: newName
	  };

	  xhr.send(JSON.stringify(data));
	} 
	
function updateUseYn(code, checkbox) {
	  var isChecked = checkbox.checked;
	  var useYn = isChecked ? 'Y' : 'N';

	  var xhr = new XMLHttpRequest();
	  xhr.open('POST', '${pageContext.request.contextPath}/updateBoardUseYn.do', true);
	  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

	  xhr.onreadystatechange = function () {
	    if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	        var response = JSON.parse(xhr.responseText);
	        if (response.success) {
	          alert('Saved successfully.');
	          location.reload();
	        } else {
	          alert('Failed to save. Please try again.');
	          checkbox.checked = !isChecked; // 되돌림
	        }
	      } else {
	        alert('Server error. Please contact admin.');
	        checkbox.checked = !isChecked; // 되돌림
	      }
	    }
	  };

	  var data = {
	    boardMasterCode: code,
	    boardUseYn: useYn
	  };

	  xhr.send(JSON.stringify(data));
	}
	
</script>