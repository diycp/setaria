<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Samples Hello JSP</title>
</head>
<body>

<a href="index.jsp">首页</a>
<hr/>
<h1>通过 Spring @ConfigValue 注解自动注入配置属性</h1>
<ul>
    <li>Fist: <span style="color: red;">${hello.first}</span>
    </li>
    <li>Second: <span style="color: red;">${hello.second}</span>
    </li>
</ul>

<hr/>

<h1>通过 Spring @Value 注解自动注入配置属性 (通过 @Value 注入配置不会自动刷新配置值)</h1>
<ul>
    <li>Fist: <span style="color: red;">${hello.first}</span>
    </li>
    <li>Second: <span style="color: red;">${hello.second}</span>
    </li>
</ul>

<hr/>

<h1>通过 Spring XML 自动注入配置属性 (通过 XML 注入的配置不会自动刷新配置值)</h1>
<ul>
    <li>Fist: <span style="color: red;">${hello.first}</span>
    </li>
    <li>Second: <span style="color: red;">${hello.second}</span>
    </li>
</ul>
</body>
</html>
