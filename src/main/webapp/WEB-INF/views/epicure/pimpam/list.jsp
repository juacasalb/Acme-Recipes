<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.pimpam.list.label.title" path="title"/>
	<acme:list-column code="epicure.pimpam.list.label.budget" path="budget"/>
	<acme:list-column code="epicure.pimpam.list.label.instantationMoment" path="instantationMoment"/>
</acme:list>

<acme:button code="epicure.pimpam.list.button.create" action="/epicure/pimpam/create"/>

