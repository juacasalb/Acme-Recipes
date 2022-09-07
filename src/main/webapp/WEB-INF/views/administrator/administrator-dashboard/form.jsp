<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<table class="table table-sm">

  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.totalNIngredients"/>
    </th>
    <td>
      <acme:print value="${totalNIngredients}"/>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.totalNKitchenUtensils"/>
    </th>
    <td>
      <acme:print value="${totalNKitchenUtensils}"/>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.totalNDishesByStatus"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${totalNDishesByStatus}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.averageRetailPriceIngredientsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${averageRetailPriceIngredientsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.averageRetailPriceKitchenUtensilsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${averageRetailPriceKitchenUtensilsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.averageBudgetDishesByStatus"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${averageBudgetDishesByStatus}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
   <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.deviationRetailPriceIngredientsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${deviationRetailPriceIngredientsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
   <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.deviationRetailPriceKitchenUtensilsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${deviationRetailPriceKitchenUtensilsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.deviationBudgetDishesByStatus"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${deviationBudgetDishesByStatus}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.minRetailPriceIngredientsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${minRetailPriceIngredientsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.minRetailPriceKitchenUtensilsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${minRetailPriceKitchenUtensilsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.minBudgetDishesByStatus"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${minBudgetDishesByStatus}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.maxRetailPriceIngredientsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${maxRetailPriceIngredientsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.maxRetailPriceKitchenUtensilsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${maxRetailPriceKitchenUtensilsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  
   <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.maxBudgetDishesByStatus"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${maxBudgetDishesByStatus}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  <%-- PIMPAM --%>
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.pimpamRatio"/>
    </th>
    <td>
      <acme:print value="${pimpamRatio} %"/>
    </td>
  </tr>
  
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.averageBudgetPimpampsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${averageBudgetPimpampsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.deviationBudgetPimpampsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${deviationBudgetPimpampsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.minimumBudgetPimpampsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${minimumBudgetPimpampsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  <tr>
    <th scope="row">
      <acme:message code="administrator.dashboard.form.label.maximumBudgetPimpampsByCurrency"/>
    </th>
    <td>
      <jstl:forEach var="entry" items="${maximumBudgetPimpampsByCurrency}">
        <acme:print value="${entry.key}:${entry.value}"/>
      </jstl:forEach>
    </td>
  </tr>
  
  </table>




<acme:return/>
  