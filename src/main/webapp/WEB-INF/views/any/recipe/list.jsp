<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.recipe.list.label.code" path="code"/>
	<acme:list-column code="any.recipe.list.label.heading" path="heading"/>
	<acme:list-column code="any.recipe.list.label.description" path="description"/>
</acme:list>