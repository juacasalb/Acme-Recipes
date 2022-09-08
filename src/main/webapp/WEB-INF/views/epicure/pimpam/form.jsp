<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="epicure.quittel.form.label.title" path="title"/>
	
	<acme:input-textbox code="epicure.quittel.form.label.code" path="code"/>
	<acme:input-textarea code="epicure.quittel.form.label.description" path="description"/>
	<acme:input-money code="epicure.quittel.form.label.helping" path="helping"/>
	<acme:input-url code="epicure.quittel.form.label.link" path="link"/>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="epicure.quittel.form.button.update" action="/epicure/quittel/update"/>
			<acme:submit code="epicure.quittel.form.button.delete" action="/epicure/quittel/delete"/>
		</jstl:when>	
		
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="epicure.quittel.form.label.item" path="item.name" readonly="true"/>
		</jstl:when>

		<jstl:when test="${command == 'create'}">
		<acme:input-select code="epicure.quittel.form.label.item" path="item.name">
	        <jstl:forEach items="${items}" var="myItem">
	            <acme:input-option code="${myItem.getName()}" value="${myItem.getId()}" selected="${myItem.getId() == item.name}"/>
	        </jstl:forEach>
    	</acme:input-select>
			<acme:submit code="epicure.quittel.form.button.create" action="/epicure/quittel/create"/>
		</jstl:when>
	</jstl:choose>


</acme:form>