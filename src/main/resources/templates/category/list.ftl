<#assign base = request.contextPath>
<html>
<head>
    <base id="base" href="${base}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>类目列表</title>
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
                        <th>类目名</th>
                        <th>类目号码</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list categoryPage.content as category>
                        <tr>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${category.createTime}</td>
                            <td>${category.updateTime}</td>
                            <td><a href="${base}/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
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
                    <#if categoryPage.getTotalPages() gt 7>
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
                                   href="${base}/seller/product/list?page=${categoryPage.getTotalPages()}">${categoryPage.getTotalPages()}</a>
                            </li>
                        <#elseif currentPage gte (7 - 2)>
                            <!--可以接到结束-->
                            <#if categoryPage.getTotalPages() - currentPage lte 2>
                                <li class="page-item"><a class="page-link"
                                                         href="${base}/seller/product/list?page=1">1</a></li>
                                <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                                <#list categoryPage.getTotalPages() - 4..categoryPage.getTotalPages() as index>
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
                        <#list 1..categoryPage.getTotalPages() as index>
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
                    <#if currentPage gte categoryPage.getTotalPages()>
                        <li class="page-item"><a class="page-link">&raquo;</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link"
                                                 href="${base}/seller/product/list?page=${currentPage + 1}">&raquo;</a>
                        </li>
                    </#if>
                </ul>
            </div>
            <div class="float-left">
                <a href="${base}/seller/category/index" class="btn btn-info" role="button">新增类目</a>
            </div>
        </main>
    </div>
</div>
</body>

</html>