<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.item.list.label.name" path="name"/>
	<acme:list-column code="chef.item.list.label.type" path="type"/>
</acme:list>

<acme:button code="chef.item.list.button.create" action="/chef/item/create"/>

	
