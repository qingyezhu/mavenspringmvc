<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	System.out.println(context);
%>
<html>
<head>
<meta charset="utf-8" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function addTable(){
		createTable1(5000);
		//createTable2(5000);
		//createTable3(5000);
		//createTable4(5000);
		//createTable5(5000);
	}
	
	function createTable1(rows){
		var div = document.getElementById("div1Id");
		var child = div.childNodes;
		for(var i = 0,len = child.length;i < len;i ++){
			div.removeChild(child[i]);
		}
		var start = new Date().getTime();
		var str = "<table>";
		str += "<tr>";
		for(var i = 0;i < 3;i ++){
			str += "<th>列" + i + "</th>";
		}
		str += "</tr>";
		for(var  i = 0;i < rows;i ++){
			str += "<tr>"
			for(var j = 0;j < 3;j ++){
				str += "<td>" + j + "</td>";
			}			
			str += "</tr>";
		}
		str += "</table>";
		//div.innerHTML = str;
		//$(div).append(str);
		$(div).html(str);
		var end = new Date().getTime();
		console.log("createTable1: " + (end - start));
	}
	
	function createTable2(rows){
		var div = document.getElementById("div2Id");
		var child = div.childNodes;
		for(var i = 0,len = child.length;i < len;i ++){
			div.removeChild(child[i]);
		}
		var start = new Date().getTime();
		var str = "<table>";
		str = str + "<tr>";
		for(var i = 0;i < 3;i ++){
			str = str + "<th>列" + i + "</th>";
		}
		str =str + "</tr>";
		for(var  i = 0;i < rows;i ++){
			str = str + "<tr>"
			for(var j = 0;j < 3;j ++){
				str = str + "<td>" + j + "</td>";
			}			
			str = str + "</tr>";
		}
		str = str + "</table>";
		//div.innerHTML = str;
		$(div).append(str);
		var end = new Date().getTime();
		alert("createTable2: " + (end - start));
	}
	
	function createTable3(rows){
		var div = document.getElementById("div3Id");
		var child = div.childNodes;
		for(var i = 0,len = child.length;i < len;i ++){
			div.removeChild(child[i]);
		}
		var start = new Date().getTime();
		var str = [];
		str.push("<table>" , "<tr>");
		for(var i = 0;i < 3;i ++){
			str.push("<th>列" + i + "</th>");
		}
		str.push("</tr>");
		for(var  i = 0;i < rows;i ++){
			str.push("<tr>");
			for(var j = 0;j < 3;j ++){
				str.push("<td>" + j + "</td>");
			}			
			str.push("</tr>");
		}
		str.push("</table>");
		div.innerHTML = str.join("");
		var end = new Date().getTime();
		alert("createTable3: " + (end - start));
	}
	
	function createTable4(rows){
		var div = document.getElementById("div4Id");
		var child = div.childNodes;
		for(var i = 0,len = child.length;i < len;i ++){
			div.removeChild(child[i]);
		}
		var start = new Date().getTime();
		var _table = document.createElement('table');
		var _row = document.createElement('tr');
		_table.appendChild(_row);
		for(var i = 0;i < 3;i ++){
			var _cell = document.createElement('td');
			_cell.innerHTML = 'col' + i;
			_row.appendChild(_cell);
		}
		for(var i = 0;i < rows;i ++){
			var _row = document.createElement('tr');
			_table.appendChild(_row);
			for(var j = 0;j < 3;j ++){
				var _cell = document.createElement('td');
				_cell.innerHTML = i + '/' + j;
				_row.appendChild(_cell);
			}
		}
		div.appendChild(_table);
		var end = new Date().getTime();
		alert("createTable4: " + (end - start));
	}
	
	function createTable5(rows){
		var div = document.getElementById("div4Id");
		var child = div.childNodes;
		for(var i = 0,len = child.length;i < len;i ++){
			div.removeChild(child[i]);
		}
		var start = new Date().getTime();
		var _table = document.createElement('table');
		var _row = document.createElement('tr');
		var _tableFrag = document.createDocumentFragment();
		var _rowFrag = document.createDocumentFragment();
		_tableFrag.appendChild(_row);
		for(var i = 0;i < 3;i ++){
			var _cell = document.createElement('td');
			_cell.innerHTML = 'col' + i;
			_rowFrag.appendChild(_cell);
		}
		_row.appendChild(_rowFrag);
		
		for(var i = 0;i < rows;i ++){
			var _row = document.createElement('tr');
			_tableFrag.appendChild(_row);
			var _rowFrag = document.createDocumentFragment();
			for(var j = 0;j < 3;j ++){
				var _cell = document.createElement('td');
				_cell.innerHTML = i + '/' + j;
				_rowFrag.appendChild(_cell);
			}
			_row.appendChild(_rowFrag);
		}
		_table.appendChild(_tableFrag);
		div.appendChild(_table);
		var end = new Date().getTime();
		alert("createTable5: " + (end - start));
	}
</script>
</head>
<body>
	<h2>Hello World!</h2>
	<form action="testHello.do" method="post">
		名称：<input type="text" name="username"/>
		<input type="submit" value="提交">
	</form>
	<a href="redirect.do">redirect重定向</a>

	<form action="addStudent.do" method="post">
		<div>
			<label>name:</label> <input type="text" name="name">
		</div>
		<div>
			<label>age:</label> <input type="text" name="age">
		</div>
		<div>
			<label>id:</label> <input type="text" name="id">
		</div>
		<input type="submit" value="add">
		<input type="button" value="添加" onclick="addTable()">
	</form>
	<div id="div1Id">
	</div>
	<div id="div2Id">
	</div>
	<div id="div3Id">
	</div>
	<div id="div4Id">
	</div>
	<div>
		<font color="red"><%=new Date()%></font>
	</div>
	<div>
	<span>监听域对象中的属性的增加和删除的事件监听器</span>
<a href="init.jsp">Init</a>
<a href="destroy.jsp">Destroy</a>
	</div>
	</body>
</html>
