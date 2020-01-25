<%@ page import ="java.util.ArrayList" %>
<%@ page import ="com.google.gson.Gson" %>
<%@ page import ="model.Film" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");

Gson gson = new Gson();
String jsonResult;
		
jsonResult = gson.toJson(films);
out.println(jsonResult);
%>