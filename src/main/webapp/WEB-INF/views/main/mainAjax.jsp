<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<span class="successBox">${success }</span>
	<span class="errBox">${duplicateErr }${addErr }${stringErr }${deleteType }</span>
	<div class="ajaxWrap">
	<p class="countBox">${getCountBydefaultCheckFalse }/200</p>
		<c:forEach items="${getExtensionByDefaultCheck }" var="getExtensionByDefaultCheck" >
			<span class="customExtension">${getExtensionByDefaultCheck.extension }<button class="deleteExtension" value="${getExtensionByDefaultCheck.extension }">X</button></span>
		</c:forEach>
	</div>
	<!-- Script Zone -->
	  <script>
    $(function(){ //Jquery
    	$(".deleteExtension").click(function(){ 
    		location.reload(); //화면 새로고침
    		var type = $(this).val();//삭제할 커스텀 확장자
    		$.ajax({
				async: true,
				type: 'POST',
				data: type,
				url: "DeleteType",
				dataType: "json",
				contentType: "application/json; charset=UTF-8",
			});
    	});
    	
    });
  </script>
</html>