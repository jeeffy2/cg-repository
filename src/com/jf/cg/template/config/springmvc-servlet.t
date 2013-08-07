<?xml version="1.0" encoding="UTF-8" ?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
                    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
                    http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                    http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
                    http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd ">

   <!-- Scans the classpath of this application for @Components to deploy as beans -->
   <context:component-scan base-package="${package}" />

   <context:annotation-config />

   <!-- Configures the @Controller programming model -->
   <mvc:annotation-driven />
   <mvc:default-servlet-handler />

<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
       <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
       <property name="prefix" value="/WEB-INF/jsp/"/>
       <property name="suffix" value=".jsp"/>
   </bean>

<!-- annotation transaction manager -->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
    <tx:annotation-driven transaction-manager="txManager"/>
</beans>
