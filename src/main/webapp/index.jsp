<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-9-5
  Time: 下午 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <form action="/test/jdbcOnly" method="post">
        <button type="submit">点我执行JDBC不带缓存</button>
    </form>
</div>

<div>
    <form action="/test/jdbcRedis" method="post">
        <button type="submit">点我执行JDBC带redis缓存分拆模型</button>
    </form>
</div>
</body>
</html>
