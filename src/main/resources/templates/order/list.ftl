<#assign base = request.contextPath>
<html>
<head>
    <base id="base" href="${base}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>订单列表</title>
    <!-- Bootstrap core CSS -->
    <script src="${base}/js/jquery-3.4.1.js"></script>
    <script src="${base}/js/bootstrap.min.js"></script>
    <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${base}/css/dashboard.css" rel="stylesheet">
    <link href="${base}/css/style.css" rel="stylesheet">

</head>

<body>
<div>
    <#include "../common/topbar.ftl">
</div>

<div class="container-fluid">
    <div class="row">
        <div> <#include "../common/sidebar.ftl"></div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th>订单号</th>
                        <th>微信名</th>
                        <th>手机号</th>
                        <th>桌号/地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
<#--                        <th>支付状态</th>-->
                        <th>下单时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTOPage.content as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.getOrderStatusEnum(orderDTO.orderStatus).getMsg()}</td>
<#--                            <td>${orderDTO.getPayStatusEnum(orderDTO.payStatus).getMsg()}</td>-->
                            <td>${orderDTO.createTime}</td>
                            <td><a href="${base}/seller/order/detail?orderId=${orderDTO.orderId}&currentPage=${currentPage}">详情</a></td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <ul class="pagination float-right">
                    <#if currentPage lte 1>
                        <li class="disabled page-item"><a class="page-link" href="#">&laquo;</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=${currentPage - 1}">&laquo;</a></li>
                    </#if>
                    <!--总页数 > 可见页-->
                    <#if orderDTOPage.getTotalPages() gt 7>
                        <!-- 当前页 < 可见页 - 2 -->
                        <#if currentPage lt (7 - 2)>
                            <#list 1..(7 - 2) as index>
                                <#if currentPage == index>
                                    <li class="page-item active" aria-current="page">
                                        <span class="page-link">${index}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                    </li>
                                <#else>
                                    <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=${index}">${index}</a></li>
                                </#if>
                            </#list>
                            <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                            <li class="page-item">
                                <a class="page-link" href="${base}/seller/order/list?page=${orderDTOPage.getTotalPages()}">${orderDTOPage.getTotalPages()}</a>
                            </li>
                        <#elseif currentPage gte (7 - 2)>
                            <!--可以接到结束-->
                            <#if orderDTOPage.getTotalPages() - currentPage lte 2>
                                <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=1">1</a></li>
                                <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                                <#list orderDTOPage.getTotalPages() - 4..orderDTOPage.getTotalPages() as index>
                                    <#if currentPage == index>
                                        <li class="page-item active" aria-current="page">
                                        <span class="page-link">${index}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                        </li>
                                    <#else>
                                        <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=${index}">${index}</a></li>
                                    </#if>
                                </#list>
                                <!--接不到结束-->
                            <#else>
                                <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=1">1</a></li>
                                <li class="page-item disabled"><a class="page-link" href="">...</a></li>
                                <#list currentPage - 1..currentPage + 1 as index>
                                    <#if currentPage == index>
                                        <li class="page-item active" aria-current="page">
                                        <span class="page-link">${index}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                        </li>
                                    <#else>
                                        <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=${index}">${index}</a></li>
                                    </#if>
                                </#list>
                                <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="${base}/seller/order/list?page=${orderDTOPage.getTotalPages()}">${orderDTOPage.getTotalPages()}</a>
                                </li>
                            </#if>
                        </#if>
                        <!--总页数 <= 可见页-->
                    <#else>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="page-item active" aria-current="page">
                                        <span class="page-link">${index}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                </li>
                            <#else>
                                <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=${index}">${index}</a></li>
                            </#if>
                        </#list>
                    </#if>
                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="page-item"><a class="page-link">&raquo;</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="${base}/seller/order/list?page=${currentPage + 1}">&raquo;</a></li>
                    </#if>
                </ul>
            </div>
        </main>
    </div>
</div>

<div class="modal" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">消息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>你有新订单了！</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    var websocket = null;
    if('websocket' in window){
        websocket = new WebSocket("ws://localhost:8080/sell/webSocket");
    }else{
        alert('不支持')
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('关闭连接')
    }
    
    websocket.onmessage = function (event) {
        console.log('收到信息' + event.data);
        $('#myModal').modal('show');
    }

    websocket.onerror = function (event) {
        alert("websocket通信发生错误！")
    }

    window.onbeforeunload = function () {
        websocket.close();
    }
</script>
</html>