<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.Products"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Products</h1>
	<table border="1">
		<tr>
			<td>Ma SP</td>
			<td>Ten SP</td>
			<td>Gia</td>
		</tr>
		<%
			Products listSP = new Products();
			List<Product> list = listSP.showProduct("");
			for (Product sp : list) {
				out.print("<form action=\"ControllerCartBean\">");
				out.print("<tr><td>" + sp.getCode() + "</td><td>" + sp.getName() + "</td>" + "<td>" + sp.getPrice()
						+ "</td><td><input type=\"submit\" " + "value=\"Add to Cart\" name=\"action\" /></td>"
						+ "<input type=\"hidden\" name=\"txtCode\" value='" + sp.getCode() + "'>"
						+ "<input type=\"hidden\" name=\"txtName\" value='" + sp.getName() + "'>"
						+ "<input type=\"hidden\" name=\"txtPrice\" value='" + sp.getPrice() + "'></tr>");
				out.print("</form>");
			}
		%>
	</table>
	<form action="ControllerCartBean">
		<input type="submit" value="View Cart" name="action" />
	</form>
</body>
</html>