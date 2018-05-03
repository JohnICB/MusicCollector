<%--@elvariable id="errorMessage" type="java.lang.String"--%>
<%--<jsp:useBean id="errorMessage" scope="page" type="java.lang.String"/>--%>
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
	<p><span style="color: red; ">${errorMessage}</span></p>
	<form action="/login" method="POST">
        <label>
            Name :
            <input name="name" type="text"/>
        </label>
        <label>
            Password :
            <input name="password" type="password"/>
        </label> <input type="submit" />
	</form>
</body>
</html>