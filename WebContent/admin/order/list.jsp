<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/adminProduct_addPage.action";
			}
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										订单编号
									</td>
									<td align="center" width="17%">
										总金额
									</td>
									<td align="center" width="17%">
										收货人
									</td>
									<td align="center" width="17%">
										订单状态
									</td>
									<td width="7%" align="center">
										订单详情
									</td>
								</tr>
								<s:iterator var="order" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="#order.oid"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="#order.total"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="#order.name"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:if test="#order.state == 1">
													未付款
												</s:if>
												<s:if test="#order.state == 2">
													<a href="#"><font color="blue">发货</font></a>
												</s:if>
												<s:if test="#order.state == 3">
													已发货，未确认收货
												</s:if>
												<s:if test="#order.state == 4">
													完成交易
												</s:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<input type="button" value="订单详情">
											</td>
									
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
					<tr align="center">
						<td class="ta_01" align="center" bgColor="#afd1f3">
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="pageBean.page != 1">
								<a href="${pageContext.request.contextPath }/adminOrder_findAll.action?page=1">首页</a>|
								<a href="${pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if>
							<s:if test="pageBean.page != pageBean.totalPage">
								<a href="${pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a href="${pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
							</s:if>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

