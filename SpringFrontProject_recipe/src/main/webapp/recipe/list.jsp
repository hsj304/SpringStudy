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
			<div class="col-md-3" v-for="vo in recipe_list">
		      <div class="thumbnail">
		        <a href="#">
		          <img :src="vo.poster" alt="Nature" :title="vo.title" style="width:100%">
		          <div class="caption">
		            <p style="font-size: 9px;">{{vo.chef}}</p>
		          </div>
		        </a>
		      </div>
		    </div>
		</div>
		<div style="height: 10px"></div>
		<div class=row>
			<div class="text-center">
			    <ul class="pagination">
			        <li v-if="startpage > 1"><a href="#" @click.prevent="prev()">&lt;</a></li>
			        <li v-for="i in range(startpage, endpage)" :class="i == curpage ? 'active' : ''">
			            <a href="#" @click.prevent="changePage(i)">{{ i }}</a>
			        </li>
			        <li v-if="endpage < totalpage"><a href="#" @click.prevent="next()">&gt;</a></li>
			    </ul>
			</div>
		</div>
	</div>
<script>
	new Vue({
		el:'.container-fluid',
		data:{
			recipe_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0
		},
		mounted:function(){
			this.send()
		},
		methods:{
			send:function(){
				axios.get('http://localhost/web/recipe/list_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.recipe_list=response.data
					this.curpage=response.data[0].curpage
					this.totalpage=response.data[0].totalpage
					this.startpage=response.data[0].startpage
					this.endpage=response.data[0].endpage
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
				this.send()
			},
			next:function(){
				this.curpage=this.endpage+1
				this.send()
			},
			changePage:function(page){
				this.curpage=page
				this.send()
			}
		}
	})
</script>
</body>
</html>