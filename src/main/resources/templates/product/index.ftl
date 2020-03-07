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
            <form class="col-6" method="post"
                  action="${base}/seller/product/save<#if currentPage??>?currentPage=${currentPage}">
                <#else>
                    ">
                </#if>
                <div class="form-group">
                    <label>名称</label>
                    <input type="text" required class="form-control" name="productName"
                           value="${(product.productName)!''}">
                </div>
                <div class="form-group">
                    <label>价格</label>
                    <input type="number" required step="0.01" class="form-control" name="productPrice"
                           value="${(product.productPrice)!''}">
                </div>
                <div class="form-group">
                    <label>库存</label>
                    <input type="number" required class="form-control" name="productStock"
                           value="${(product.productStock)!''}">
                </div>
                <div class="form-group">
                    <label>介绍</label>
                    <input type="text" class="form-control" name="productDescription"
                           value="${(product.productDescription)!''}">
                </div>
                <div class="form-group">
                    <label>图片</label>
                    <input type="text" class="form-control" name="productIcon" value="${(product.productIcon)!''}">
                </div>
                <div class="form-group">
                    <label>类目</label>
                    <select name="categoryType" class="form-control">
                        <#list category as category>
                            <option value="${category.categoryType}"
                                    <#if (product.categoryType)?? && product.categoryType == category.categoryType>
                                    selected>
                                <#else>
                                    >
                                </#if>
                                ${category.categoryName}
                            </option>
                        </#list>
                    </select>
                </div>
                <input hidden type="text" name="productId" value="${(product.productId)!''}">
                <button type="submit" class="btn btn-primary">保存</button>
            </form>
        </main>
    </div>
</div>
</body>

</html>