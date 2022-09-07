<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Provider,acme.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.link.alvgomnie" action="http://www.github.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.link.juacasalb" action="https://es.wikipedia.org/wiki/Shiba_Inu"/>
			<acme:menu-suboption code="master.menu.anonymous.link.fraprapra1" action="https://elpais.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.link.miggavmar" action="https://okdiario.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.link.vicgrabru" action="https://www.lavanguardia.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.link.juacasben" action="https://www.elmundo.es/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">
			<acme:menu-suboption code="master.menu.chef.memoranda.list" action="/chef/memorandum/list"/>
			<acme:menu-suboption code="master.menu.chef.fine-dish.list" action="/chef/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.chef.fine-dish.list-proposed" action="/chef/fine-dish/list-proposed"/>
			<acme:menu-suboption code="master.menu.chef.fine-dish.list-accepted" action="/chef/fine-dish/list-accepted"/>
			<acme:menu-suboption code="master.menu.chef.ingredients-link" action="/chef/item/list-my-ingredients"/>
			<acme:menu-suboption code="master.menu.chef.kitchenutensils-link" action="/chef/item/list-my-kitchenutensils"/>
			<acme:menu-suboption code="master.menu.chef.recipe.list" action="/chef/recipe/list"/>		
			<acme:menu-suboption code="master.menu.authenticated.chef" action="/authenticated/chef/update"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.examen.chef.pimpam.list" action="/chef/pimpam/list"/>
    	</acme:menu-option>
		
		<acme:menu-option code="master.menu.any.items">
			<acme:menu-suboption code="master.menu.any.items.kitchenutensils" action="/any/item/list?type=kitchenutensil"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.any.items.ingredients" action="/any/item/list?type=ingredient"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.any.peeps">
			<acme:menu-suboption code="master.menu.any.peeps.list" action="/any/peep/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any.recipes">
			<acme:menu-suboption code="master.menu.any.recipe" action="/any/recipe/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any.user-account">
			<acme:menu-suboption code="master.menu.any.user-account.chef" action="/any/user-account/list?role=chef"/>
			<acme:menu-suboption code="master.menu.any.user-account.epicure" action="/any/user-account/list?role=epicure"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.admin-dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.sys-config" action="/administrator/system-configuration/show-config"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.bulletin.create" action="/administrator/bulletin/create"/>
			<acme:menu-separator/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.memoranda.list" action="/epicure/memorandum/list"/>
			<acme:menu-suboption code="master.menu.epicure.fine-dish.list" action="/epicure/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.epicure.fine-dish.list-published" action="/epicure/fine-dish/list-published"/>
			<acme:menu-suboption code="master.menu.epicure.fine-dish.list-not-published" action="/epicure/fine-dish/list-not-published"/>
			<acme:menu-suboption code="master.menu.epicure.fine-dish.list-accepted" action="/epicure/fine-dish/list-accepted"/>
			<acme:menu-suboption code="master.menu.epicure.fine-dish.list-denied" action="/epicure/fine-dish/list-denied"/>
			<acme:menu-suboption code="master.menu.epicure.fine-dish.list-proposed" action="/epicure/fine-dish/list-proposed"/>
			<acme:menu-suboption code="master.menu.epicure.epicure-dashboard.show" action="/epicure/epicure-dashboard/show"/>
			<acme:menu-suboption code="master.menu.authenticated.epicure" action="/authenticated/epicure/update"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.examen.epicure.pimpam.list" action="/epicure/pimpam/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.bulletins" action="/authenticated/bulletin/list"/>
			<acme:menu-suboption code="master.menu.authenticated.sys-config" action="/authenticated/system-configuration/show-config"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-chef" action="/authenticated/chef/create" access="!hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.become-epicure" action="/authenticated/epicure/create" access="!hasRole('Epicure')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

