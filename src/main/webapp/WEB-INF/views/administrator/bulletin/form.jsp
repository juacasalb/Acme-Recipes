<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="administrator.bulletin.form.label.heading" path="heading"/>
	<acme:input-textarea code="administrator.bulletin.form.label.text" path="text"/>
	<acme:input-checkbox code="administrator.bulletin.form.label.flag" path="flag"/>
	<acme:input-url code="administrator.bulletin.form.label.link" path="link"/>
	<acme:input-checkbox code="administrator.bulletin.form.label.confirm" path="confirm"/>
	
	<acme:submit code="administrator.bulletin.form.button.create" action="/administrator/bulletin/create"/>
</acme:form>