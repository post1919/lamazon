<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import = "com.lamazon.properties.Properties" %>

<!-- zTree -->
<!-- API = http://www.treejs.cn/v3/api.php -->
<link rel="stylesheet" href="/css/zTree/demo.css"                  type="text/css">
<link rel="stylesheet" href="/css/zTree/zTreeStyle/zTreeStyle.css" type="text/css">

<script type="text/javascript" src="/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/js/zTree/jquery.ztree.exedit.js"></script>

<SCRIPT type="text/javascript">
	<%-- <!-- --%>
	
	var curStatus = "init", curAsyncCount = 0, asyncForAll = false, goAsync = false;
	var log, className = "dark", curDragNodes, autoExpandNode;
	
	var setting = {
		treeId : "lamazon",
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false,
			expandSpeed:"fast"
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename,
			onClick: onClick,
			beforeCollapse: beforeCollapse,
			beforeExpand: beforeExpand,
			onCollapse: onCollapse,
			onExpand: onExpand,
			onDrop:onDrop
		}
	};

	var zNodes =[
		<c:set var="pId_no"       value="0" />
		<c:set var="pId_no_child" value="0" />
		
		<!-- 1 DEPTH -->
		<c:forEach var="item" items="${ADMIN_MENU_LIST}" varStatus="status_1">
			<c:if test="${status_1.count gt 1}">,</c:if> 
			{ 
			  id        : "${status_1.count}", pId      : 0,                  name         : "${item.AM_NAME}",      open        : true
			, AM_PK     : "${item.AM_PK}",     AM_CODE  : "${item.AM_CODE}",  AM_FULL_NAME : "${item.AM_FULL_NAME}", AM_AUTH     : "${item.AM_AUTH}",     AM_AUTH2  : "${item.AM_AUTH2}"
			, AM_PARENT : "${item.AM_PARENT}", AM_DEPTH : "${item.AM_DEPTH}", AM_IS_MAIN   : "${item.AM_IS_MAIN}",   AM_ORDERING : "${item.AM_ORDERING}", AM_MEMO   : "${item.AM_MEMO}"
			, AM_URI    : "${item.AM_URI}",    AM_ICON  : "${item.AM_ICON}",  AM_STATUS    : "${item.AM_STATUS}",    AM_INCODE   : "${item.AM_INCODE}",   AM_INDATE : "${item.AM_INDATE}"
			
				<c:set var="pId_no" value="${pId_no+1}" />
				
				<!-- 2 DEPTH -->
				<c:forEach var="item2" items="${item['CHILD1']}" varStatus="status_2">
				<c:if test="${status_2.count eq 1}">, children:[</c:if>
				<c:if test="${status_2.count gt 1}">,</c:if>
				{ 
				  id        : "${status_1.count}${status_2.count}", pId : ${pId_no}, name : "${item2.AM_NAME}"
				, AM_PK     : "${item2.AM_PK}",     AM_CODE  : "${item2.AM_CODE}",  AM_FULL_NAME : "${item2.AM_FULL_NAME}", AM_AUTH     : "${item2.AM_AUTH}",     AM_AUTH2  : "${item2.AM_AUTH2}"
				, AM_PARENT : "${item2.AM_PARENT}", AM_DEPTH : "${item2.AM_DEPTH}", AM_IS_MAIN   : "${item2.AM_IS_MAIN}",   AM_ORDERING : "${item2.AM_ORDERING}", AM_MEMO   : "${item2.AM_MEMO}"
				, AM_URI    : "${item2.AM_URI}",    AM_ICON  : "${item2.AM_ICON}",  AM_STATUS    : "${item2.AM_STATUS}",    AM_INCODE   : "${item2.AM_INCODE}",   AM_INDATE : "${item2.AM_INDATE}"
					  
					<c:set var="pId_no_child" value="${pId_no_child+1}" />
								
					<!-- 3 DEPTH -->
					<c:forEach var="item3" items="${item2['CHILD2']}" varStatus="status_3">
					<c:if test="${status_3.count eq 1}">, children:[</c:if>
					<c:if test="${status_3.count gt 1}">,</c:if>
					{ 
							id        : "${status_1.count}${status_2.count}${status_3.count}", pId : ${pId_no_child}, name : "${item3.AM_NAME}"
						  , AM_PK     : "${item3.AM_PK}",     AM_CODE  : "${item3.AM_CODE}",  AM_FULL_NAME : "${item3.AM_FULL_NAME}", AM_AUTH     : "${item3.AM_AUTH}",     AM_AUTH2  : "${item3.AM_AUTH2}"
						  , AM_PARENT : "${item3.AM_PARENT}", AM_DEPTH : "${item3.AM_DEPTH}", AM_IS_MAIN   : "${item3.AM_IS_MAIN}",   AM_ORDERING : "${item3.AM_ORDERING}", AM_MEMO   : "${item3.AM_MEMO}"
						  , AM_URI    : "${item3.AM_URI}",    AM_ICON  : "${item3.AM_ICON}",  AM_STATUS    : "${item3.AM_STATUS}",    AM_INCODE   : "${item3.AM_INCODE}",   AM_INDATE : "${item3.AM_INDATE}"
					}
					<c:if test="${status_3.last}">]</c:if>
					</c:forEach>
				}
				<c:if test="${status_2.last}">]</c:if>
				</c:forEach>
			}
		</c:forEach>
	];
	
	//기본정보 저장/수정
	function fn_default_save(node){
		//console.log("---------------- fn_default_save");
		//console.log(node.getParentNode());
		
		if( node == null ){
			
		} else {
			//최상위노드일경우
			if( node.getParentNode() == null){
				var treeObj = $.fn.zTree.getZTreeObj("lamazonTree");
				var nodes = treeObj.getNodes();
				
				console.log("---------------- treeObj.getNodes()");
				//console.log(treeObj.getNodes());
			
				for( var i=0; i<treeObj.getNodes().length; i++ ){
					var am_pk       = nodes[i].AM_PK;
					var am_name     = nodes[i].name;
					var am_parent   = "0";
					var am_depth    = "0";
					var am_ordering = i+1;
					
					console.log(i + " = am_pk=>"+am_pk+", am_name=>"+am_name+", am_parent=>"+am_parent + ", am_depth=>"+am_depth + ", am_ordering=>"+am_ordering);
					
					var paramData = {
							"am_pk":am_pk
							,"am_name":am_name
							,"am_parent":am_parent
							,"am_depth":am_depth
							,"am_ordering":am_ordering
					}
					
					var data = procAjax("/rest/blog/adminMenuDefaultSave", paramData, false);
					
					if(data == "1"){
						
					//신규등록일경우 새로생성된 AM_PK 셋팅
					} else if(data.result == "success_wuth_am_pk"){
						nodes[i].AM_PK = data.AM_PK;
						
					} else {                                                                                                                                                                                                                                                                                     
						alert("오류가 발생했습니다.");     
						//window.close();         
						document.location.reload();
					}
				}
			
			} else {
				console.log("----------------node.getParentNode().children");
				//console.log(node.getParentNode().children);
				
				var parent_am_pk = node.getParentNode().AM_PK;
				
				var childNodes = node.getParentNode().children;
				
				for( var i=0; i<childNodes.length; i++ ){
					
					var am_pk       = childNodes[i].AM_PK;
					var am_name     = childNodes[i].name;
					var am_parent   = parent_am_pk;
					var am_depth    = childNodes[i].level + 1;
					var am_ordering = i+1;
					
					console.log(i + " = am_name=>"+am_name+ " = am_parent=>"+am_parent + " = am_depth=>"+am_depth + " = am_ordering=>"+am_ordering);
					
					var paramData = {
							"am_pk":am_pk
							,"am_name":am_name
							,"am_parent":am_parent
							,"am_depth":am_depth
							,"am_ordering":am_ordering
					}
					
					var data = procAjax("/rest/blog/adminMenuDefaultSave", paramData, false);
					
					if(data == "1"){
						
					//신규등록일경우 새로생성된 AM_PK 셋팅
					} else if(data.result == "success_wuth_am_pk"){
						console.log(childNodes[i].AM_PK);
						childNodes[i].AM_PK = data.AM_PK;
						console.log(childNodes[i].AM_PK);
					} else {                                                                                                                                                                                                                                                                                     
						alert("오류가 발생했습니다.");     
						//window.close();         
						document.location.reload();
					}
				}
			}
		}
	}
	
	//메뉴 클릭했을때
	function onClick(event, treeId, treeNode, clickFlag) {
		
		//오른쪽 영역에 값들 셋팅
		$("#am_pk").val(treeNode.AM_PK); //순번
		$("#am_pk_txt").text(treeNode.AM_PK); //순번 HIDDEN
		
		//부모메뉴PK
		$("#am_parent option").each(function(){
			if( $(this).val() == treeNode.AM_PARENT ){
				$(this).prop("selected", "seleted");
			}
		});
		
		$("#am_code").val(treeNode.AM_CODE); //메뉴코드
		$("#am_name").val(treeNode.name); //메뉴명
		$("#am_full_name").val(treeNode.AM_FULL_NAME); //메뉴명 전체
		$("#am_auth").val(treeNode.AM_AUTH); //권한
		$("#am_auth2").val(treeNode.AM_AUTH2); //권한2
		$("#am_depth").val(treeNode.AM_DEPTH); //메뉴LEVEL	
		$("#am_uri").val(treeNode.AM_URI); //URI
		$("#am_is_main").val(treeNode.AM_IS_MAIN); //메인메뉴여부
		$("#am_ordering").val(treeNode.AM_ORDERING); //정렬순서
		$("#am_icon").val(treeNode.AM_ICON); //아이콘명
		$("#am_memo").val(treeNode.AM_MEMO); //메모
		$("#am_status").val(treeNode.AM_STATUS); //상태
		
		//showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;event="+event+"&nbsp;treeId="+treeId+"&nbsp;treeNode.tId="+treeNode.tId+"&nbsp;clickFlag="+clickFlag);
	}
	
	//마우스드래그중
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		
		//console.log("===beforeDrag treeNodes===");
		//console.log(treeNodes);
		
		//showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;treeId="+treeId+"&nbsp;treeNodes="+treeNodes);
		
		return true;
	}
	
	//드래그 후 드롭할때
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
		
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
		//showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"));
		return true;
	}
	
	//드래그 후 드롭할때(beforeDrop함수에서 true일때 넘어옴)
	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		
		//console.log(treeNodes);
		//console.log(targetNode);
		//return false;
		
		//기본정보 저장/수정
		fn_default_save(treeNodes[0]);
		
		//className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
		//showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"))
	}
	
	function dropPrev(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	
	function dropInner(treeId, nodes, targetNode) {
		if (targetNode && targetNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				if (!targetNode && curDragNodes[i].dropRoot === false) {
					return false;
				} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	
	function dropNext(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}

	function beforeDrag(treeId, treeNodes) {
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				curDragNodes = null;
				return false;
			} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
				curDragNodes = null;
				return false;
			}
		}
		curDragNodes = treeNodes;
		return true;
	}
	
	function beforeDragOpen(treeId, treeNode) {
		autoExpandNode = treeNode;
		return true;
	}
	
	//수정아이콘 클릭시
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("lamazonTree");
		zTree.selectNode(treeNode);
		setTimeout(function() {
			//if (confirm(treeNode.name + "' 수정하시겠습니까?")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			//}
		}, 0);
		return false;
	} 
	
	//메뉴삭제 아이콘 클릭
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("lamazonTree");
		zTree.selectNode(treeNode);
		
		if( typeof treeNode.AM_PK == "undefined" ){
			//return면 onRemove호출함
			return true;
			
		} else {
			//취소아니면 고고
			if( confirm("'" + treeNode.name + "' 삭제하시겠습니까?") ){
				var am_pk       = treeNode.AM_PK;
				var am_name     = treeNode.name;
				var am_delflag  = "0"; //사용 1, 삭제 0
				
				console.log("am_pk=>"+am_pk);
				
				var paramData = {
					  "am_pk"      : am_pk,
					  "am_delflag" : am_delflag 
				}
				
				var successFlag = procAjax("/rest/blog/adminMenuDelete", paramData, false);
				
				//정상적으로 이벤트 신청완료 되었을 경우 완료 메세지 띄움           
				if(successFlag == 1){
					
				}else if(successFlag == 5){                                                                                                                                                                                                                                                                               
					alert("실패");   
					//window.close();                                                                                                                                                                                                                              
				} else if(successFlag == 9){//이미 등록되어있는 경우                                                                                                                                                                                                                                                                                     
					alert("이미 등록되었습니다.");   
					//window.close();                                                                                                                                                                                                                              
				} else {                                                                                                                                                                                                                                                                                     
					alert("오류가 발생했습니다.");     
					//window.close();     
					document.location.reload();
				}
				
				return true;
				
			} else {
				return false;
			}
		}
	}
	
	function onRemove(e, treeId, treeNode) {
		//showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		//showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		if (newName.length == 0) {
			setTimeout(function() {
				var zTree = $.fn.zTree.getZTreeObj("lamazonTree");
				zTree.cancelEditName();
				alert("Node name can not be empty.");
			}, 0);
			return false;
		}
		
		//메뉴명이 바뀐경우만 고고
		if( treeNode.name != newName ){
			fn_name_update(treeNode, newName);
		}
		
		return true;
	}
	
	//메뉴명 수정
	function fn_name_update(treeNode, newName){
		var am_pk       = treeNode.AM_PK;
		var am_name     = newName;
		
		console.log("am_pk=>"+am_pk+", am_name=>"+am_name);
		
		var paramData = {
				  "am_pk"   : am_pk
				, "am_name" : am_name
		}
		
		var successFlag = procAjax("/rest/blog/adminMenuNameSave", paramData, false);
		
		//정상적으로 이벤트 신청완료 되었을 경우 완료 메세지 띄움           
		if(successFlag == 1){
			
		}else if(successFlag == 5){                                                                                                                                                                                                                                                                               
			alert("실패");   
			//window.close();                                                                                                                                                                                                                              
		} else if(successFlag == 9){//이미 등록되어있는 경우                                                                                                                                                                                                                                                                                     
			alert("이미 등록되었습니다.");   
			//window.close();                                                                                                                                                                                                                              
		} else {                                                                                                                                                                                                                                                                                     
			alert("오류가 발생했습니다.");     
			//window.close();     
			document.location.reload();
		}
	}
	
	//메뉴명 변경한후(엔터)
	function onRename(e, treeId, treeNode, isCancel) {
		//console.log(treeNode);
		//showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	}
	
	function showRemoveBtn(treeId, treeNode) {
		return true;
		//return !treeNode.isFirstNode;
	}
	
	function showRenameBtn(treeId, treeNode) {
		return true;
		//return !treeNode.isLastNode;
	}
	
	function beforeCollapse(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" beforeCollapse ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name );
		return (treeNode.collapse !== false);
	}
	
	function onCollapse(event, treeId, treeNode) {
		//showLog("[ "+getTime()+" onCollapse ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
	}	
	
	function beforeExpand(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" beforeExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name );
		return (treeNode.expand !== false);
	}
	
	function onExpand(event, treeId, treeNode) {
		//showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
	}
	
	function expandNodes(nodes) {
		if (!nodes) return;
		curStatus = "expand";
		var zTree = $.fn.zTree.getZTreeObj("lamazonTree");
		for (var i=0, l=nodes.length; i<l; i++) {
			zTree.expandNode(nodes[i], true, false, false);
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			} else {
				goAsync = true;
			}
		}
	}
	
	function reset() {
		if (!check()) {
			return;
		}
		asyncForAll = false;
		goAsync = false;
		//$("#demoMsg").text("");
		$.fn.zTree.init($("#lamazonTree"), setting);
	}
	
	function check() {
		if (curAsyncCount > 0) {
		//	$("#demoMsg").text(demoMsg.async);
			return false;
		}
		return true;
	}
	
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}

	var newCount = 1;
	
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		
		//아이콘눌러서 노드추가
		if (btn) btn.bind("click", function(){
			//console.log("=======Add Menu =======")
			//console.log(treeNode);
			
			if( treeNode.level >= 2 ){
				alert("3 DEPTH이상은 만들수 없습니다.");
				return false;
			}
			
			var zTree = $.fn.zTree.getZTreeObj("lamazonTree");
			var newNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)} );
			
			//console.log(newNode[0]);
			
			//기본정보 저장/수정
			fn_default_save(newNode[0]);
			
			return false;
		});
	};
	
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("lamazonTree");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	
	function expandNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("lamazonTree"),
		type = e.data.type,
		nodes = zTree.getSelectedNodes();
		if (type.indexOf("All")<0 && nodes.length == 0) {
			alert("Please select one parent node at first...");
		}

		if (type == "expandAll") {
			zTree.expandAll(true);
		} else if (type == "collapseAll") {
			zTree.expandAll(false);
		} else {
			var callbackFlag = $("#callbackTrigger").attr("checked");
			for (var i=0, l=nodes.length; i<l; i++) {
				zTree.setting.view.fontCss = {};
				if (type == "expand") {
					zTree.expandNode(nodes[i], true, null, null, callbackFlag);
				} else if (type == "collapse") {
					zTree.expandNode(nodes[i], false, null, null, callbackFlag);
				} else if (type == "toggle") {
					zTree.expandNode(nodes[i], null, null, null, callbackFlag);
				} else if (type == "expandSon") {
					zTree.expandNode(nodes[i], true, true, null, callbackFlag);
				} else if (type == "collapseSon") {
					zTree.expandNode(nodes[i], false, true, null, callbackFlag);
				}
			}
		}
	}
	
	$(document).ready(function(){
		$.fn.zTree.init($("#lamazonTree"), setting, zNodes);
		
		//초기 전체 닫음
		$.fn.zTree.getZTreeObj("lamazonTree").expandAll(false);
		
		//전체 펼치기
		$("#expandAllBtn").bind("click", {type:"expandAll"}, expandNode);
		
		//전체 닫기
		$("#collapseAllBtn").bind("click", {type:"collapseAll"}, expandNode);
		
		$("#selectAll").bind("click", selectAll);
	});
	<%-- //--> --%>
</SCRIPT>
<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: 2px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<!--// zTree -->

<link rel="stylesheet" href="/css/admin/style.css?ver=0.2" type="text/css">
<link rel="stylesheet" href="/css/admin/layout.css?ver=0.2" type="text/css">
<link rel="stylesheet" href="/css/admin/contents.css?ver=0.2" type="text/css">

<link rel="stylesheet" href="/css/new/base.css">
<link rel="stylesheet" href="/static/css/reset.css" type="text/css">
<link rel="stylesheet" href="/static/css/main.css?ver=0.2" type="text/css">

<!-- 익스 input타입 placeholder 사용 -->
<script type="text/javascript" src="/js/placeholders.min.js"></script>

<!--[if lt IE 9]>
<script src="/static/js/lib/html5shiv.min.js"></script>
<script src="/static/js/lib/respond.js"></script>
<![endif]-->

<script type="text/javascript" src="/js/jquery.ztree.all.min.js"></script>

<!-- 익스 input타입 placeholder 사용 -->
<script type="text/javascript" src="/js/placeholders.min.js"></script>

<script type="text/javascript" src="/js/new/jquery.form.min.js"></script>

<style>
	.regi_inner02 .titles_form { width:65%;padding-left:70px; }
	.regi_inner02 .row_preview{ height:640px; margin-right:25px; }
	.regi_inner02 .btn__group {top:597px;background:#d6795b;border-top:1px solid #d6795b;font-size:13px;}
	
	.regi_inner02 .titles_form table th { width: 130px;padding:13px 0 9px;font-weight:600;font-size:15px; }
	.regi_inner02 .titles_form table td { padding:10px 25px 14px 0; }
	
	.regi_inner02, .regi_inner03, .regi_inner04 { margin-left:50px; }
	
	.btn_regi_group {padding:10px	 0 0 0;}
	
	.input_text_number { text-align:right; }
	
	select { padding:0px !important;background: #fafafa;height: 28px;line-height: 10px; }
	
	.title_regi2 span { color:#777; font-weight:600; }
	
	.tf_text {width:65%;}
</style>

<form id="frm" name="frm" action="/admin/manage/menuSave" method="post">
	
    <div id="contents">
        <h2 class="screen_out">본문내용</h2>
        
        <div id="cMain" class="sub_container">
			<div class="inner_cont" id="_membership">
				<div id="mb_mange_regi" style="padding-bottom:1px;">
					
					<div class="regi_inner02">
						<div class="title_regi2">
							<!-- <h4>메뉴 기본정보</h4> -->
							<p>
								<span class="searchtag_emphasis">
									◎ 메뉴를 드래그해서 위치를 변경할수 있습니다.<br/>
									◎ 등록후엔 서버 재시작해야 적용내용이 반영됩니다.
								</span>
								<span id="log"></span>
							</p>
						</div>
					
						<div class="row__group">
							<div class="row_preview" id="img_logo_main_list" name="img_logo_main_list" style="cursor:pointer;background:;">
							
								<div class="btn__group" style="top:0px;">
									<button type="button" class="btn_modal" id="expandAllBtn">전체 펼치기</button>
									<button type="button" id="collapseAllBtn">전체 닫기</button>
								</div>
							
								<!-- zTree -->
								<div class="content_wrap">
									<div class="zTreeDemoBackground left" style="padding-top:39px">
										<ul id="lamazonTree" class="ztree"></ul>
									</div>
								</div>
								<!--// zTree  -->
								
								<div class="btn__group">
									<button type="button" class="btn_modal" id="btn_title_image_enter" data-modal="title_image_enter">적용하기</button>
									<button type="button" id="img_logo_main_delete" onclick="document.location.reload();">새로고침</button>
								</div>
							</div>
							
							<!-- .row_preview -->
							<div class="titles_form">
								<table>
	 								<tr>
										<th>순번</th>
										<td>
											<input type="hidden" id="am_pk" name="am_pk" value="${AMIN_MENU.AM_PK}" />
											<strong id="am_pk_txt"><c:out value="${AMIN_MENU.MI_PK}"/></strong>
										</td>
									</tr>
									<%-- 
									왼쪽 트리에서 바로처리하므로 사용안함
									<tr>
										<th style="padding-top:20px;">부모메뉴</th>
										<td>
											<div class="radio__wrapper">
												<div class="item">
													<select id="am_parent" name="am_parent" class="template_select">
														<option value="">선택</option>
														
														<c:forEach var="parentMenu" items="${ADMIN_MENU_LIST}" varStatus="status">
														<option value="${parentMenu.AM_PK}" <c:if test="${parentMenu.AM_PK eq AMIN_MENU.AM_PK}">selected</c:if> ><c:out value="${parentMenu.AM_NAME}"/></option>
														</c:forEach>
														
													</select>
												</div>
											</div>
										</td>
									</tr>
									--%>
									
									<tr>
										<th>메뉴코드</th>
										<td>
											<input type="text" id="am_code" name="am_code" value="${AMIN_MENU.AM_CODE}" class="tf_text" maxlength="50" />
										</td>
									</tr>
									
									<%-- 
									왼쪽 트리에서 바로처리하므로 사용안함
									<tr>
										<th>메뉴LEVEL</th>
										<td>
											<input type="text" id="am_depth" name="am_depth" class="tf_text tf_w2" value="${AMIN_MENU.AM_DEPTH}" />
										</td>
									</tr>
									
									<tr>
										<th>메뉴명</th>
										<td>
											<input type="text" id="am_name" name="am_name" value="${AMIN_MENU.AM_NAME}" class="tf_text" maxlength="50" />
										</td>
									</tr>
									--%>
									
									<tr>
										<th>메뉴명 전체</th>
										<td>
											<input type="text" id="am_full_name" name="am_full_name" value="${AMIN_MENU.AM_FULL_NAME}" class="tf_text" maxlength="50" />
										</td>
									</tr>
									
									<tr>
										<th>권한</th>
										<td>
											<select id="am_auth" name="am_auth" class="select_info" style="margin-left:0px;">
												<option value="0">선택</option>
												<c:forEach var="item" items="${U_TYPE}" varStatus="status">
												<option value="${item.CM_CODE}" <c:if test="${AMIN_MENU.AM_AUTH eq item.CM_CODE}">selected</c:if> ><c:out value="${item.CM_NAME}"/></option>
												</c:forEach>
											</select>
										</td>
									</tr>
									
									<tr>
										<th>권한2</th>
										<td>
											<select id="am_auth2" name="am_auth2" class="select_info" style="margin-left:0px;">
												<option value="0">선택</option>
												<c:forEach var="item" items="${U_TYPE}" varStatus="status">
												<option value="${item.CM_CODE}" <c:if test="${AMIN_MENU.AM_AUTH2 eq item.CM_CODE}">selected</c:if> ><c:out value="${item.CM_NAME}"/></option>
												</c:forEach>
											</select>
										</td>
									</tr>
									
									<tr>
										<th>URI</th>
										<td>
											<input type="text" id="am_uri" name="am_uri" value="${AMIN_MENU.AM_URI}" class="tf_text" maxlength="50" />
										</td>
									</tr>
									
									<tr>
										<th>메인메뉴여부</th>
										<td>
											<select id="am_is_main" name="am_is_main" class="select_info" style="margin-left:0px;">
												<option value="">선택</option>
												<option value="1" <c:if test="${AMIN_MENU.AM_IS_MAIN ne '0'}">selected</c:if> >예</option>
												<option value="0" <c:if test="${AMIN_MENU.AM_IS_MAIN eq '0'}">selected</c:if> >아니오</option>
											</select>
										</td>
									</tr>
									<%--
									왼쪽 트리에서 바로처리하므로 사용안함
									<tr>
										<th>정렬순서</th>
										<td>
											<input type="text" id="am_ordering" name="am_ordering" value="${AMIN_MENU.AM_ORDERING}" class="tf_text tf_w2" maxlength="50" />
										</td>
									</tr>
									--%>
									
									<tr>
										<th>아이콘명</th>
										<td>
											<input type="text" id="am_icon" name="am_icon" value="${AMIN_MENU.AM_ICON}" class="tf_text" maxlength="50" placeholder="아이콘 css명" />
										</td>
									</tr>
									
									<tr>
										<th>메모</th>
										<td>
											<input type="text" id="am_memo" name="am_memo" value="${AMIN_MENU.AM_MEMO}" class="tf_text" maxlength="50" placeholder="메모" />
										</td>
									</tr>
									
									<tr>
										<th>상태</th>
										<td>
											<select id="am_status" name="am_status" class="select_info" style="margin-left:0px;">
												<option value="1" <c:if test="${AMIN_MENU.MI_STATUS eq 1}">selected</c:if> >사용</option>
												<option value="0" <c:if test="${AMIN_MENU.MI_STATUS eq 0}">selected</c:if> >미사용</option>
											</select>
										</td>
									</tr>
									<%-- 
									<tr>
										<th>등록자</th>
										<td>
											<input type="hidden" id="am_incode" name="am_incode" value="${AMIN_MENU.AM_INCODE}" />
											<strong><c:out value="${AMIN_MENU.AM_INCODE}"/></strong>
										</td>
									</tr>
							
									<tr>
										<th>등록일</th>
										<td>
											<input type="hidden" id="am_indate" name="am_indate" value="${AMIN_MENU.AM_INDATE}" />
											<strong><c:out value="${AMIN_MENU.AM_INDATE}"/></strong>
										</td>
									</tr>
									--%>
									
									<%-- 
									<!-- checkbox -->
									<tr>
										<th>결제유형</th>
										<td>
											<div class="lbl_raido_grogup">
												<c:set var="PD_PAYTYPE1000" value="" />
												<c:set var="PD_PAYTYPE2000" value="" />
												<c:set var="PD_PAYTYPE3000" value="" />
												<c:set var="PD_PAYTYPE" value="${fn:split(AMIN_MENU.PD_PAYTYPE, ',')}"/>
												<c:forEach items="${PD_PAYTYPE}" var="searchtag">
											    	<c:if test="${searchtag eq '1000'}"><c:set var="PD_PAYTYPE1000" value="checked" /></c:if>
											    	<c:if test="${searchtag eq '2000'}"><c:set var="PD_PAYTYPE2000" value="checked" /></c:if>
											    	<c:if test="${searchtag eq '3000'}"><c:set var="PD_PAYTYPE3000" value="checked" /></c:if>
												</c:forEach>
												
												<label style="cursor:pointer;"><input type="checkbox" id="PD_PAYTYPE1000" name="PD_PAYTYPE" value="1000" ${PD_PAYTYPE1000} /> 선불</label>
												<label style="cursor:pointer;"><input type="checkbox" id="PD_PAYTYPE2000" name="PD_PAYTYPE" value="2000" ${PD_PAYTYPE2000} /> 일부 선불</label>
												<label style="cursor:pointer;"><input type="checkbox" id="PD_PAYTYPE3000" name="PD_PAYTYPE" value="3000" ${PD_PAYTYPE3000} /> 후불</label>
											</div>
										</td>
									</tr>
									
									<!-- radio -->
									<tr>
										<th>세금계산서</th>
										<td>
											<div class="lbl_raido_grogup">
												<label style="cursor:pointer;"><input type="radio" id="PD_TAXTYPE1000" name="PD_TAXTYPE" value="1000" <c:if test="${AMIN_MENU.PD_TAXTYPE eq '1000' or empty AMIN_MENU.PD_TAXTYPE}">checked</c:if> /> 발행가능</label>
												<label style="cursor:pointer;"><input type="radio" id="PD_TAXTYPE2000" name="PD_TAXTYPE" value="2000" <c:if test="${AMIN_MENU.PD_TAXTYPE eq '2000'}">checked</c:if> /> 발행불가</label>
											</div>
										</td>
									</tr>
									--%>
								</table>
								<!-- <div class="btn_regi_group"><button type="button" class="btn_01">대표이미지/설명 미리보기</button></div> -->
							</div>
							<!-- .titles_form -->
						</div>
					</div>
					<!-- .regi_inner02 -->
			
					<!-- 검색 Tag 요청 & 하단 버튼 영역 -->
					<div class="regi_inner05">
						
						<script>
						$(document).ready(function(){
							
							$("#btnDel").click(function(){
								if( confirm("삭제 하시겠습니까?") ){
									var data = {"mi_pk":"${mi_pk}"}
									$.ajax({
										type: "GET",
										dataType:"json",
										data: data,
										async: true,
										url: "/rest/manage/deleteMailInfo",
										success:function( data ) {
											if( data.result == "success" ){
												//목록으로
												$("#btnList").click();	
											} else {
												alert(data.result);
											}
										},
										fail:function() {
											error('error');
										}
									});
								}	
							});
							
						});
						</script>
						
						<%-- 
						<!-- 하단 버튼 영역 -->
						<div class="btn_regi_group">
							<button type="button" id="btnRegist" name="btnRegist" class="btn_01">등록 / 수정</button>
							
							<c:if test="${not empty mi_pk}">
							<button type="button" id="btnDel"    name="btnDel"    class="btn_02">삭제</button>
							</c:if>
							
							<button type="button" id="btnList"   name="btnList"   class="btn_03">목록으로</button>
						</div>
						--%>
					</div>
					<!-- .regi_inner05 -->
				</div>
			
			<script>
				$(document).ready(function(){
					
					//금액에 콤마
					//$("#PD_MINPRICE").val(fn_moneycomma($("#PD_MINPRICE").val()));
					
					//금액에 콤마
					//$("#PD_OPTPRICE1000").val(fn_moneycomma($("#PD_OPTPRICE1000").val()));
					
					//등록
					$("#btn_title_image_enter").click(function(){
						
						if( $("#am_pk").val() == "" ){
							alert("메뉴를 선택해주세요.");
							$("#am_pk").focus();
							return false;
						}
						
						if( $("#am_name").val() == "" ){
							alert("메뉴명을 입력하세요.");
							$("#am_name").focus();
							return false;
						}
						
						if( $("#am_full_name").val() == "" ){
							alert("메뉴명 전체를 입력하세요.");
							$("#am_full_name").focus();
							return false;
						}
						
						if( $("#am_is_main").val() == "" ){
							alert("메인메뉴여부를 선택하세요.");
							$("#am_is_main").focus();
							return false;
						}
						
						<%--
						if( $("#am_depth").val() == "" ){
							alert("메뉴LEVEL를 입력하세요.");
							$("#am_depth").focus();
							return false;
						}
						
						if( $("#am_ordering").val() == "" ){
							alert("정렬순서를 입력하세요.");
							$("#am_ordering").focus();
							return false;
						}
						--%>
						
						if( confirm("적용 하시겠습니까?") ){
							
							//runFlag : "1",                                                                                                                                                                                                                                                                                                
							var paramData   = {
									  "am_pk"        : $("#am_pk").val()
									, "am_code"      : $("#am_code").val()
									, "am_name"      : $("#am_name").val()
									, "am_parent"    : $("#am_parent").val()
									, "am_full_name" : $("#am_full_name").val()
									, "am_auth"      : $("#am_auth").val()
									, "am_auth2"     : $("#am_auth2").val()
									, "am_depth"     : $("#am_depth").val()
									, "am_uri"       : $("#am_uri").val()
									, "am_is_main"   : $("#am_is_main").val()
									, "am_ordering"  : $("#am_ordering").val()
									, "am_icon"      : $("#am_icon").val()
									, "am_memo"      : $("#am_memo").val()
									, "am_status"    : $("#am_status").val()
				            };
							
							var successFlag = procAjax("/rest/blog/adminMenuSave", paramData, false);
							
							//정상적으로 이벤트 신청완료 되었을 경우 완료 메세지 띄움           
							if(successFlag == 1){
								alert("적용되었습니다.");
								
								document.location.reload();
								
							}else if(successFlag == 5){ //등급 업데이트 실패                                                                                                                                                                                                                                                                                     
								alert("등록이 완료 되지않았습니다.");   
								//window.close();                                                                                                                                                                                                                              
							} else if(successFlag == 9){//이미 등록되어있는 경우                                                                                                                                                                                                                                                                                     
								alert("이미 등록되었습니다.");   
								//window.close();                                                                                                                                                                                                                              
							} else {                                                                                                                                                                                                                                                                                     
								alert("오류가 발생했습니다.");     
								//window.close();
								document.location.reload();
							} 
						}
					});
					
					//목록으로
					$("#btnList").click(function(){
						location.href="/admin/manage/adminMenu";
					});
				});
			
				//Ajax 호출                                                                                                                                                                                                                                                                                                         
				function procAjax(paramUrl, paramData, paramAsync, flag){	                                                                                                                                                                                                                                                                                                                  
					var returnValue = 0;                                                                                                                                                                                                                                                                                              
					$.ajax({                                                                                                                                                                                                                                                                                                          
						  url	  : paramUrl                                                                                                                                                                                                                                                                                              
						, type	  : "POST"                                                                                                                                                                                                                                                                                              
						, data	  : paramData                                                                                                                                                                                                                                                                                           
						, async	  : paramAsync                                                                                                                                                                                                                                                                                          
						, error	  : function(xhr, ajaxOptions, thrownError) {                                                                                                                                                                                                                                                           
										alert(xhr.status + " - " + xhr.statusText);                                                                                                                                                                                                                                                               
										alert("thrownError : " + thrownError);  
										//메모리 Clear                                                                                                                                                                                                                                                                                              
										clearMemory(xhr);
						            }                                                                                                                                                                                                                                                                                                               
					                                                                                                                                                                                                                                                                                                                  
						, success : function(data, status, xhr) {
							//var str = JSON.stringify(data);
							if( data.result == "success" ){
								returnValue = '1';
								
							} else if( data.result = "success_wuth_am_pk" ){
								returnValue = data;
								
							} else if( data.result == "need_login" ){
								returnValue = '10';
							}
							
							//메모리 Clear                                                                                                                                                                                                                                                                                              
							clearMemory(xhr);	                                                                                                                                                                                                                                                                                          
						}
					});                                                                                                                                                                                                                                                                                                               
					                                                                                                                                                                                                                                                                                                                  
					return returnValue;                                                                                                                                                                                                                                                                                               
				}                                                                                                                                                                                                                                                                                                                   
				                                                                                                                                                                                                                                                                                                                    
				// 메모리 Clear 함수                                                                                                                                                                                                                                                                                                
				function clearMemory(xhr){                                                                                                                                                                                                                                                                                                                   
					if (xhr != null){                                                                                                                                                                                                                                                                                                 
						xhr.onreadystatechange = null;                                                                                                                                                                                                                                                                                  
						xhr.abort = null;                                                                                                                                                                                                                                                                                               
						xhr = null;                                                                                                                                                                                                                                                                                                     
					}                                                                                                                                                                                                                                                                                                                 
				}
				
				//숫자만입력받기
				$('#am_ordering').keyup(function () { 
				    this.value = this.value.replace(/[^0-9]/g,'');
				});
			</script>
			
			</div>
        </div>
        <!--// cMain -->
    </div>
    <!--// contents -->
</form>