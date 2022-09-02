<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.pimpam.form.label.title" path="title"/>
	<acme:input-textbox code="chef.pimpam.form.label.item" path="item.name"/>
	<acme:input-textbox code="chef.pimpam.form.label.code" path="code"/>
	<acme:input-textarea code="chef.pimpam.form.label.description" path="description"/>
	<acme:input-money code="chef.pimpam.form.label.budget" path="budget"/>
	<acme:input-url code="chef.pimpam.form.label.link" path="link"/>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="chef.pimpam.form.button.update" action="/chef/pimpam/update"/>
			<acme:submit code="chef.pimpam.form.button.delete" action="/chef/pimpam/delete"/>
			<acme:submit code="chef.pimpam.form.button.publish" action="/chef/pimpam/publish"/>
		</jstl:when>	
		
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="chef.pimpam.form.label.published" path="published" readonly="true"/>
		</jstl:when>

		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.pimpam.form.button.create" action="/chef/pimpam/create"/>
		</jstl:when>
	</jstl:choose>


</acme:form>