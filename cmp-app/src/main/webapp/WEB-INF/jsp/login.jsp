<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
</head>
<body>
<form action="/acl-web/login" method="POST">
	<table>
		<tr>
			<td>机构号：</td>
			<td><input id="orgId" name="orgId" /></td>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><input id="loginName" name="loginName" /></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input id="password" name="password" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="登录" /></td>
		</tr>
	</table>
</form>

</body>
</html>