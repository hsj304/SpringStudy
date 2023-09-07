<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <div class="content"> 
      <div id="gallery">
        <figure>
          <header class="heading">총 <span style="color:green;font-size: 35px">{{count}}</span>개의 맛있는 레시피가 있습니다.</header>
          <ul class="nospace clear">
            <li v-for="vo,index in recipe_list" :class="index%4==0?'one_quarter first':'one_quarter'"><a href="#"><img :src="vo.poster" :title="vo.title"></a></li>
          </ul>
          <figcaption>Gallery Description Goes Here</figcaption>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startpage>1"><a href="#" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startpage,endpage)" :class="i==curpage?'current':''"><a href="#" @click="pageChange(i)">{{i}}</a></li>
          <li v-if="endpage<totalpage"><a href="#" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
    </div>
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<script>
	new Vue({
		el:'.container',
		data:{
			recipe_list:[],
			page_list:{},
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0,
			count:''
		}, 
		mounted:function(){
			this.dataRecive();
		},
		methods:{
			dataRecive:function(){
				//해당 페이지 데이터 읽기
				axios.get('http://localhost/web/recipe/recipe_list_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(res=>{
					console.log(res.data)
					this.recipe_list=res.data
				}).catch(error=>{
					console.log(error.response)
				})
				//페이지 정보
				axios.get('http://localhost/web/recipe/page_list_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(res=>{
					console.log(res.data)
					this.page_list=res.data
					this.curpage=this.page_list.curpage
					this.totalpage=this.page_list.totalpage
					this.startpage=this.page_list.startpage
					this.endpage=this.page_list.endpage
					this.count=this.page_list.count
				}).catch(error=>{
					console.log(error.res)
				})
				
			},
			range:function(start,end){
				let arr=[];
				let length=end-start;
				for(let i=0;i<=length;i++)
				{
					arr[i]=start
					start++;
				}
				return arr;
			},
			prev:function(){
				this.curpage=this.startpage-1;
				this.dataRecive();
			},
			next:function(){
				this.curpage=this.endpage+1;
				this.dataRecive();
			},
			pageChange:function(page){
				this.curpage=page;
				this.dataRecive();
			}
		}
	})
</script>
</body>
</html>