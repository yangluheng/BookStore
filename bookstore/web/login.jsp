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

	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
			<table width="900px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px"><div style="height:470px">
							<b>&nbsp;&nbsp;首页&nbsp;&raquo;&nbsp;个人用户登录</b>
							<div>
								<table width="85%" border="0" cellspacing="0">
									<tr>
										<td>
											<div id="logindiv">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td style="text-align:center; padding-top:20px"><img
															src="images/logintitle.gif" width="150" height="30" />
														</td>
													</tr>
													<tr>
														<td style="text-align:center;padding-top:20px;"><font
															color="#ff0000">${requestScope["login_msg"]}</font>
														</td>
													</tr>
													<tr>
														<td style="text-align:center">
															<table width="80%" border="0" cellspacing="0"
																style="margin-top:15px ;margin-left:auto; margin-right:auto">
																<tr>
																	<tr>
																<td>

																</td>
																<td>

																</td>
															</tr>
																	<td
																		style="text-align:right; padding-top:5px; width:25%">用户名：</td>
																	<td style="text-align:left"><input name="username"
																		type="text" class="textinput" />
																		<font color="red">${user_err}</font>
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right; padding-top:5px">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
																	<td style="text-align:left"><input name="password"
																		type="password" class="textinput" />
																		<font color="red">${password_err}</font>
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right; width:20%">输入校验码：</td>
																	<td style="width:50%">
																		<input type="text" class="textinput" name="checkcode"/>
																		<font color="red">${checkcode_err}</font>
																	</td>
																	<td>&nbsp;</td>
																</tr>
																<tr>
																	<td style="text-align:right;width:20%;">&nbsp;</td>
																	<td colspan="2" style="text-align:center"><a href="${pageContext.request.contextPath}/CheckLoginServlet">自动登陆 </a><a href="${pageContext.request.contextPath}/ClearCookiesServlet">用戶退出，不保留登陆信息</a>
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right;width:20%;">&nbsp;</td>
																	<td colspan="2" style="width:50%"><img
																			src="${pageContext.request.contextPath}/imageCode" width="180"
																			height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
																		<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
																	</td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top:10px; text-align:center"><input
																		name="image" type="image" onclick="return formcheck()"
																		src="images/loginbutton.gif" width="83" height="22" />
																	</td>
																</tr>

																<tr>
																	<td colspan="2" style="padding-top:10px"><img
																		src="images/loginline.gif" width="241" height="10" />
																	</td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top:10px; text-align:center"><a
																		href="register.jsp"><img name="image"
																			src="images/signupbutton.gif" width="135" height="33" />
																	</a></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div></td>
										<td style="text-align:left; padding-top:30px; width:60%"><h1>您还没有注册？</h1>
											<p>注册新会员，享受更优惠价格!</p>
											<p>千种图书，供你挑选！注册即享受丰富折扣和优惠，便宜有好货！超过万本图书任您选。</p>
											<p>超人气社区！精彩活动每一天。买卖更安心！支付宝交易超安全。</p>
											<p style="text-align:right">
												<a href="register.jsp"><img
													src="images/signupbutton.gif" width="135" height="33" /> </a>
											</p>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
