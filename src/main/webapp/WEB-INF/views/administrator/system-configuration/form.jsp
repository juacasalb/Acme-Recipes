<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<table>
		<tr>
			<th>
				<acme:message code="administrator.sys-config.currency-default"/>
			</th>
			<th>
				<acme:message code="administrator.sys-config.currency-accepted"/>
			</th>
		</tr>
		<tr>
			<td>
				<acme:print value="${ currencyConfiguration.getDefaultCurrency()}"/>
			</td>
			<td>
				<acme:print value="${ currencyConfiguration.getAcceptedCurrencies()}"/>
			</td>
		</tr>
	</table>
	<acme:submit code="currency.update" action="/administrator/currency-configuration/show"/>
	<table>
		<tr>
			<th>
				<acme:message code="administrator.sys-config.spam-word"/>
			</th>
			<th>
				<acme:message code="administrator.sys-config.spam-threshold"/>
			</th>
			<th>
				<acme:message code="administrator.sys-config.actions"/>
			</th>
		</tr>
		<jstl:forEach var="spam" items="${spamConfiguration}">
			<tr>
				<td>
					<jstl:out value="${spam.getSpamWord() }"/>
				</td>
				<td>
					<jstl:out value="${spam.getThreshold() }"/>
				</td>
				<td>
					<acme:submit code="spam.delete" action="/administrator/spam-tuple/delete?id=${spam.getId()}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<acme:submit code="spam.create" action="/administrator/spam-tuple/create"/>
</acme:form>