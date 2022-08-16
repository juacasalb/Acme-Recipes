<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.quantity.list.label.type" path="item.type"/>
	<acme:list-column code="chef.quantity.list.label.name" path="item.name"/>
	<acme:list-column code="chef.quantity.list.label.retailPrice" path="item.retailPrice"/>
	<acme:list-column code="chef.quantity.list.label.number" path="number"/>
</acme:list>

<acme:button test="${showCreate}" code="chef.quantity.form.button.add" action="/chef/quantity/create?id=${id}"/>