<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="chef.quantity.list.label.number" path="number"/>
    <acme:list-column code="chef.quantity.list.label.item-name" path="name"/>
    <acme:list-column code="chef.quantity.list.label.item-code" path="code"/>
    <acme:list-column code="chef.quantity.list.label.item-type" path="type"/>
    <acme:list-column code="chef.quantity.list.label.item-unit" path="unit"/>
    <acme:list-column code="chef.quantity.list.label.item-retailPrice" path="retailPrice"/>
</acme:list>

<jstl:if test="${type == 'TOOL' && published == true}">
	<acme:button code="chef.quantity.button.addKitchenUtensil" action="/chef/quantity/create?type=KITCHENUTENSIL&masterId=${masterId}"/>
</jstl:if>

<jstl:if test="${type == 'COMPONENT' && published == true}">
	<acme:button code="chef.quantity.button.addIngredient" action="/chef/quantity/create?type=INGREDIENT&masterId=${masterId}"/>
</jstl:if>