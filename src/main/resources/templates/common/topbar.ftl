<!DOCTYPE html>
<html lang="en">
<#assign base = request.contextPath>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" style="color: white">点餐系统后台管理</a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="${base}/admin/logout">注销</a>
        </li>
    </ul>
</nav>
</html>