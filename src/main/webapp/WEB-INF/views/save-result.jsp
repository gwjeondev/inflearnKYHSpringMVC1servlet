<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>성공</h1>
<ul>
    <!-- jsp 표현식 property 접근법, controller에서 request.setAttribute("member", 객체)로 넣어주면 다음과 같이 쓸 수 있다. -->
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
