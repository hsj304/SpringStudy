<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
	.container{
		margin-top: 50px;
	}
	.row{
		margin: 0 auto;
		width:960px;
	}
</style>
</head>
<body>
	<div class=container>
		<h1 class=text-center>{{message}}</h1>
	</div>
	<template id="aaa">
		<h1>Hello Template</h1>
		<h3>{{pdata}}</h3>
	</template>
<script>
	Vue.component('my-com1',{
		props:['pdata'],
		template:'#aaa'
	})
	new Vue({
		el:'.container',
		data:{
			message:'Hello Component',
		}
	})
</script>
</body>
</html>