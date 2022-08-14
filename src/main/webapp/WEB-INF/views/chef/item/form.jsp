<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.item.form.label.name" path="name"/>
	<acme:input-textbox code="chef.item.form.label.unit" path="unit"/>
	<acme:input-select code="chef.item.form.label.type" path="type">
		<acme:input-option code="INGREDIENT" value="INGREDIENT"
			selected="${type == 'INGREDIENT'}" />
		<acme:input-option code="KITCHENUTENSIL" value="KITCHENUTENSIL"
			selected="${type == 'KITCHENUTENSIL'}" />
	</acme:input-select>
	<acme:input-textbox code="chef.item.form.label.code" path="code"/>
	<acme:input-textarea code="chef.item.form.label.description" path="description"/>
	<acme:input-money code="chef.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="chef.item.form.label.link" path="link"/>
	<acme:input-select code="chef.item.list.label.published" path="published">
<%-- 		<acme:input-option code="true" value="true" selected="${published == true}"/>
 --%>		<acme:input-option code="false" value="false" selected="${published == false }"/>
	</acme:input-select>	 

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="chef.item.form.button.update" action="/chef/item/update"/>
			<acme:submit code="chef.item.form.button.delete" action="/chef/item/delete"/>
			<acme:submit code="chef.item.form.button.publish" action="/chef/item/publish"/>
		</jstl:when>	

		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.item.form.button.create" action="/chef/item/create"/>
		</jstl:when>
	</jstl:choose>


</acme:form>