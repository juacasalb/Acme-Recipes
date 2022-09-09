<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.ketema.form.label.theme" path="theme"/>
	
	<acme:input-textarea code="chef.ketema.form.label.statement" path="statement"/>
	<acme:input-money code="chef.ketema.form.label.allotment" path="allotment"/>
	<acme:input-url code="chef.ketema.form.label.moreInfo" path="moreInfo"/>
	<acme:input-moment code="chef.ketema.form.label.finishingDate" path="finishingDate"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="chef.ketema.form.label.keylet" path="keylet" readonly="true"/>
			<acme:input-textbox code="chef.ketema.form.label.item" path="item.name" readonly="true"/>
			<acme:input-moment code="chef.ketema.form.label.instantationMoment" path="instantationMoment" readonly="true"/>
			<acme:submit code="chef.ketema.form.button.update" action="/chef/ketema/update"/>
			<acme:submit code="chef.ketema.form.button.delete" action="/chef/ketema/delete"/>
		</jstl:when>	

		<jstl:when test="${command == 'create'}">
		<acme:input-select code="chef.ketema.form.label.item" path="itemId">
	        <jstl:forEach items="${items}" var="myItem">
	            <acme:input-option code="${myItem.getName()}" value="${myItem.getId()}" selected="${myItem.getId() == itemId}"/>
	        </jstl:forEach>
    	</acme:input-select>
			<acme:submit code="chef.ketema.form.button.create" action="/chef/ketema/create"/>
		</jstl:when>
	</jstl:choose>


</acme:form>