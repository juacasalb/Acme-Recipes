<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.quittel.list.label.title" path="title"/>
	<acme:list-column code="epicure.quittel.list.label.helping" path="helping"/>
	<acme:list-column code="epicure.quittel.list.label.instantationMoment" path="instantationMoment"/>
</acme:list>

<acme:button code="epicure.quittel.list.button.create" action="/epicure/quittel/create"/>

