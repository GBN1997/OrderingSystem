<#assign base = request.contextPath>
<html>
<head>
    <base id="base" href="${base}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>类目修改</title>
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
            <form CLASS="col-6" method="post" action="${base}/seller/category/save">
                <input type="hidden" name="categoryId" value="${(category.categoryId)!''}">
                <div class="form-group">
                    <label>类目名称</label>
                    <input type="text" required class="form-control" name="categoryName"
                           value="${(category.categoryName)!''}">
                </div>
                <div class="form-group">
                    <label>类目编号</label>
                    <input type="text" required class="form-control" name="categoryType"
                           value="${(category.categoryType)!''}">
                </div>
                <button type="submit" class="btn btn-primary">保存</button>
            </form>
        </main>
    </div>
</div>
</body>
</html>