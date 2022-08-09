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

<acme:form>
	<acme:input-select code="form.epicure.fine-dish.state" path="state"
		readonly="true">
		<acme:input-option code="form.epicure.fine-dish.state.PROPOSED"
			value="PROPOSED" selected="${type == 'PROPOSED'}" />
		<acme:input-option code="form.epicure.fine-dish.state.DENIED"
			value="DENIED" selected="${type == 'DENIED'}" />
		<acme:input-option code="form.epicure.fine-dish.state.ACCEPTED"
			value="ACCEPTED" selected="${type == 'ACCEPTED'}" />
	</acme:input-select>
	<acme:input-textbox code="form.epicure.fine-dish.code" path="code"
		readonly="${readOnly}" />
	<acme:input-textarea code="form.epicure.fine-dish.request"
		path="request" readonly="${readOnly}" />
	<acme:input-money code="form.epicure.fine.dish.budget" path="budget" />
	<acme:input-moment code="form.epicure.fine.dish.startPeriod"
		path="startPeriod" readonly="${readOnly}" />
	<acme:input-moment code="form.epicure.fine.dish.endPeriod"
		path="endPeriod" readonly="${readOnly}" />
	<acme:input-url code="form.epicure.fine-dish.moreInfo" path="moreInfo"
		readonly="${readOnly}" />
	<jstl:if test="${command != 'create' }">
		<acme:input-textbox code="form.epicure.fine-dish.chef.identity.name"
			path="chef.identity.name" readonly="${readOnly}" />
		<acme:input-textbox
			code="form.epicure.fine-dish.chef.identity.surname"
			path="chef.identity.surname" readonly="${readOnly}" />
		<acme:input-textbox code="form.epicure.fine-dish.chef.identity.email"
			path="chef.identity.email" readonly="${readOnly}" />
	</jstl:if>
	<jstl:if test="${command =='create' }">
		<acme:input-textbox code="form.epicure.fine-dish.chef.find"
			path="chefun" />
		<acme:submit code="form.epicure.fine-dish.create"
			action="/epicure/fine-dish/create" />
	</jstl:if>


</acme:form>

