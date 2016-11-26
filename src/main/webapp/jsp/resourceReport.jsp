<%--
  Created by IntelliJ IDEA.
  User: Booker
  Date: 11/24/16
  Time: 6:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
<jsp:include page="header.jsp"/>
<div class="row header">
    <div class="col-xs-12">
        <div class="col-xs-2">
            <form action="../mainMenu" method="POST">
                <a href="#" onclick="$(this).closest('form').submit()">Back to Main Menu</a>
            </form>
        </div>
        <div class="col-xs-8 text-center">
            Emergency Resource Report
        </div>
        <div class="col-xs-2 pull-right">
            <form action="../Logout" method="POST">
                <a href="#" onclick="$(this).closest('form').submit()">Logout</a>
            </form>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-centered welcomeMessage">
            <p class="font30 text-center">Resource Report for <b>${username}!</b></p>
        </div>
        <br><br>
        <table class="table">
            <tr>
                <th>#</th>
                <th>Primary Emergency Support Function</th>
                <th>Total Resources</th>
                <th>Resources in Use</th>
            </tr>
            <c:forEach items="${resourceReport}" var="rowResourceReport">
                <tr>
                    <c:if test="${rowResourceReport.rowNumber!=0}">
                        <td>${rowResourceReport.rowNumber}</td>
                        <td>${rowResourceReport.primaryEmergencySupportFunction}</td>
                        <td>${rowResourceReport.totalResources}</td>
                        <td>${rowResourceReport.resourcesInUse}</td>
                    </c:if>
                    <c:if test="${rowResourceReport.rowNumber==0}">
                        <th></th>
                        <th>TOTALS</th>
                        <th>${rowResourceReport.totalResources}</th>
                        <th>${rowResourceReport.resourcesInUse}</th>
                    </c:if>
                </tr>
            </c:forEach>
            <tr>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
