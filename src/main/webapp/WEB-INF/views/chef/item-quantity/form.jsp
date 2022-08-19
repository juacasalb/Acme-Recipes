<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'show' or command =='update'}">
			<jstl:if test="${type == 'KITCHENUTENSIL'}">
				<acme:input-textbox code="chef.quantity.form.label.number" path="number" readonly="true"/>
			</jstl:if>
			<jstl:if test="${type == 'INGREDIENT'}">
				<acme:input-textbox code="chef.quantity.form.label.number" path="number"/>
			</jstl:if>
			<acme:input-textbox code="chef.item.form.label.name" path="name" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.code" placeholder="AB:CDE-123" path="code" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.type" path="type" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.unit" path="unit" readonly="true"/>
			<acme:input-textarea code="chef.item.form.label.description" path="description" readonly="true"/>
			<acme:input-money code="chef.item.form.label.retailPrice" path="retailPrice" readonly="true"/>
			<jstl:if test="${published == true}">
				<acme:submit code="chef.quantity.form.button.delete" action="/chef/quantity/delete"/>
				<jstl:if test="${type == 'INGREDIENT'}">
					<acme:submit code="chef.quantity.form.button.update" action="/chef/quantity/update"/>
				</jstl:if>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<jstl:if test="${type == 'KITCHENUTENSIL' && published == true}">
				<acme:input-integer code="chef.quantity.form.label.number" path="number"  readonly="true"/>
			    <acme:input-select code="chef.quantity.form.label.item" path="itemId">
			        <jstl:forEach items="${items}" var="item">
			            <acme:input-option code="${item.getCode()}   ${item.getName()}" value="${item.getId()}" selected="${item.getId() == itemId}"/>
			        </jstl:forEach>
			    </acme:input-select>
			    <acme:submit code="chef.quantity.kitchenutensil.button.create" action="/chef/quantity/create?type=KITCHENUTENSIL&masterId=${masterId}"/>
		    </jstl:if>
			<jstl:if test="${type == 'INGREDIENT' && published == true}">
				<acme:input-integer code="chef.quantity.form.label.number" path="number"/>
			    <acme:input-select code="chef.quantity.form.label.item" path="itemId">
			        <jstl:forEach items="${items}" var="item">
			            <acme:input-option code="${item.getCode()}   ${item.getName()}" value="${item.getId()}" selected="${item.getId() == itemId}"/>
			        </jstl:forEach>
			    </acme:input-select>
			    <acme:submit code="chef.quantity.ingredient.button.create" action="/chef/quantity/create?type=INGREDIENT&masterId=${masterId}"/>
		    </jstl:if>
    	</jstl:when>
    </jstl:choose>
</acme:form>