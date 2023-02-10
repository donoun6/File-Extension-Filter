<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<p>${getCountBydefaultCheckFalse }/200</p>
	<c:forEach items="${getExtensionByDefaultCheck }" var="getExtensionByDefaultCheck" >
		<span>${getExtensionByDefaultCheck.extension }<button class="deleteExtension" value="${getExtensionByDefaultCheck.extension }">X</button></span>
	</c:forEach>
	  <script>
 
    $(function(){
    	
    	$(".deleteExtension").click(function(){
    		location.reload();
    		var type = $(this).val();
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