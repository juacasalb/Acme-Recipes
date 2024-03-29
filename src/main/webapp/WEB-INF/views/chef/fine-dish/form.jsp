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
	<acme:input-select code="form.chef.fine-dish.state" path="state" readonly="${readOnly}">
		<acme:input-option code="form.chef.fine-dish.state.PROPOSED" value="PROPOSED" selected="${state == 'PROPOSED'}"/>
		<acme:input-option code="form.chef.fine-dish.state.DENIED" value="DENIED" selected="${state == 'DENIED'}"/>
		<acme:input-option code="form.chef.fine-dish.state.ACCEPTED" value="ACCEPTED" selected="${state == 'ACCEPTED'}"/>
	</acme:input-select>
	<acme:input-textbox code="form.chef.fine-dish.code" path="code" readonly="${readOnly}"/>
	<acme:input-textarea code="form.chef.fine-dish.request" path="request" readonly="${readOnly}"/>
	<acme:input-money code="form.chef.fine-dish.budget" path="budget" readonly="${readOnly}"/>
	<acme:input-moment code="form.chef.fine.dish.startPeriod" path="startPeriod" readonly="${readOnly}"/>
	<acme:input-moment code="form.chef.fine.dish.endPeriod" path="endPeriod" readonly="${readOnly}"/>
	<acme:input-url code="form.chef.fine-dish.moreInfo" path="moreInfo" readonly="${readOnly}"/>
	<acme:input-textbox code="form.chef.fine-dish.epicure.identity.name" path="epicure.identity.name" readonly="${readOnly}"/>
	<acme:input-textbox code="form.chef.fine-dish.epicure.identity.surname" path="epicure.identity.surname" readonly="${readOnly}"/>
	<acme:input-textbox code="form.chef.fine-dish.epicure.identity.email" path="epicure.identity.email" readonly="${readOnly}"/>
	<jstl:if test="${state == 'PROPOSED'}">
		<acme:submit code="form.chef.fine-dish.accept" action="/chef/fine-dish/accept"/>
		<acme:submit code="form.chef.fine-dish.deny" action="/chef/fine-dish/deny"/>
	</jstl:if>
	
</acme:form>

