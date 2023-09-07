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
			<div class="col-md-3" v-for="vo in goods_list">
		      <div class="thumbnail">
		        <a :href="'detail.do?no='+vo.no">
		          <img :src="vo.poster" alt="Nature" :title="vo.name" style="width:100%">
		          <div class="caption">
		            <p style="font-size: 9px;">{{vo.price}}</p>
		          </div>
		        </a>
		      </div>
		    </div>
		</div>
		<div style="height: 10px"></div>
		<div class=row>
			<div class=text-center>
				<ul class="pagination">
				  <li v-if="startpage>1"><a href="#" v-on:click="prev()">&lt;</a></li>
				  <li v-for="i in range(startpage,endpage)" :class="i==curpage?'active':''"><a href="#">{{i}}</a></li>
				  <li v-if="endpage<totalpage"><a href="#" v-on:click="next()">&gt;</a></li>
				</ul>
			</div>
		</div>
		<h3>최근 방문 상품</h3>
		<hr>
		<div class=row>
		
		</div>
	</div>
<script>
	new Vue({
		el:'.container-fluid',
		data:{
			goods_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0,
			goods_cookie:[]
		},//자동 호출 함수
		mounted:function(){
			this.send();
		},//사용자 정의 함수 => 반드시 호출
		methods:{
			//서버와 통신하는 함수
			send:function(){
				let _this=this;
				//axios.get(), axios.post()
				axios.get("http://localhost/web/goods/list_vue.do",{
					params:{
						page:this.curpage
					}
				}).then(function(response){
					console.log(response.data)
					_this.goods_list=response.data
					_this.curpage=response.data[0].curpage
					_this.totalpage=response.data[0].totalpage
					_this.startpage=response.data[0].startpage
					_this.endpage=response.data[0].endpage
				})
			},
			range:function(start,end){
				let arr=[];
				let length=end-start;
				for(let i=0;i<=length;i++){
					arr[i]=start;
					start++;
				}
				return arr;
			},
			prev:function(){
				this.curpage=this.startpage-1
				this.send();
			},
			next:function(){
				this.curpage=this.endpage+1
				this.send();
			}
		}
	})
</script>
</body>
</html>