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

<acme:form readonly="${readOnly}">
	<acme:input-textbox code="chef.pimpam.form.label.code" path="code" readonly="${true}"/>
	<acme:input-textbox code="chef.pimpam.form.label.item-code" path="itemCode" readonly="${command != 'create'}"/>
	<acme:input-textbox code="chef.pimpam.form.label.title" path="title"/>
	<acme:input-textbox code="chef.pimpam.form.label.description" path="description"/>
	<acme:input-url code="chef.pimpam.form.label.link" path="link"/>
	<acme:input-money code="chef.pimpam.form.label.budget" path="budget"/>
	<acme:input-moment code="chef.pimpam.form.label.instantiation-moment" path="instantiationMoment" readonly="${true}"/>
	<acme:input-moment code="chef.pimpam.form.label.period-start" path="periodStart"/>
	<acme:input-moment code="chef.pimpam.form.label.period-end" path="periodEnd"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.pimpam.form.label.create.button" action="/chef/pimpam/create"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, delete, update')}">
			
			<jstl:if test="${published==false}">
				<acme:submit code="chef.pimpam.form.label.delete.button" action="/chef/pimpam/delete"/>
				<acme:submit code="chef.pimpam.form.label.update.button" action="/chef/pimpam/update"/>
			</jstl:if>
		</jstl:when>
	</jstl:choose>
</acme:form>
