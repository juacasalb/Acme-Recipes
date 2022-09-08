<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.quittel.form.label.title" path="title"/>
	
	<acme:input-textarea code="chef.quittel.form.label.description" path="description"/>
	<acme:input-money code="chef.quittel.form.label.helping" path="helping"/>
	<acme:input-url code="chef.quittel.form.label.link" path="link"/>
	<acme:input-moment code="chef.quittel.form.label.finishingDate" path="finishingDate"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="chef.quittel.form.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="chef.quittel.form.label.item" path="item.name" readonly="true"/>
			<acme:input-moment code="chef.quittel.form.label.instantationMoment" path="instantationMoment" readonly="true"/>
			<acme:submit code="chef.quittel.form.button.update" action="/chef/quittel/update"/>
			<acme:submit code="chef.quittel.form.button.delete" action="/chef/quittel/delete"/>
		</jstl:when>	

		<jstl:when test="${command == 'create'}">
		<acme:input-select code="chef.quittel.form.label.item" path="itemId">
	        <jstl:forEach items="${items}" var="myItem">
	            <acme:input-option code="${myItem.getName()}" value="${myItem.getId()}" selected="${myItem.getId() == itemId}"/>
	        </jstl:forEach>
    	</acme:input-select>
			<acme:submit code="chef.quittel.form.button.create" action="/chef/quittel/create"/>
		</jstl:when>
	</jstl:choose>


</acme:form>