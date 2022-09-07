<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.pimpam.list.label.title" path="title"/>
	<acme:list-column code="chef.pimpam.list.label.code" path="code"/>
	<acme:list-column code="chef.pimpam.list.label.budget" path="budget"/>
	<acme:list-column code="chef.pimpam.list.label.item-code" path="itemCode"/>
	<acme:list-column code="chef.pimpam.list.label.instantiation-moment" path="instantiationMoment"/>
</acme:list>
<acme:button code="chef.pimpam.list.label.create-redirect-button" action="/chef/pimpam/create"/>