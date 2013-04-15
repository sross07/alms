<%@ include file="/taglibs.jsp" %>
<stripes:layout-render name="/Layouts/standard.jsp" title="Login">
	<stripes:layout-component name="contents">	
		<stripes:form beanclass="org.web.action.ForgotPasswordActionBean">		    
		    Please Enter Your Email:
		    <stripes:text name="email"/>	
		    <stripes:submit name="submit" value="Retreive Password"/>		    
		</stripes:form>	
	</stripes:layout-component>
</stripes:layout-render>