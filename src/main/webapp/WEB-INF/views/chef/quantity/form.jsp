<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-integer code="chef.quantity.form.label.number" path="number"/>		
		<acme:input-textbox code="chef.item.form.label.type" path="item.type" readonly="true"/>		
		<acme:input-textbox code="chef.item.form.label.name" path="item.name" readonly="true"/>
		<acme:input-textbox code="chef.quantity.item.form.label.code" path="item.code" readonly="true"/>
		<acme:input-money code="chef.item.form.label.retailPrice" path="item.retailPrice" readonly="true" />
		<acme:input-textbox code="chef.quantity.recipe.form.label.code" path="recipe.code" readonly="true"/>			
	</jstl:if>
			
	<jstl:if test="${recipePublished == false}">
		<acme:submit code="chef.quantity.form.button.update" action="/chef/quantity/update"/>
		<acme:submit code="chef.quantity.form.button.delete" action="/chef/quantity/delete"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
		<acme:input-integer code="chef.quantity.form.label.number" path="number"/>
		<acme:input-select code="chef.quantity.form.label.item" path="itemId">
			<jstl:forEach items="${items}" var="item">
				<acme:input-option code="${item.getName()}" value="${item.getId()}"
					selected="${item.getId() == itemId}"/>
			</jstl:forEach>
		</acme:input-select>
		<acme:submit code="chef.quantity.form.button.create" action="/chef/quantity/create?id=${masterId}" />	
	</jstl:if>
</acme:form>