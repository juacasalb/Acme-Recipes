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
	<acme:list-column code="chef.memoranda.list.label.automatic-seq-num" path="automaticSeqNum"/>
	<acme:list-column code="chef.memoranda.list.label.instantiation-moment" path="instantiationMoment"/>
	<acme:list-column code="chef.memoranda.list.label.fine-dish.code" path="fineDishCode"/>
	<acme:list-column code="chef.memoranda.list.label.fine-dish.epicure-username" path="fineDishEpicureUsername"/>
</acme:list>
<acme:button code="chef.memoranda.list.label.create-redirect-button" action="/chef/memorandum/create"/>
