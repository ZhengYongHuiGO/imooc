<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../commonJsp/head.jsp"%>
    <%@include file="../commonJsp/tag.jsp"%>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <title>产品详情页</title>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3>正在秒杀</h3>
        </div>
        <div class="panel-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>剩余数量</th>
                    <th>剩余时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${killGoods.goodsName}
                    </td>
                    <td>
                        ${killGoods.totalNum}
                    </td>
                    <td>
                        <span class="glyphicon glyphicon-time"></span>
                        <span class="glyphicon" id="seckill-box"></span>
                    </td>
                    <td>
                        <button type="button" class="btn btn-primary" id="kill" disabled="disabled">我要秒杀</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                绑定手机
            </div>
            <div class="modal-body">
                <div class="form-group has-success has-feedback">
                    <span class="glyphicon glyphicon-phone">手机号</span>
                    <input type="text" class="form-control" id="phone" aria-describedby="inputSuccess4Status" placeholder="手机号">
                </div>
            </div>
            <div class="modal-footer">
                <span id="message" class="glyphicon"></span>
                <button type="button" id="modal-confirm" class="btn btn-primary">确认</button>
                <button type="button" id="modal-quit" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/2.2.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="${pageContext.request.contextPath}/js/seckill.js"></script>
<script type="text/javascript">
    $(function(){
      seckill.detail.init({
            goodsId:${killGoods.id},
            startTime:${killGoods.startTime.time},
            endTime:${killGoods.endTime.time}
        });
    });

</script>
</body>
</html>
