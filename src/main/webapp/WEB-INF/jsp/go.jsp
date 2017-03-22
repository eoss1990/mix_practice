<%--
  Created by IntelliJ IDEA.
  User: yangyu
  Date: 16/10/27
  Time: 下午4:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="jsptag" uri="/myjsptag" %>
<html>
<head>
    <title>let'go</title>
</head>
<body>
<br>
${id}<br/>
${name}<br/>
年后<br/>
${msg}<br/>
${abc}<br/>
${high}<br/>
我是session里面的参数：
<%=session.getAttribute("id")%>

<form action="/test" method="post">
    <%--这样就可以使用jspTag了，注意需要引入taglib--%>
    <div><jsptag:mytag name="yangyu">nihao</jsptag:mytag></div>

    <input type="text" id="para" name="para"/>
    <button type="submit" value="提交"/>

</form>
</body>
</html>
