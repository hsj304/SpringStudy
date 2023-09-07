<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.row1{
		width:650px;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	let fileIndex=0;
	$(function(){
		$('#addBtn').click(function(){
			$('#user-table > tbody').append(
				'<tr id="m'+(fileIndex)+'">'
			 +  '<td>첨부파일 '+(fileIndex+1)+'<input type=file size=20 name=files['+fileIndex+']>'
			 +  '</td>'
			 +  '</tr>'
			)
			fileIndex++;
		})
		$('#removeBtn').click(function(){
			if(fileIndex>0){
				$('#m'+(fileIndex-1)).remove()
				fileIndex--;
			}
		})
	})
</script>
</head>
<body>
	<div class="row row1">
		<h1 class=text-center>수정</h1>
		<form method=post action="../databoard/update_ok.do">
			<table class=table>
				<tr>
					<th width=15% class="text-right success">이름</th>
					<td width=85%>
						<input type="text" name=name size=15 class="input-sm" value="${vo.name }">
						<input type="hidden" name=no value="${vo.no }">
					</td>
				</tr>
				<tr>
					<th width=15% class="text-right success">제목</th>
					<td width=85%><input type="text" name=subject size=50 class="input-sm" value="${vo.subject }"></td>
				</tr>
				<tr>
					<th width=15% class="text-right success">내용</th>
					<td width=85%><textarea rows="10" cols="50" name="content">${vo.content }</textarea></td>
				</tr>
				<tr>
					<th width=15% class="text-right success">비밀번호</th>
					<td width=85%><input type="text" name=pwd size=10 class="input-sm"></td>
				</tr>
				<tr>
					<td colspan=2 class=text-center>
						<button class="btn btn-sm btn-primary">수정</button>
						<input type=button class="btn btn-sm btn-danger" value="취소" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>