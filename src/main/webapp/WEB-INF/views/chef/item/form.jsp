<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readOnly}">
	<acme:input-textbox code="chef.item.form.label.name" path="name"/>
	<acme:input-textbox code="chef.item.form.label.code" path="code"/>
	<acme:input-textbox code="chef.item.form.label.unit" path="unit"/>
	<acme:input-textarea code="chef.item.form.label.description" path="description"/>
	<acme:input-money code="chef.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="chef.item.form.label.link" path="link"/>
	<acme:input-textbox code="chef.item.form.label.type" path="type"/>
</acme:form>