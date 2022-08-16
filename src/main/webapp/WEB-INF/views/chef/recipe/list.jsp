<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.recipe.list.label.code" path="code"/>
	<acme:list-column code="chef.recipe.list.label.heading" path="heading"/>
	<acme:list-column code="chef.recipe.list.label.description" path="description"/>
	<acme:list-column code="chef.recipe.list.label.published" path="published"/>
</acme:list>

<acme:button code="chef.recipe.list.button.create" action="/chef/recipe/create"/>