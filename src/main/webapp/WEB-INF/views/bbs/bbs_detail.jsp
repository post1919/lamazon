<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import = "com.lamazon.properties.Properties" %>

<script src="/js/jquery.number.min.js"></script> <!-- 금액 콤마찍기 -->

<style>
	.modal-title, .modal_sub_tit {text-align:left;}
	
	.datepicker_wrap{overflow:hidden;}
	.datepicker_area{position:relative;display:inline-block;width:45% !important;float:left;}
	.datepicker_area_block{position:relative;display:block;width:100%;}
	.datepicker_wrap .unit{width:10%;text-align:center;float:left;}
	.datepicker_area input{width:100% !important;padding-right:20px;}
	.datepicker_area img{position:absolute;top:8px;right:8px;}
	.datepicker_area_block img{position:absolute;top:15px;right:15px;}
	div.datepicker_area_block img{position:absolute;top:8px;right:8px;}
</style>
    
<section class="content-header">
	<h1>[1:1게시판]</h1>
</section>

<section class="content">
	<form id="bbsForm" name="bbsForm" method="post" action="/bbs/regist">
		<input type="hidden" id="b_pk" name="b_pk" value="<c:out value="${BBS.B_PK}"/>"/>
		<input type="hidden" id="r_u_pk" name="r_u_pk" value="<c:out value="${BBS.R_U_PK}"/>"/>
	    <input type="hidden" id="flag" name="flag" value="<c:out value="${flag}"/>"/>
   		
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">기본 정보</h3>
					</div>
					
					<div class="box-body table-responsive">
						<table class="table c_table">
							<colgroup>
								<col class="col-md-1">
								<col class="col-md-2">
								<col class="col-md-1">
								<col class="col-md-2">
							</colgroup>
							<tbody>
								<c:if test="${flag ne 'regist'}">
								<tr>
									<th>아이디 / 이름 / 회사명</th>
									<td>
										<input type="text" value="${BBS.U_ID} / ${BBS.U_NAME} / ${BBS.U_COMPANY}" class="form-control" disabled />
									</td>
									
									<th>등록일</th>
									<td>
										<input type="text" value="<c:out value="${BBS.B_INDATE}" />" class="form-control" disabled />
									</td>
								</tr>
								</c:if>
								
								<tr>
									<th>제목</th>
									<td colspan="3">
										<input type="text" id="b_title" name="b_title"  value="<c:out value="${BBS.B_TITLE}" />" style="width:100%;" class="form-control" maxlength="100" 
										<c:if test="${empty BBS.MINE and flag ne 'regist'}">disabled</c:if>
										/>
									</td>
								</tr>
								
								<tr>
									<th>내용</th>
									<td colspan="3">
										<textarea id="b_content" name="b_content" class="form-control" maxlength="255" style="min-height:500px;"
										<c:if test="${empty BBS.MINE and flag ne 'regist'}">disabled</c:if>
										><c:out value="${BBS.B_CONTENT}" escapeXml="false"/></textarea>
									</td>	
								</tr>
								
								<tr>
									<th>첨부파일</th>
									<td colspan="3">
										<div class="input-group" style="width:100%;">
											<div class="input-group-btn">
												<button type="button" class="btn btn-danger" onclick="selectFile(''); return false;" <c:if test="${empty BBS.MINE and flag ne 'regist'}">disabled</c:if> >첨부하기</button>
											</div>
											<input type="text" id="b_file" name="b_file" value="${item['B_FILE']}" style="width:100%;" class="form-control" readonly 
											<c:if test="${empty BBS.MINE and flag ne 'regist'}">disabled</c:if>
											/>
											<input type="hidden" id="b_file_rename" name="b_file_rename" value="${item['B_FILE_RENAME']}" />
											<input type="file" id="files" name="files" title="파일찾기" class="cropit-image-input btnUpload" accept=".jpg,.jpeg,.png,.gif,.pdf,.ppt,.xlsx,.xls" style="display:none;">
										</div>
										
										<c:if test="${not empty BBS.B_FILE}">
										<div>
											(<a href="<%= Properties.URL_B_FILE%>/${BBS.B_FILE_RENAME}" style="color:red;" target="_blank"><c:out value="${BBS.B_FILE}" /></a>)
										</div>
										</c:if>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="right_btn_wrap">
							<button type="button" id="btn_save" name="btn_save" class="btn btn-warning"
							<c:if test="${empty BBS.MINE and flag ne 'regist'}">disabled</c:if>
							>
							<c:choose>
								<c:when test="${flag eq 'regist'}">등 록</c:when>
								<c:otherwise>수 정</c:otherwise>
							</c:choose>
							</button>
						</div>
						
						<script>
							$(function(){
								$("#btn_save").on("click", function(){
									if( $("#b_title").val() == "" ){
										alert("제목을 입력해주세요.");
										$("#b_title").focus();
										return false;
										
									} else if( $("#b_content").val() == "" ){
										alert("내용을 입력해주세요.");
										$("#b_content").focus();
										return false;
									}
									
									var confirmMessage = "등록";
									if( "${flag}" != 'regist' ){
										confirmMessage = "수정";
									}
									
									if( confirm(confirmMessage + " 하시겠습니까?") ){
										var url = "/rest/bbs/regist/auth";
										
										var data = new FormData();
										data.append("b_pk",      $("#b_pk").val()        );
										data.append("r_u_pk",    $("#r_u_pk").val()      );
										data.append("b_title",   $("#b_title").val()     );
										data.append("b_content", $("#b_content").val()   );
										data.append("flag",      $("#flag").val()        );
										data.append("files",     $("#files")[0].files[0] );
										
										$.ajax({
										  url: url,
										  data: data,
										  async : true,
										  method: "POST",
										  dataType: "JSON",
										  processData : false,
										  contentType : false,
										  success: function(data, status, xhr) {
											console.log()
											if( data.result === 'success' ){
											  	item = data;
											  	
											  	alert(confirmMessage + ' 되었습니다.');	
												location.href="/bbs/detail?b_pk="+item.B_PK;
											  	
											} else if( data.result === 'exist' ){
											  	alert('존재하는 데이터가 있습니다.');
											} else if( data.result === 'no_permission' ){
												alert('권한이 없습니다.');
											} else {
											  	alert("처리중에 문제가 있었습니다.\n\n잠시후 다시 시도해주세요.");
											}
										  },
										  error: function(xhr) {
											console.log(xhr);
										  }
										});
									}
									
								});
							});
						</script>
						
					</div>
				</div>
			</div>
		</div>		
	</form>

	<c:if test="${flag ne 'regist'}">
	
		<c:forEach var="item" items="${BBS_REPLY}" varStatus="status">
		<!-- 댓글 -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">댓글</h3>
					</div>
					
					<div class="box-body table-responsive">
						<table class="table c_table">
							<colgroup>
								<col class="col-md-1">
								<col class="col-md-2">
								<col class="col-md-1">
								<col class="col-md-2">
							</colgroup>
							<tbody>
								<c:if test="${flag ne 'regist'}">
								<tr>
									<th>아이디 / 이름 / 회사명</th>
									<td>
										<input type="text" value="${item['U_ID']} / ${item['U_NAME']} / ${item['U_COMPANY']}" class="form-control" disabled />
									</td>
									
									<th>등록일</th>
									<td>
										<input type="text" value="<c:out value="${item['BR_INDATE']}" />" class="form-control" disabled />
									</td>
								</tr>
								</c:if>
								
								<tr>
									<th>내용</th>
									<td colspan="3">
										<textarea id="br_content_${status.count}" name="br_content_${status.count}" class="form-control" maxlength="255" placeholder="내용을 입력해주세요." style="min-height:70px;"
										<c:if test="${empty item['MINE'] and flag ne 'regist'}">disabled</c:if>
										><c:out value="${item['BR_CONTENT']}" escapeXml="false"/></textarea>
									</td>	
								</tr>
								
								<tr style="display:none;">
									<th>첨부파일</th>
									<td colspan="3">
										<div class="input-group" style="width:100%;">
											<div class="input-group-btn">
												<button type="button" class="btn btn-danger" onclick="selectFile('${status.count}'); return false;"
												<c:if test="${empty item['MINE'] and flag ne 'regist'}">disabled</c:if>
												>첨부하기</button>
											</div>
											<input type="text" id="br_file_${status.count}" name="br_file_${status.count}" value="${item['BR_FILE']}" style="width:100%;" class="form-control" readonly 
											<c:if test="${empty item['MINE'] and flag ne 'regist'}">disabled</c:if>
											/>
											<input type="hidden" id="br_file_rename_${status.count}" name="br_file_rename_${status.count}" value="${item['BR_FILE_RENAME']}" />
											<input type="file" id="files_${status.count}" name="files_${status.count}" title="파일찾기" class="cropit-image-input btnUpload" accept=".jpg,.jpeg,.png,.gif,.pdf,.ppt,.xlsx,.xls" style="display:none;">
										</div>
										
										<c:if test="${not empty itme['BR_FILE']}">
										<div>
											(<a href="<%= Properties.URL_B_FILE%>/${item['B_FILE_RENAME']}" style="color:red;" target="_blank"><c:out value="${item['BR_FILE']}" /></a>)
										</div>
										</c:if>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="right_btn_wrap">
							<input type="hidden" id="br_pk_${status.count}" name="br_pk_${status.count}" value="${item['BR_PK']}" />
							<input type="hidden" id="flag_${status.count}" name="flag_${status.count}" value="detail" />
							
							<button type="button" id="btn_detail_save_${status.count}" name="btn_detail_save_${status.count}" class="btn btn-warning"
							<c:if test="${empty item['MINE'] and flag ne 'regist'}">disabled</c:if>
							>
							<c:choose>
								<c:when test="${flag eq 'regist'}">등 록</c:when>
								<c:otherwise>수 정</c:otherwise>
							</c:choose>
							</button>
						</div>
						
						<script>
							$(function(){
								$("#btn_save").on("click", function(){
									if( $("#b_title").val() == "" ){
										alert("제목을 입력해주세요.");
										$("#b_title").focus();
										return false;
										
									} else if( $("#b_content").val() == "" ){
										alert("내용을 입력해주세요.");
										$("#b_content").focus();
										return false;
									}
									
									if( confirm("등록 하시겠습니까?") ){
										var url = "/rest/bbs/regist/auth";
										
										var data = new FormData();
										data.append("b_pk",      $("#b_pk").val()        );
										data.append("br_pk",      $("#br_pk").val()        );
										data.append("r_u_pk",    $("#r_u_pk").val()      );
										data.append("o_title",   $("#o_title").val()     );
										data.append("o_content", $("#o_content").val()   );
										data.append("flag",      $("#flag").val()        );
										data.append("files",     $("#files")[0].files[0] );
										
										$.ajax({
										  url: url,
										  data: data,
										  async : true,
										  method: "POST",
										  dataType: "JSON",
										  processData : false,
										  contentType : false,
										  success: function(data, status, xhr) {
											console.log()
											if( data.result === 'success' ){
											  	item = data;
											  	
											  	alert('등록 되었습니다.');	
												location.href="/bbs/detail?b_pk="+result.B_PK;
											  	
											} else if( data.result === 'exist' ){
											  	alert('존재하는 데이터가 있습니다.');
											} else if( data.result === 'no_permission' ){
												alert('권한이 없습니다.');
											} else {
											  	alert("처리중에 문제가 있었습니다.\n\n잠시후 다시 시도해주세요.");
											}
										  },
										  error: function(xhr) {
											console.log(xhr);
										  }
										});
									}
									
								});
							});
						</script>
						
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
		
		<c:if test="${flag ne 'regist'}">
			<!-- 댓글 등록 -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box box-danger">
						<div class="box-header">
							<h3 class="box-title">댓글 등록</h3>
						</div>
						
						<div class="box-body table-responsive">
							<table class="table c_table">
								<colgroup>
									<col class="col-md-1">
									<col class="col-md-2">
									<col class="col-md-1">
									<col class="col-md-2">
								</colgroup>
								<tbody>
									<c:if test="${flag ne 'regist'}">
									<tr>
										<th>아이디 / 이름 / 회사명</th>
										<td colspan="3">
											<input type="text" value="${USER['U_ID']} / ${USER['U_NAME']} / ${USER['U_COMPANY']}" class="form-control" disabled />
										</td>
									</tr>
									</c:if>
									
									<tr>
										<th>내용</th>
										<td colspan="3">
											<textarea id="br_content_0" name="br_content_0" class="form-control" maxlength="255" placeholder="내용을 입력해주세요." style="min-height:70px;"></textarea>
										</td>	
									</tr>
									
									<tr style="display:none;">
										<th>첨부파일</th>
										<td colspan="3">
											<div class="input-group" style="width:100%;">
												<div class="input-group-btn">
													<button type="button" class="btn btn-danger" onclick="selectFile('0'); return false;">첨부하기</button>
												</div>
												<input type="text" id="br_file_0" name="br_file_0" value="" style="width:100%;" class="form-control" readonly />
												<input type="hidden" id="br_file_rename_0" name="br_file_rename_0" value="" />
												<input type="file" id="files_0" name="files_0" title="파일찾기" class="cropit-image-input btnUpload" accept=".jpg,.jpeg,.png,.gif,.pdf,.ppt,.xlsx,.xls" style="display:none;">
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							
							<div class="right_btn_wrap">
								<input type="hidden" id="br_pk_0" name="br_pk_0" value="-1" />
								<input type="hidden" id="flag_0" name="flag_0" value="regist" />
								<button type="button" id="btn_detail_save_0" name="btn_detail_save_0" class="btn btn-warning">등 록</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</c:if>
</section>

<script>
$(document).ready(function(){
	$("[name^=btn_detail_save_]").on('click', function(){
		var idx = $(this).attr("id").split("_")[3];
		
		if( $("#br_content_"+idx).val() == "" ){
			alert("내용을 입력해주세요");
			$("#br_content_"+idx).focus();
			return false;
		
		} else {
			if( confirm('등록 하시겠습니까?') ){
                var url = "/rest/bbs/regist_detail/auth";
				
				var data = new FormData();
				data.append("b_pk",       $("#b_pk").val()             );
				data.append("u_pk",       $("#r_u_pk").val()           );
				data.append("br_pk",      $("#br_pk_"+idx).val()       );
				data.append("br_content", $("#br_content_"+idx).val()  );
				data.append("flag",       $("#flag_"+idx).val()        );
				data.append("files",      $("#files_"+idx)[0].files[0] );
				
				$.ajax({
				  url: url,
				  data: data,
				  async : true,
				  method: "POST",
				  dataType: "JSON",
				  processData : false,
				  contentType : false,
				  success: function(data, status, xhr) {
					if( data.result === 'success' ){
					  	item = data;
					  	
					  	if( $("#flag").val() == 'detail' ){
			   				alert('수정되었습니다.');
					  		
					  	} else {
			   				alert('등록되었습니다.');					  		
					  	}
					  	
					  	location.reload();
					  	
					} else if( data.result === 'exist' ){
					  	alert('존재하는 데이터가 있습니다.');
					} else if( data.result === 'no_permission' ){
						alert('권한이 없습니다.');
					} else {
					  	alert("처리중에 문제가 있었습니다.\n\n잠시후 다시 시도해주세요.");
					}
				  },
				  error: function(xhr) {
					console.log(xhr);
				  }
				});
			}
		}
	});
	
	//첨부파일 핸들링
	$("#files").change(function(e) {
		FileSelectHandler(e, "b_file");
	});
	
	//첨부파일 핸들링
	$("[name^=files_]").change(function(e) {
		var idx = $(this).attr("id").split("_")[1];
		FileSelectHandler(e, "br_file_"+idx);
	});
	
});

function FileSelectHandler(e, id) {
	var files = e.target.files || e.dataTransfer.files;
	
	for (var i = 0, f; f = files[i]; i++) {
		$("#"+id).val(f.name);
	}
}

function selectFile(idx) {
	if( idx === '' ){
		$("#files").click();
		
	} else {
		$("#files_"+idx).click();
	}
}

function uploadFile() {
	var files = document.frm.files.files;
	
	if(files.length) {
		//if(files.length==0) {
			//alert("파일을 선택해주세요.");
			//return false;
		//}
		
		for(var i=0;i<files.length;i++) {
			var file = files[i];
			var name = file.name;
			if((/\.(jpg|jpeg|png|gif|pdf|ppt|xlsx|xls)$/i).test(name)) {
				
			} else {
				alert(name+'은 올릴수 있는 파일이 아닙니다.');		
				return false;
			}
		}
	//} else {
		//alert("파일을 선택해주세요.");
		//return false;
	}
	
	return true;
}
</script>