<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.ketema.list.label.theme" path="theme"/>
	<acme:list-column code="chef.ketema.list.label.allotment" path="allotment"/>
	<acme:list-column code="chef.ketema.list.label.instantationMoment" path="instantationMoment"/>
</acme:list>

<acme:button code="chef.ketema.list.button.create" action="/chef/ketema/create"/>

