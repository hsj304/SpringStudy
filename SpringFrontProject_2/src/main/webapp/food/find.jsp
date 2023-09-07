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
			<input type=text ref="fd" size=30 class=input-sm v-model="fd">
			<input type=button class="btn btn-sm btn-primary" value="검색" v-on:click="findData()">
		</div>
		<div style="height: 20px;"></div>
		<div class=row>
			<div class="col-md-3" v-for="vo in food_list">
		      <div class="thumbnail">
		        <a href="#">
		          <img :src="vo.poster" alt="Nature" style="width:100%">
		          <div class="caption">
		            <p style="font-size: 9px;">{{vo.name}}&nbsp;<span style="color:orange">{{vo.score}}</span></p>
		          </div>
		        </a>
		      </div>
		    </div>
		</div>
	
		<div style="height: 10px"></div>
		<div class=row>
			<div class=text-center>
			    <ul class="pagination">
			        <li :disabled="curpage == 1"><a href="#" v-on:click="selectpage(curpage - 1)">&lt;</a></li>
			        <li v-for="i in range(startpage, endpage)" :class="i==curpage?'active':''">
			            <a href="#" v-on:click="selectpage(i)">{{i}}</a>
			        </li>
			        <li :disabled="curpage == totalpage"><a href="#" v-on:click="selectpage(curpage + 1)">&gt;</a></li>
			    </ul>
			</div>
		</div>
	</div>
<script>
	new Vue({
		el:'.container-fluid',
		data:{
			fd:'마포',
			food_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0
		},
		//시작과 동시에 데이터를 읽어서 출력
		//Callback => 시스템에 의해 자동으로 호출되는 함수 => 생명주기
		//window.onload => $(function(){}) => componentDidMount => useEffect()
		mounted:function(){
			this.send()
		},
		methods:{
			send:function(){
				//스프링 서버를 연결해서 => 필요한 데이터를 전송, 결과값을 받는다
				//List=>Array[]
				//VO => Object{no:1,name:'',sex:''}
							   
				axios.get("http://localhost/web/food/find_vue.do",{
					params:{
						page:this.curpage,
						fd:this.fd
					}
				}).then(response=>{
					console.log(response.data)
					this.food_list=response.data
					this.curpage=response.data[0].curpage;
					this.totalpage=response.data[0].totalpage;
					this.startpage=response.data[0].startpage;
					this.endpage=response.data[0].endpage;
				})
			},
			findData:function(){
				this.curpage=1
				this.send();
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
			selectpage:function(page){
				this.curpage=page;
				this.send();
			}
		}
		
	})
</script>
</body>
</html>