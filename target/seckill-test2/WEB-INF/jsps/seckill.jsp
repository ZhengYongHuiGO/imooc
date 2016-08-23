<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../commonJsp/head.jsp"%>
    <%@include file="../commonJsp/tag.jsp"%>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">

    <title>秒杀产品页</title>


</head>
<body>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>秒杀商品列表</h3>
            </div>
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>商品价格</th>
                    <th>剩余数量</th>
                    <th>秒杀开始时间</th>
                    <th>秒杀结束时间</th>
                    <th>秒杀操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${killGoodses}" var="me">
                    <tr>
                        <th>${me.goodsName}</th>
                        <td>${me.price}</td>
                        <td>${me.totalNum}</td>
                        <td>
                            <fmt:formatDate value="${me.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            <fmt:formatDate value="${me.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            <a href="/seckill/killGoods/${me.id}" class="btn btn-default" id="kill">进入秒杀</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/2.2.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</body>
</html>