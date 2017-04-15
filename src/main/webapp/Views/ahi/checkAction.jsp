<%@ page contentType="text/html; charset=utf-8" language="java" 

<%
String actions = request.getParameter("actions");
System.out.println("-----ahi action:"+actions);
String plateNumber = request.getParameter("plateNumber");
String strOpenId = request.getParameter("strOpenId");
String ratio = request.getParameter("ratio");
String id = request.getParameter("id");
if(actions != null && actions.equals("queryAllPointByPlateNumber")) {
	AHIService AHIService = new AHIService();
	out.print(AHIService.queryAllPointByPlateNumber(plateNumber));
}else if(actions != null && actions.equals("queryCarPointOne")) {
	AHIService AHIService = new AHIService();
	out.print(AHIService.queryCarPointOne(plateNumber));
}else if(actions != null && actions.equals("queryCarPointTwo")) {
	AHIService AHIService = new AHIService();
	out.print(AHIService.queryCarPointTwo(plateNumber,id,ratio));
}
%>