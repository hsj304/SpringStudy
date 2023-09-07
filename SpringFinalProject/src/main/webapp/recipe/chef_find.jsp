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
    <table class="table">
    	<tr>
    		<td width=30% class="text-center" rowspan="2">
    		<img :src="chef_info.poster" style="width:90px;height: 90px;" class="img-circle">
    		</td>
    		<td width=70% colspan="4"><h4 style="color:orange">{{chef_info.chef}}</h4></td>
    	</tr>
    	<tr>
    		<td class="text-center">
    			<img src="../recipe/images/mem1.png">&nbsp;{{chef_info.mem_cont1}}
    		</td>
    		<td class="text-center">
    			<img src="../recipe/images/mem2.png">&nbsp;{{chef_info.mem_cont2}}
    		</td>
    		<td class="text-center">
				<img src="../recipe/images/mem7.png">&nbsp;{{chef_info.mem_cont4}}    		
    		</td>
    		<td class="text-center">
    			<img src="../recipe/images/mem3.png">&nbsp;{{chef_info.mem_cont3}}
    		</td>
    	</tr>
    </table>
    <%--
    	1. 가상돔 : 개발자가 소스를 올리는 공간 => diff(실제 돔과 바교해서 => 변경)
    		Vue (프레임워크: 변경이 가능) / React (라이브러리:사용) **
    	2. template : 컴포넌트 (기능) => 화면에 출력할 요소(태그)를 작성
    		Vue.component('태그명',template:'<html>....' => 형식(XML) => 루트
    					<template></template>
    					=> porps:[''] => 부모가 전달한 데이터를 받기 위해 만든다
    	3. 출력시 <태그>{{}}</태그>
    			  속성 <a href="">
    	4. 부모의 데이터를 읽을 때
    		Vue.component() => Child
    			=> 메소드, 변수를 찾는 경우
    			=> this.$parent.변수, 메소드
    		new Vue({}) => Parent
    	5. v-model => 데이터 양방향 => 입력 => 자동으로 변수에 값을 채워준다
    	6. v-for, v-if, v-show, v-bind
    	7. v-html v-text
    	8. 이벤트 처리
    		@click, @keydown.....
    	9. split, reverse, join
    	10. router..: vue를 단독으로 사용 => react
    	11. axios => 서버와 연결 (요청,응답)
     --%>
    <div class="content"> 
      <div id="gallery">
        <figure>
          <header class="heading inline">
          	<input type=text size=20 ref="fd" class="input-sm" v-model="fd">
          	<input type=button value="검색" class="btn btn-sm btn-danger" @click="find()">
          </header>
          <ul class="nospace clear" v-if="count==0">
            <li>검색된 결과가 없습니다</li>
          </ul>
          <ul class="nospace clear" v-else>
            <li v-for="vo,index in recipe_list" :class="index%4==0?'one_quarter first':'one_quarter'"><a href="#"><img :src="vo.poster" :title="vo.title+'('+vo.chef+')'"></a></li>
          </ul>
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
		  el: '.container',
		  data: {
		    chef_info: {},
		    recipe_list: [],
		    page_info: {},
		    curpage: 1,
		    totalpage: 0,
		    startpage: 0,
		    endpage: 0,
		    count: '',
		    chef: '${chef}', 
		    fd: 'all'
		  },
		  mounted: function () {
		    axios.get('http://localhost/web/recipe/chef_info_vue.do', {
		      params: {
		        chef: this.chef
		      }
		    }).then(res => {
		      console.log(res.data);
		      this.chef_info = res.data;
		    });

		    this.dataRecive();
		  },
		  methods: {
		    dataRecive: function () {
		      axios.post('http://localhost/web/recipe/chef_find_vue.do', null, {
		        params: {
		          page: this.curpage,
		          fd: this.fd,
		          chef: this.chef
		        }
		      }).then(res => {
		        console.log(res.data);
		        this.recipe_list = res.data;
		      }).catch(error => {
		        console.log(error.res);
		      });

		      axios.get('http://localhost/web/recipe/page_info_vue.do', {
		        params: {
		          page: this.curpage,
		          fd: this.fd,
		          chef: this.chef
		        }
		      }).then(res => {
		        console.log(res.data);
		        this.page_info = res.data;
		        this.curpage = this.page_info.curpage;
		        this.totalpage = this.page_info.totalpage;
		        this.startpage = this.page_info.startpage;
		        this.endpage = this.page_info.endpage;
		        this.count = Number(this.page_info.count);
		      });
		    },
		    range: function (start, end) {
		      let arr = [];
		      let leng = end - start;
		      for (let i = 0; i <= leng; i++) {
		        arr[i] = start;
		        start++;
		      }
		      return arr;
		    },
		    find: function () {
		      this.curpage = 1;
		      this.dataRecive();
		    },
		    prev: function () {
		      this.curpage = this.startpage - 1;
		      this.dataRecive();
		    },
		    next: function () {
		      this.curpage = this.endpage + 1;
		      this.dataRecive();
		    },
		    pageChange: function (page) {
		      this.curpage = page;
		      this.dataRecive();
		    }
		  }
		});
	</script>
</body>
</html>