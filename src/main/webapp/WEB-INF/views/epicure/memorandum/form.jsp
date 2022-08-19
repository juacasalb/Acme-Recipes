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

<acme:form readonly="${readOnly}">
	<acme:input-integer code="epicure.memoranda.form.label.automatic-seq-num" path="automaticSeqNum"/>
	<acme:input-textbox code="epicure.memoranda.form.label.report" path="report"/>
	<acme:input-url code="epicure.memoranda.form.label.link" path="link"/>
	<acme:input-moment code="epicure.memoranda.form.label.instantiation-moment" path="instantiationMoment"/>
	
	<acme:input-textbox code="epicure.memoranda.form.label.fine-dish.code" path="fineDishCode"/>
	<acme:input-textbox code="epicure.memoranda.form.label.fine-dish.chef-username" path="fineDishChefUsername"/>
</acme:form>
