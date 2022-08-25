<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="administrator.spam-tuple.form.label.spamWord" path="spamWord"/>
	<acme:input-textarea code="administrator.spam-tuple.form.label.threshold" path="threshold"/>
	
	<acme:submit code="administrator.spam-tuple.form.button.create" action="/administrator/spam-tuple/create"/>
</acme:form>