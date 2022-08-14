<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="chef.item.form.label.name" path="name"/>
	<acme:input-textbox code="chef.item.form.label.code" path="code"/>
	
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="chef.item.form.label.type" path="type" readonly="true"/>
	</jstl:if>
	
	<acme:input-textbox code="chef.item.form.label.unit" path="unit"/>
	<acme:input-textbox code="chef.item.form.label.description" path="description"/>
	<acme:input-money code="chef.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-checkbox code="chef.item.form.label.published" path="published"/>
	<acme:input-url code="chef.item.form.label.link" path="link"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:submit code="chef.item.form.button.create" action="/chef/item/create?type=${type}"/>
	</jstl:if>
	
	<jstl:if test="${acme:anyOf(command, 'show, update, delete, publish') && published == 'false'}">
		<acme:submit code="chef.item.form.button.update" action="/chef/item/update"/>
		<acme:submit code="chef.item.form.button.publish" action="/chef/item/publish"/>
		<acme:submit code="chef.item.form.button.delete" action="/chef/item/delete"/>
	</jstl:if>

</acme:form> 