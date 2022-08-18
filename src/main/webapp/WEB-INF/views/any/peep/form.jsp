<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="form.peep.heading" path="heading"/>
	<acme:input-textbox code="form.peep.writer" path="writer"/>
	<acme:input-textbox code="form.peep.pieceOfText" path="pieceOfText"/>
	<acme:input-email code="form.peep.email" path="email"/>
	<acme:input-checkbox code="form.peep.confirm.message" path="confirm"/>
	<acme:submit code="any.peep.form.create" action="/any/peep/create"/>
</acme:form>