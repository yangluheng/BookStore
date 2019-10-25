<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />



	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="product_list.html">&nbsp;${product.name}</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${product.category}
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="ad/page_ad.jpg" width="645" height="84" />

								<table width="100%%" border="0" cellspacing="0">
									<tr>
										<td width="30%">

											<div class="divbookcover">
												<p>
													<img src="bookcover/101.jpg"
														width="213" height="269" border="0" />
												</p>
											</div>

											<div style="text-align:center; margin-top:25px">
												<a href="${pageContext.request.contextPath}/AddCartServlet?id=${product.id}&count=${count}">
													<img src="images/buybutton.gif" border="0" /> 
												</a>
											</div></td>
										<td style="padding:20px 5px 5px 5px"><img
											src="images/miniicon3.gif" width="16" height="13" /><font
											class="bookname">&nbsp;&nbsp;${product.name}</font>

											<hr />售价：<font color="#FF0000">￥${product.price}</font>
											<hr /> 类别：${product.category}
											<hr />
											<p>
												<b>内容简介：</b>
											</p> ${product.description}</td>
										<table cellspacing="1" class="carttable">
											<tr>
												<td width="20%">数量</td>
												<td width="10%">库存</td>
												<td width="10%">小计</td>
												<td width="10%">取消</td>
											</tr>
										</table>
										<table width="100%	" border="0" cellspacing="0">
												<tr>
													<td width="20%">
														<input type="button" value='-'
															   style="width:20px" onclick="changeNum(${product.id},${count-1},${product.pnum})">

														<input name="text" type="text"  value="${count}"
															   style="width:40px;text-align:center" /> <input
															type="button" value='+' style="width:20px" onclick="changeNum(${product.id},${count+1},${product.pnum})">
														<script type="text/javascript">
															/**
															 *
															 * @param id  产品id
															 * @param num  更改后的数量
															 * @param snum  库存
															 */
															function changeNum(id,count,snum) {
																//1.购买的数量不能大于库存
																if (count>snum){
																	alert("购买的数量不能大于库存");
																	return;
																}
																//2.如果购买的数量为0，给个提示
																if (count==0){
																	var flag=confirm("您确定不够买这件商品？");
																	if (!flag){
																		return;
																	}
																}
																location.href='${pageContext.request.contextPath}/ChangeProductNumServlet?id='+id+'&count='+count;
                                                                <%--location.href='${pageContext.request.contextPath}/product_info.jsp';--%>

																alert("page");
															}
														</script>
													</td>
													<td width="10%">${product.pnum}</td>
													<td width="10%">${product.price*product.pnum}</td>
													<td width="10%"><a href="#"
																	   style="color:#FF0000; font-weight:bold">X</a></td>
												</tr>
										</table>
										</td>
									</tr>
								</table>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>


	<jsp:include page="foot.jsp" />


</body>
</html>
