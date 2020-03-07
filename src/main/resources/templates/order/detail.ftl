<#assign base = request.contextPath>
<html>
<head>
    <base id="base" href="${base}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>订单详情</title>
    <!-- Bootstrap core CSS -->
    <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${base}/css/dashboard.css" rel="stylesheet">
    <link href="${base}/css/style.css" rel="stylesheet">
    <style type="text/css">
        /* Chart.js */

        @-webkit-keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }

        @keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }

        .chartjs-render-monitor {
            -webkit-animation: chartjs-render-animation 0.001s;
            animation: chartjs-render-animation 0.001s;
        }
    </style>
</head>

<body>
<div>
    <#include "../common/topbar.ftl">
</div>
<div class="container-fluid">
    <div class="row">
        <div> <#include "../common/sidebar.ftl"></div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="table-responsive col-6">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th>订单编号</th>
                        <th>订单价格</th>
                        <th>订单状态</th>
<#--                        <th>支付状态</th>-->
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.getOrderStatusEnum(orderDTO.orderStatus).getMsg()}</td>
<#--                        <td>${orderDTO.getPayStatusEnum(orderDTO.payStatus).getMsg()}</td>-->
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="table-responsive col-10">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th>商品ID</th>
                        <th>商品名</th>
                        <th>商品价格</th>
                        <th>商品数量</th>
                        <th>商品总额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTO.orderDetails as detail>
                        <tr>
                            <td>${detail.productId}</td>
                            <td>${detail.productName}</td>
                            <td>${detail.productPrice}</td>
                            <td>${detail.productQuantity}</td>
                            <td>${detail.productPrice * detail.productQuantity}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <div class="float-left">
                    <#if orderDTO.orderStatus == 1>
                        <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}&currentPage=${currentPage}"
                           class="btn btn-success"
                           role="button">完结订单</a>
                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}&currentPage=${currentPage}"
                           class="btn btn-danger"
                           role="button">取消订单</a>
                    </#if>
                    <a href="/sell/seller/order/list?page=${currentPage}" class="btn btn-secondary" role="button">返回</a>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="${base}/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="${base}/js/popper.min.js"></script>
<script type="text/javascript" src="${base}/js/bootstrap.min.js"></script>

</html>

