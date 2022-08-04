<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="any.peep.list.label.heading" path="heading"/>
	<acme:list-column code="any.peep.list.label.piece-of-text" path="pieceOfText"/>
	<acme:list-column code="any.peep.list.label.instantiation-moment" path="instantationMoment"/>
	<acme:list-column code="any.peep.list.label.writer" path="writer"/>
	<acme:list-column code="any.peep.list.label.email" path="email"/>
</acme:list>