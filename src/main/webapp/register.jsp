<%@ include file="/taglibs.jsp" %>
<stripes:layout-render name="/Layouts/standard.jsp" title="Login">

	<stripes:layout-component name="htmlHead">
		<link href="css/smart_wizard.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="scripts/jquery-1.4.2.min.js"></script>	
		<script type="text/javascript" src="scripts/jquery.smartWizard-2.0.min.js"></script>
		
		<script type="text/javascript">
		    $(document).ready(function(){		
		    	// Smart Wizard  
		  		$('#wizard').smartWizard({transitionEffect:'slideleft',
		  			onLeaveStep:leaveAStepCallback,onFinish:onFinishCallback,enableFinishButton:true});			
		
		      function leaveAStepCallback(obj)
		      {		
			        var step_num= obj.attr('rel');		
			        return validateSteps(step_num);		
		      }	
		      
		      function onFinishCallback()
		      {		
			       if(validateAllSteps())
			       {	    	  
			        	$('form').submit();	
			       }		
		      }
		
			});	
		    
		    function validateAllSteps()
		    { 	 
		      
		       var isStepValid = true;
		       
		       if(validateStep1() == false)
		       {		
		         isStepValid = false;		
		         $('#wizard').smartWizard('setError',{stepnum:1,iserror:true});
		       }
		       else
		       {		
		         $('#wizard').smartWizard('setError',{stepnum:1,iserror:false});		
		       }
		
		       if(validateStep3() == false)
		       {	
		         isStepValid = false;		         
		         $('#wizard').smartWizard('setError',{stepnum:3,iserror:true});   
		       }
		       else
		       {		
		         $('#wizard').smartWizard('setError',{stepnum:3,iserror:false});		
		       }
		
		       if(!isStepValid)
		       {
		          $('#wizard').smartWizard('showMessage','Please correct the errors in the steps and continue');		
		       }
		       return isStepValid;		
		    } 	
			function validateSteps(step)
			{		
				  var isStepValid = true;		
		      // validate step 1		
		      if(step == 1){
		    	  
		        if(validateStep1() == false ){		
		          isStepValid = false; 		
		          $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');		
		          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         		
		        }else{		
		          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});		
		        }		
		      }
		      
		      // validate step3		
		      if(step == 3){		
		        if(validateStep3() == false ){		
		          isStepValid = false; 		
		          $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');		
		          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         		
		        }else{		
		          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});		
		        }		
		      }
		      return isStepValid;		
		    }
		
			function validateStep1()
			{			
				 MsgConnectionConfig();
			  
		       var isValid = true; 		
		       // Validate Username		
		       var un = $('#username').val();		
		       if(!un && un.length <= 0){		
		         isValid = false;		
		         $('#msg_username').html('Please fill username').show();		
		       }else{		
		         $('#msg_username').html('').hide();		
		       }
		       // validate password		
		       var pw = $('#password').val();		
		       if(!pw && pw.length <= 0){		
		         isValid = false;
		
		         $('#msg_password').html('Please fill password').show();         
		
		       }else{		
		         $('#msg_password').html('').hide();		
		       }
		       
		       // validate confirm password		
		       var cpw = $('#cpassword').val();		
		       
		       if(!cpw && cpw.length <= 0){		
		         isValid = false;		
		         $('#msg_cpassword').html('Please fill confirm password').show();         
		
		       }else{	
		         $('#msg_cpassword').html('').hide();
		
		       }  
		       // validate password match		
		       if(pw && pw.length > 0 && cpw && cpw.length > 0){		
		         if(pw != cpw){		
		           isValid = false;		
		           $('#msg_cpassword').html('Password mismatch').show();   
		         }else{		
		           $('#msg_cpassword').html('').hide();		
		         }		
		       }		
		       return isValid;		
		    }
		    
			function validateStep3(){
		      	return true;		
		    }		
		    // Email Validation		
		    function isValidEmailAddress(emailAddress) 
		    {		
		      var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);		
		      return pattern.test(emailAddress);		
		    } 	
		    
		    //Handle the radio button 
		    function MsgConnectionConfig()
		    {   	
		    	var selValue = $('input[name=connectionOption]:checked').val();	
	    	
		    	if (typeof selValue == 'undefined')
	    		{		    				    		
		    		$("#PullSelected").hide();
		    		$("#PushSelected").hide();  		
	    		}
		    	else
		    	{
			    	if (selValue=="Push")
			    	{
		    		   $("#PullSelected").hide(100);
		    		   $("#PushSelected").show(100);	    		   
			    	}	    		
			    	else if (selValue=="Pull")
		    		{
		    		   $("#PushSelected").hide(100);
		    		   $("#PullSelected").show(100);			    		
			    	}	
		    	}
		    }
		</script>
	</stripes:layout-component>


	<stripes:layout-component name="contents">
		<stripes:form action="/CompletedRegistration.action"  >			
		  <input type='hidden' name="issubmit" value="1">		
	  		<div id="wizard" class="swMain">	
	  			<ul>	
	  				<li><a href="#step-1">	
	                <label class="stepNumber">1</label>	
	                <span class="stepDesc">	
	                   Account Details<br />	
	                   <small>Fill your account details.</small>	
	                </span>	
	            </a></li>	
	  				<li><a href="#step-2">	
	                <label class="stepNumber">2</label>	
	                <span class="stepDesc">	
	                   Institution<br />	
	                   <small>Fill your institution details.</small>	
	                </span>	
	            </a></li>	
	  				<li><a href="#step-3">	
	                <label class="stepNumber">3</label>	
	                <span class="stepDesc">	
	                   Configuration<br />	
	                   <small>Messaging configuration.</small>	
	                </span>	
	             </a></li>	
	  			</ul>	
	  			<div id="step-1">		
	            <h2 class="StepTitle">Step 1: Account Details</h2>	
	            <table cellspacing="3" cellpadding="3" align="center">	
	          			<tr>	
	                    	<td align="center" colspan="3">&nbsp;</td>	
	          			</tr>        	
	          			<tr>	
	                    	<td align="right">Username :</td>	
	                    	<td align="left">	
	                    		<stripes:text id="username" name="username" value="" class="txtBox" />
	                      	</td>	
	                    	<td align="left"><span id="msg_username"></span>&nbsp;</td>	
	          			</tr>	
	          			<tr>	
	                    	<td align="right">Password :</td>	
	                    	<td align="left">	
	                    		<stripes:password id="password" name="password" value="" class="txtBox"/>		                    	  
	                      	</td>	
	                    	<td align="left"><span id="msg_password"></span>&nbsp;</td>	
	          			</tr> 	
	                	<tr>	
	                    	<td align="right">Confirm Password :</td>	
	                    	<td align="left">	
	                    		<stripes:password id="cpassword" name="cpassword" value="" class="txtBox"/>		                    	  
	                      	</td>	
	                    	<td align="left"><span id="msg_cpassword"></span>&nbsp;</td>	
	          			</tr>  
	  			   </table>  
		        </div>
		  			<div id="step-2">	
			            <h2 class="StepTitle">Step 2: Institution Details</h2>		
			            <table cellspacing="3" cellpadding="3" align="center">	
			          			<tr>	
			                    	<td align="center" colspan="3">&nbsp;</td>	
			          			</tr>        	
			          			<tr>	
			                    	<td align="right">Institution Name :</td>	
			                    	<td align="left">				                    	  
			                    	  <stripes:text id="institutionName" name="institutionName" value="" class="txtBox"/>
			                      	</td>	
			                    	<td align="left"><span id="msg_InstitutionName"></span>&nbsp;</td>	
			          			</tr>	
			          			<tr>	
			                    	<td align="right">Contact Email:</td>	
			                    	<td align="left">	
			                    	  <stripes:text id="contactEmail" name="contactEmail" value="" class="txtBox"/>			                    	  
			                      	</td>	
			                    	<td align="left"><span id="msg_contactEmail"></span>&nbsp;</td>	
			          			</tr>
			          			<tr>	
			                    	<td align="right">Application OID:</td>	
			                    	<td align="left">	
			                    	  <stripes:text id="oid" name="oid" value="" class="txtBox"/>			                    	  
			                      	</td>	
			                    	<td align="left"><span id="msg_oid"></span>&nbsp;</td>	
			          			</tr>
			  			   </table>  
		        	</div>     
		  			<div id="step-3">	
		            <h2 class="StepTitle">Step 3: Messaging Configuration</h2>		
		            	<table cellspacing="3" cellpadding="3" align="center">	
		          			<tr>	
		                    	<td align="center" colspan="3">&nbsp;</td>		                    	
		          			</tr>  
     						<tr>	
		                    	<td align="right">Messaging Connection:</td>	
		                    	<td align="left">				                    	  
		                    	  	<stripes:radio id="connectionOption" name="connectionOption" value="Push" onchange="MsgConnectionConfig();" />Push
									<stripes:radio id="connectionOption" name="connectionOption" value="Pull" onchange="MsgConnectionConfig();" />Pull
		                      	</td>		                    		
		          			</tr>	
		          			 <tr>
				                <td> <div id="lblHeader">More Options: </div> </td>
				                <td> 
				                	<div id="PushSelected">
				                		URL to Push To: <stripes:text id="pushUrl" name="pushUrl" value="" class="txtBox"/> <br />
										Header Variables: <stripes:text id="pushHeaderVarible" name="pushHeaderVarible" value="" class="txtBox"/> <br />
	                					HTTP Verb:  
	                					<stripes:radio id="pushHttpVerb" name="pushHttpVerb" value="Post"/>Post
										<stripes:radio id="pushHttpVerb" name="pushHttpVerb" value="Put"/>Put 
										<br />			                		
				                	</div> 
				                	
				                	<div id="PullSelected">
				                		Set to Pull.  No other options.
				                	</div>				                	
			                	</td>
				            </tr> 
		            	</table>      	            	
		        	</div>		  			
	  			</div> 	
		</stripes:form>		
	</stripes:layout-component>
</stripes:layout-render>