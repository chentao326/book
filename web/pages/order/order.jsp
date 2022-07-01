<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%--    静态包含base标签，css样式，jquery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">

    <span class="wel_word">我的订单</span>
    <%--		静态包含manager管理模块的菜单--%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <c:forEach items="${sessionScope.order.items}" var="entry">
            <tr>
                <td>${entry.name}</td>
                <td>${entry.createTime}</td>
                <td>${entry.price}</td>
                <c:if test="${entry.status==0}">
                    <td>未发货</td>
                </c:if>
                <c:if test="${entry.status==1}">
                    <td>已发货</td>
                </c:if>
                <c:if test="${entry.status==2}">
                    <td>已签收</td>
                </c:if>

                <td><a href="#">查看详情</a></td>
            </tr>
        </c:forEach>

    </table>


</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>