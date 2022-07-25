<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="epicure.epicure-dashboard.form.title"/>
</h1>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.count-fine-dishes"/>
	</caption>
	<jstl:forEach var="dishCount" items="${totalNDishesOfState}">
		<tr>
			<th scope="row">
				<acme:print value=" - ${dishCount.key} : ${dishCount.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.average-budget-fine-dishes"/>
	</caption>
	<jstl:forEach var="budgets" items="${averageBudgetFineDishesOfStateByCurrency}">
		<tr>
			<th scope="row">
				<acme:print value=" - ${budgets.key.first} / ${budgets.key.second} : ${budgets.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.deviation-budget-fine-dishes"/>
	</caption>
	<jstl:forEach var="budgets" items="${deviationBudgetFineDishesOfStateByCurrency}">
		<tr>
			<th scope="row">
				<acme:print value=" - ${budgets.key.first} / ${budgets.key.second} : ${budgets.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.min-budget-fine-dishes"/>
	</caption>
	<jstl:forEach var="budgets" items="${minBudgetFineDishesOfStateByCurrency}">
		<tr>
			<th scope="row">
				<acme:print value=" - ${budgets.key.first} / ${budgets.key.second} : ${budgets.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>

<table class="table table-sm">
	<caption>
		<acme:message code="epicure.epicure-dashboard.form.max-budget-fine-dishes"/>
	</caption>
	<jstl:forEach var="budgets" items="${maxBudgetFineDishesOfStateByCurrency}">
		<tr>
			<th scope="row">
				<acme:print value=" - ${budgets.key.first} / ${budgets.key.second} : ${budgets.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>