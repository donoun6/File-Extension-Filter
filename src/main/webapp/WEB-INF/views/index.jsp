<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" />
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <title>main</title>
 </head>
<body>
 <h1>File Extension Filter</h1>
	<c:forEach items="${getExtensionByDefaultCheck }" var="getExtensionByDefaultCheck">
		 <c:set var="checkBox" value="${getExtensionByDefaultCheck.checkBox}"></c:set>
		 <label><input class="default" type="checkbox" value=${getExtensionByDefaultCheck.extension } name="extension" <c:if test="${checkBox == true}"> checked </c:if> >${getExtensionByDefaultCheck.extension }</label>
	</c:forEach>
	<br>
	<input class="extensionType" type="text" placeholder="확장자 입력"><button class="addExtension">추가</button>
	<div>
		<div id="ajaxReturn" style="width: 200px; height: 200px"></div>		
	</div>
  <script>
 
    $(function(){
    	$(document).ready(function(){
			$(".addExtension").trigger("click");
		});
    	
    	$(".default").click(function(){
    		var checked = $(this).is(':checked');
    		var defaultVal = $(this).val();
    		var allData = {"checked": checked, "defaultVal": defaultVal}			
    			$.ajax({
    				async: true,
    				type: 'POST',
    				data: JSON.stringify(allData),
    				url: "DefaultVal",
    				dataType: "json",
    				contentType: "application/json; charset=UTF-8",
    			});
    	});
    	
    	$(".addExtension").click(function(){
    		var type = $(".extensionType").val();
    		$(".extensionType").val('');
    		$.ajax({
				async: true,
				type: 'GET',
				data: {type},
				url: "addType",
				dataType: "html",
				contentType: "application/json; charset=UTF-8",
				success: function(data) {
					$("#ajaxReturn").html(data);
				}
			});
    	});
    	
    });
  </script>
</body>
</html>
