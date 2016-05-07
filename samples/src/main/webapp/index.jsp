<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.weghst.setaria.client.Configs" %>
<html>
<head>
    <title>Samples Index JSP</title>
</head>
<body>
<h1>通过 API 获取配置属性值</h1> -- <h2><a href="${pageContext.request.contextPath}/hello.htm">Spring 注解配置</a></h2>
<ul>
    <li>Fist: <span style="color: red;"><%=Configs.getString("samples.first")%></span>
    </li>
    <li>Second: <span style="color: red;"><%=Configs.getInt("samples.second")%></span>
    </li>
</ul>
</body>
</html>
