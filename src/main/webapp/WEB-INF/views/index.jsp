<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="<c:url value='/resources/common/initial.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <title>main</title>
 </head>
<body>
<main>
	<div class="wrap">
		<article>
			<h1 class="title">Flow</h1>
			<table>
				<tr class="default">
					<td>고정확장자</td>
					<td>
					<c:forEach items="${getExtensionByDefaultCheck }" var="getExtensionByDefaultCheck">
						<c:set var="checkBox" value="${getExtensionByDefaultCheck.checkBox}"></c:set>
						<label><input style="margin-left: 0;" class="default" type="checkbox" value=${getExtensionByDefaultCheck.extension } name="extension" <c:if test="${checkBox == true}"> checked </c:if> >${getExtensionByDefaultCheck.extension }</label>
					</c:forEach>
					<td>
				</tr>
				<tr>
					<td>커스텀확장자</td>
					<td><input class="extensionType" type="text" placeholder="확장자 입력 후 엔터를 누르세요."><button class="addExtension">+추가</button></td>
				</tr>
				<tr>
					<td></td>
					<td><div id="ajaxReturn"></div> <button class="initial">초기화</button></td>
				</tr>
			</table>
		</article>
		<footer><p>Copyright &copy; donoun6 2023</p></footer>
	</div>
	<div class="popUp">
		<div class="popUpWrap">
			<div>
				<h2>초기화 하시겠습니까?</h2>
				<div class="btnBox">
					<form method="post"><button class="yes" type="submit">네</button></form>
					<button class="no">아니요</button>
				</div>
			</div>
		</div>
	</div>
	
</main>
<!-- Script Zone -->
  <script>
    $(function(){//jquery
    	$(document).ready(function(){//DOM 이 생성된후 호출하는 callback함수 
			$(".addExtension").trigger("click"); //해당 클래스에 trigger메서드를 사용하여 자동으로 클릭
		});
    	
    	$(".default").click(function(){
    		var checked = $(this).is(':checked'); //체크 여부
    		var defaultVal = $(this).val(); // 체크박스의 값
    		var allData = {"checked": checked, "defaultVal": defaultVal} //Map형태로 데이터를 묶는다.			
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
    		var type = $(".extensionType").val(); //입력한 값
    		$(".extensionType").val(''); //text 초기화
    		$.ajax({
				async: true,
				type: 'GET',
				data: {type},
				url: "addType",
				dataType: "html",
				contentType: "application/json; charset=UTF-8",
				success: function(data) {
					$("#ajaxReturn").html(data); //html형태의 데이터를 가져온다
				}
			});
    	});
    	
    	$(".extensionType").keyup(function(event) { 
            if (event.which === 13) { //enter 작동
                $(".addExtension").click();
            }
        });
     
    	$(".initial").click(function(){
    		$(".popUp").addClass("on");
    	});
    	
    	$(".no").click(function(){
    		$(".popUp").removeClass("on");
    	});
    	
    });
  </script>
</body>
</html>
