<!DOCTYPE html>
<html lang="en">
<head>
    <#assign base = request.contextPath>
    <base id="base" href="${base}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>用户登录</title>
    <!-- Bootstrap core CSS -->
    <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${base}/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-signin" action="${base}/admin/login" method="POST" >
    <img class="mb-4" src="" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">订餐后台管理</h1>
    <label class="sr-only">Username</label>
    <input type="text" class="form-control" name="username" placeholder="用户名" required="" autofocus="">
    <label class="sr-only"></label>
    <input type="password" class="form-control" name="password" placeholder="密码" required="">
<#--    <div class="checkbox mb-3">-->
<#--        <label>-->
<#--            <input type="checkbox" name="remember-me">记住我-->
<#--        </label>-->
<#--    </div>-->
    <div class="alert alert-danger mb-3" role="alert">
        请先登录！
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    <#if msg??>
        <p style="color: red" text="${msg}"></p>
    </#if>
    <p class="mt-5 mb-3 text-muted">© 2017-2019</p>
</form>

</body>

</html>