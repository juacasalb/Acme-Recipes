<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>
	<acme:input-textarea code="chef.recipe.form.label.description" path="description"/>
	<acme:input-money code="chef.recipe.form.label.preparationNotes" path="preparationNotes"/>
	<acme:input-textbox code="chef.recipe.form.label.link" path="link"/>
	<acme:input-textbox code="chef.recipe.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="chef.recipe.form.label.published" path="published"/>
	<acme:input-textbox code="chef.recipe.form.label.chef" path="chef"/>
	
	<acme:button code="chef.recipe.form.label.items" action="/chef/item/list-item?id=${id}"/>
	
</acme:form>