<%@ include file="/taglibs.jsp" %>

<stripes:layout-render name="/Layouts/standard.jsp" title="Login">
	<stripes:layout-component name="contents">
		<stripes:form action="/UserLogin.action" focus="username">
			<fieldset class="form-submit">
				
				<div class="form-pair">
					<div class="form-item">
						<stripes:label for="username">Username: </stripes:label>						
					</div>
					<div class="form-value">							
						<stripes:text name="username" size="30"/>												
					</div>
				</div>

				<div class="form-pair">
					<div class="form-item">
						<stripes:label for="password">Password: </stripes:label>
					</div>
					<div class="form-value">
						<stripes:password name="password" size="30"/>
					</div>
				</div>
				
				<div class="form-pair">
					<div class="form-value">
						<stripes:errors/>
						<br />
						<stripes:submit name="submit" value="submit" class="input-submit"/>
						<stripes:submit name="reset" value="reset" class="input-reset" />					
					</div>
				</div>
			</fieldset>
			<br />
			<p>
				<stripes:link href="/register.jsp">Register</stripes:link> | 
				<stripes:link href="ForgotPassword.action">Forgot Password</stripes:link>
			</p>
         </stripes:form>
	</stripes:layout-component>
</stripes:layout-render>



