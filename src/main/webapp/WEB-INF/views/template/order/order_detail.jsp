<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import = "com.lamazon.properties.Properties" %>

<c:if test="${flag ne 'regist'}">

	<c:forEach var="item" items="${ORDER_DETAIL}" varStatus="status">
	<!-- 상품 정보 -->
	<div class="row">
		<div class="col-md-12">
			<div class="box box-info">
				<div class="box-header">
					<h3 class="box-title">상품 정보 - ${status.count}</h3>
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
							<tr>
								<th>상품명</th>
								<td colspan="3">
									<input type="text" id="od_name_${status.count}" name="od_name_${status.count}"  value="${item['OD_NAME']}" style="width:100%;" class="form-control" maxlength="100" 
									<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
									/>
								</td>
							</tr>
							<tr>
								<th>수량</th>
								<td>
									<input type="text" id="od_count_${status.count}" name="od_count_${status.count}"  value="${item['OD_COUNT']}" style="width:100%;" class="form-control" maxlength="25" <%-- style="text-align:right;" onkeypress='onlyNumber()' --%> 
									<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
									/>
								</td>
								
								<th>색상</th>
								<td>
									<input type="text" id="od_color_${status.count}" name="od_color_${status.count}"  value="${item['OD_COLOR']}" style="width:100%;" class="form-control" maxlength="25" 
									<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
									/>
								</td>
							</tr>
							
							<tr>
								<th>사이즈</th>
								<td>
									<input type="text" id="od_size_${status.count}" name="od_size_${status.count}"  value="${item['OD_SIZE']}" style="width:100%;" class="form-control" maxlength="25" 
									<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
									/>
								</td>
								
								<th>브랜드</th>
								<td>
									<input type="text" id="od_brand_${status.count}" name="od_brand_${status.count}"  value="${item['OD_BRAND']}" style="width:100%;" class="form-control" maxlength="50" 
									<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
									/>
								</td>
							</tr>
							
							<tr>
								<th>운송장번호</th>
								<td>
									<input type="text" id="od_invoice_${status.count}" name="od_invoice_${status.count}"  value="${item['OD_INVOICE']}" style="width:100%;" class="form-control" maxlength="25" 
									<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
									/>
								</td>
								
								<th>라벨파일</th>
								<td>
									<div class="input-group">
										<div class="input-group-btn">
											<button type="button" class="btn btn-danger" onclick="selectFile('${status.count}'); return false;"
											<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>	
											>첨부하기</button>
										</div>
										<input type="text" id="od_file_${status.count}" name="od_file_${status.count}" value="${item['OD_FILE']}" style="width:100%;" class="form-control" readonly 
										<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
										/>
										<input type="hidden" id="od_file_rename_${status.count}" name="od_file_rename_${status.count}" value="${item['OD_FILE_RENAME']}" />
										<input type="file" id="files_${status.count}" name="files_${status.count}" title="파일찾기" class="cropit-image-input btnUpload" accept=".jpg,.jpeg,.png,.gif,.pdf,.ppt,.xlsx,.xls" style="display:none;">
									</div>
									<div>
										(<a href="<%= Properties.URL_OD_FILE%>/${item['OD_FILE_RENAME']}" style="color:red;" target="_blank"><c:out value="${item['OD_FILE']}" /></a>)
									</div>
								</td>
							</tr>
							
							<tr>
								<th>메모</th>
								<td colspan="3">
									<textarea id="od_memo_${status.count}" name="od_memo_${status.count}" class="form-control" maxlength="255"
									<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
									><c:out value="${item['OD_MEMO']}" escapeXml="false"/></textarea>
								</td>											
							</tr>
						</tbody>
					</table>
					
					<div class="right_btn_wrap">
						<input type="hidden" id="od_pk_${status.count}" name="od_pk_${status.count}" value="${item['OD_PK']}" />
						<input type="hidden" id="flag_${status.count}" name="flag_${status.count}" value="detail" />
						<button type="button" id="btn_detail_save_${status.count}" name="btn_detail_save_${status.count}" class="btn btn-warning"
						<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
						>수 정</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</c:forEach>
	
	<%-- 미입금상태 O_STATUS=1000 --%>
	<c:if test="${ORDER_MASTER.O_STATUS eq 1000}">
		<!-- 상품 등록 -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-danger">
					<div class="box-header">
						<h3 class="box-title">상품 등록</h3>
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
								<tr>
									<th>상품명</th>
									<td colspan="3">
										<input type="text" id="od_name_0" name="od_name_0"  value="" style="width:100%;" class="form-control" maxlength="100" />
									</td>
								</tr>
								<tr>
									<th>수량</th>
									<td>
										<input type="text" id="od_count_0" name="od_count_0"  value="" style="width:100%;" class="form-control" maxlength="25" <%-- style="text-align:right;" onkeypress='onlyNumber()'--%> />
									</td>
									
									<th>색상</th>
									<td>
										<input type="text" id="od_color_0" name="od_color_0"  value="" style="width:100%;" class="form-control" maxlength="25" />
									</td>
								</tr>
								
								<tr>
									<th>사이즈</th>
									<td>
										<input type="text" id="od_size_0" name="od_size_0"  value="" style="width:100%;" class="form-control" maxlength="25" />
									</td>
									
									<th>브랜드</th>
									<td>
										<input type="text" id="od_brand_0" name="od_brand_0"  value="" style="width:100%;" class="form-control" maxlength="50" />
									</td>
								</tr>
								
								<tr>
									<th>운송장번호</th>
									<td>
										<input type="text" id="od_invoice_0" name="od_invoice_0"  value="" style="width:100%;" class="form-control" maxlength="25" />
									</td>
									
									<th>라벨파일</th>
									<td>
										<div class="input-group">
											<div class="input-group-btn">
												<button type="button" class="btn btn-danger" onclick="selectFile('0'); return false;"
												<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>	
												>첨부하기</button>
											</div>
											<input type="text" id="od_file_0" name="od_file_0" value="" style="width:100%;" class="form-control" readonly /> 
											<input type="file" id="files_0" name="files_0" title="파일찾기" class="cropit-image-input btnUpload" accept=".jpg,.jpeg,.png,.gif,.pdf,.ppt,.xlsx,.xls" style="display:none;">
										</div>
									</td>
								</tr>
								
								<tr>
									<th>메모</th>
									<td colspan="3">
										<textarea id="od_memo_0" name="od_memo_0" class="form-control" maxlength="255"><c:out value="${ORDER_MASTER.OD_MEMO}" escapeXml="false" /></textarea>
									</td>											
								</tr>
							</tbody>
						</table>
						
						<div class="right_btn_wrap">
							<input type="hidden" id="od_pk_0" name="od_pk_0" value="-1" />
							<input type="hidden" id="flag_0" name="flag_0" value="regist" />
							<button type="button" id="btn_detail_save_0" name="btn_detail_save_0" class="btn btn-warning">등 록</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
		
	<script>
		$(document).ready(function(){
			$("[name^=btn_detail_save_]").on('click', function(){
				var idx = $(this).attr("id").split("_")[3];
				
				if( $("#od_name_"+idx).val() == "" ){
					alert("상품명을 입력해주세요");
					$("#od_name_"+idx).focus();
					return false;
					
				} else if( $("#od_count_"+idx).val() == "" ){
					alert("수량을 입력해주세요");
					$("#od_count_"+idx).focus();
					return false;
					
				} else if( $("#od_color_"+idx).val() == "" ){
					alert("색상을 입력해주세요");
					$("#od_color_"+idx).focus();
					return false;
					
				} else if( $("#od_size_"+idx).val() == "" ){
					alert("사이즈를 입력해주세요");
					$("#od_size_"+idx).focus();
					return false;
				
				} else if( $("#od_brand_"+idx).val() == "" ){
					alert("브랜드를 입력해주세요");
					$("#od_brand_"+idx).focus();
					return false;
					
				} else if( $("#od_invoice_"+idx).val() == "" ){
					alert("운송장번호를 입력해주세요");
					$("#od_invoice_"+idx).focus();
					return false;
					
				} else if( $("#od_file_"+idx).val() == "" ){
					alert("라벨파일 선택해주세요");
					$("#od_file_"+idx).focus();
					return false;
					
				} else {
					if( confirm('등록 하시겠습니까?') ){
		                var url = "/rest/order/regist_detail/auth";
						
						var data = new FormData();
						data.append("o_pk",       $("#o_pk").val()             );
						data.append("u_pk",       $("#r_u_pk").val()           );
						data.append("od_pk",      $("#od_pk_"+idx).val()       );
						data.append("od_name",    $("#od_name_"+idx).val()     );
						data.append("od_count",   $("#od_count_"+idx).val()    );
						data.append("od_color",   $("#od_color_"+idx).val()    );
						data.append("od_size",    $("#od_size_"+idx).val()     );
						data.append("od_brand",   $("#od_brand_"+idx).val()    );
						data.append("od_invoice", $("#od_invoice_"+idx).val()  );
						data.append("od_memo",    $("#od_memo_"+idx).val()     );
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
							console.log()
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
			$("[name^=files_]").change(function(e) {
				var idx = $(this).attr("id").split("_")[1];
				FileSelectHandler(e, "od_file_"+idx);
			});
			
		});

		function FileSelectHandler(e, id) {
			var files = e.target.files || e.dataTransfer.files;
			
			for (var i = 0, f; f = files[i]; i++) {
				$("#"+id).val(f.name);
			}
		}
		
		function selectFile(idx) {
			$("#files_"+idx).click();
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
</c:if>