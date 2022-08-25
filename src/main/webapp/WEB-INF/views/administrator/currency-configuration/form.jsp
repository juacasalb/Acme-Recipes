<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="administrator.currency-configuration.form.label.defaultCurrency" path="defaultCurrency"/>
	<acme:input-textarea code="administrator.currency-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	
	<acme:submit code="administrator.currency-configuration.form.button.update" action="/administrator/currency-configuration/update"/>
</acme:form>