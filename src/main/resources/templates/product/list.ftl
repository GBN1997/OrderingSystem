<#assign base = request.contextPath>
<html>
<head>
    <base id="base" href="${base}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品列表</title>
    <!-- Bootstrap core CSS -->
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
                        <th>商品ID</th>
                        <th>名称</th>
                        <th>图片</th>
                        <th>单价</th>
                        <th>库存</th>
                        <th>介绍</th>
                        <th>类目</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th colspan="2" style="text-align: center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list productPage.content as product>
                        <tr>
                            <td>${product.productId}</td>
                            <td>${product.productName}</td>
                            <td><img src="${product.productIcon}" alt="暂无图片" class="rounded" width="75px" height="75px"></td>
                            <td>${product.productPrice}</td>
                            <td>${product.productStock}</td>
                            <td>${product.productDescription}</td>
                            <td>${product.categoryType}</td>
                            <td>${product.getProductStatusEnum(product.productStatus).getMsg()}</td>
                            <td>${product.createTime}</td>
                            <td>${product.updateTime}</td>
                            <td><a href="${base}/seller/product/index?productId=${product.productId}&currentPage=${currentPage}">修改</a></td>
                            <#if product.productStatus == 0>
                                <td><a href="${base}/seller/product/offsale?productId=${product.productId}&currentPage=${currentPage}">下架</a></td>
                            <#else>
                                <td><a href="${base}/seller/product/onsale?productId=${product.productId}&currentPage=${currentPage}">上架</a></td>
                            </#if>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <ul class="pagination float-right">
                    <#if currentPage lte 1>
                        <li class="disabled page-item"><a class="page-link" href="#">&laquo;</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link"
                                                 href="${base}/seller/product/list?page=${currentPage - 1}">&laquo;</a>
                        </li>
                    </#if>
                    <!--总页数 > 可见页-->
                    <#if productPage.getTotalPages() gt 7>
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
                                    <li class="page-item"><a class="page-link"
                                                             href="${base}/seller/product/list?page=${index}">${index}</a>
                                    </li>
                                </#if>
                            </#list>
                            <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                            <li class="page-item">
                                <a class="page-link"
                                   href="${base}/seller/product/list?page=${productPage.getTotalPages()}">${productPage.getTotalPages()}</a>
                            </li>
                        <#elseif currentPage gte (7 - 2)>
                            <!--可以接到结束-->
                            <#if productPage.getTotalPages() - currentPage lte 2>
                                <li class="page-item"><a class="page-link"
                                                         href="${base}/seller/product/list?page=1">1</a></li>
                                <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                                <#list productPage.getTotalPages() - 4..productPage.getTotalPages() as index>
                                    <#if currentPage == index>
                                        <li class="page-item active" aria-current="page">
                                        <span class="page-link">${index}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                        </li>
                                    <#else>
                                        <li class="page-item"><a class="page-link"
                                                                 href="${base}/seller/product/list?page=${index}">${index}</a>
                                        </li>
                                    </#if>
                                </#list>
                                <!--接不到结束-->
                            <#else>
                                <li class="page-item"><a class="page-link"
                                                         href="${base}/seller/product/list?page=1">1</a></li>
                                <li class="page-item disabled"><a class="page-link" href="">...</a></li>
                                <#list currentPage - 1..currentPage + 1 as index>
                                    <#if currentPage == index>
                                        <li class="page-item active" aria-current="page">
                                        <span class="page-link">${index}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                        </li>
                                    <#else>
                                        <li class="page-item"><a class="page-link"
                                                                 href="${base}/seller/product/list?page=${index}">${index}</a>
                                        </li>
                                    </#if>
                                </#list>
                                <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${base}/seller/product/list?page=${productDTOPage.getTotalPages()}">${productDTOPage.getTotalPages()}</a>
                                </li>
                            </#if>
                        </#if>
                        <!--总页数 <= 可见页-->
                    <#else>
                        <#list 1..productPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="page-item active" aria-current="page">
                                        <span class="page-link">${index}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                </li>
                            <#else>
                                <li class="page-item"><a class="page-link"
                                                         href="${base}/seller/product/list?page=${index}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                    </#if>
                    <#if currentPage gte productPage.getTotalPages()>
                        <li class="page-item"><a class="page-link">&raquo;</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link"
                                                 href="${base}/seller/product/list?page=${currentPage + 1}">&raquo;</a>
                        </li>
                    </#if>
                </ul>
            </div>
            <div class="float-left">
                <a href="${base}/seller/product/index" class="btn btn-info" role="button">新增商品</a>
            </div>
        </main>
    </div>
</div>
</body>

</html>