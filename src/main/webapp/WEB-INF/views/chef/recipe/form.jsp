<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>
	<acme:input-textarea code="chef.recipe.form.label.description" path="description"/>
	<acme:input-money code="chef.recipe.form.label.preparationNotes" path="preparationNotes"/>
	<acme:input-textbox code="chef.recipe.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'show' && published == true}">
			<acme:input-textbox code="chef.recipe.form.label.retailPrice" path="retailPrice"/>
			<acme:input-textbox code="chef.recipe.form.label.published" path="published"/>
			<acme:input-textbox code="chef.recipe.form.label.chef" path="chef.userAccount.username" readonly="true"/>
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:input-textbox code="chef.recipe.form.label.chef" path="chef.userAccount.username" readonly="true"/>
			<acme:input-money code="chef.recipe.form.label.retailPrice" path="retailPrice" readonly="true"/>	
			<acme:submit code="chef.recipe.form.button.delete" action="/chef/recipe/delete"/>
			<acme:submit code="chef.recipe.form.button.update" action="/chef/recipe/update"/>
			<acme:submit code="chef.recipe.form.button.publish" action="/chef/recipe/publish"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.recipe.form.button.create" action="/chef/recipe/create"/>
		</jstl:when>
	</jstl:choose>
	
	<jstl:if test="${command != 'create'}">
		<acme:button code="chef.recipe.form.label.items" action="/chef/quantity/list?id=${id}"/>
	</jstl:if>
	
</acme:form>