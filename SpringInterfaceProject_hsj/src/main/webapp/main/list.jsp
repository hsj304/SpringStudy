<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container-fluid{
		margin-top: 50px;
	}
	.row{
		margin:0 auto;
		width:100%;
	}
</style>
</head>
<body>
	<div class=container-fluid>
		<div class=row>
			<div class="col-sm-8">
				<div class="col-md-3" v-for="vo in list">
			      <div class="thumbnail">
			        <a href="#">
			          <img :src="vo.gimage" alt="Nature" style="width:300px;height: 200px;">
			          <div class="caption">
			            <p style="font-size: 9px;">{{vo.gname}}</p>
			          </div>
			        </a>
			      </div>
			    </div>
			</div>
			<div class="col-sm-4">
				
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div class=row>
			<div class=text-center>
				<input type=button value="이전" class="btn btn-sm btn-danger" v-on:click="prev()">
				{{curpage}} page / {{totalpage}} pages
				<input type=button value="다음" class="btn btn-sm btn-primary" v-on:click="next()">
			</div>
		</div>
	</div>
	
<script>
	new Vue({
		el:'.container-fluid',
		data:{
			curpage:1,
			totalpage:0,
			list:[]
		},
		mounted:function(){
			this.send();
		},
		methods:{
			send:function(){
				axios.get("http://localhost/web/main/list_vue.do",{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data);
					this.list=response.data;
					this.curpage=response.data[0].curpage;
					this.totalpage=response.data[0].totalpage;
					
				})
			},
			next:function(){
				this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
				this.send();
			},
			prev:function(){
				this.curpage=this.curpage>1?this.curpage-1:this.curpage;
				this.send();
			}
		}
	})
</script>
</body>
</html>