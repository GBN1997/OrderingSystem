<html>
<head>
    <#assign base = request.contextPath>
    <meta charset="UTF-8">
    <title>错误！</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
<#--                <h4>错误!</h4><br>-->
                <strong>${msg}</strong><br>
                <a href="${url}" class="alert-link">点击跳转</a><br>
                <p id="time">3</p>秒后自动跳转
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    function countDown() {
        var time = document.getElementById("time");
        //alert(time.innerHTML);
        //获取到id为time标签中的内容，现进行判断
        if (time.innerHTML == 0) {
            //等于0时清除计时
            window.location.href = "${url}";
            rel = "external nofollow";
        } else {
            time.innerHTML = time.innerHTML - 1;
        }
    }

    //1000毫秒调用一次
    window.setInterval("countDown()", 1000);
</script>