//<![CDATA[
$(document).ready(function() {
	$("#membergroup").change( function() {
		
		$.ajax({
			type: "POST",
			url: "/ajaxCommon/getDepartment.do",
			data: { membergroup : $(this).val() }
			})
			.done(function( data ) {
				$("#member_dept").html( data );	
				
				
			});
		
		
		
	})  ;  
});



//]]>