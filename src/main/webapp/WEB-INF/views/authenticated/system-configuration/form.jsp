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
</acme:form>