<%@ include file="/taglibs.jsp" %>

<stripes:layout-definition>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <html>
        <head>
            <title>State Animal Laboratory Messaging Service</title>
            <link rel="stylesheet" type="text/css" media="all" href="css/salms.css"  />
            
            <stripes:layout-component name="htmlHead"/>
        </head>
        <body>
            <div id="content">
                <stripes:layout-component name="header">
                   	<jsp:include page="header.jsp"/>
                </stripes:layout-component>

                <div>
                    <stripes:messages/>
                    <stripes:layout-component name="contents"/>
                </div>


                <div id="footer">

     				<p>
						<strong>Note:</strong> This portal is for use of SALMS partnered laboratories.  SALMS provides a solution 
						for partnering laboratories interested in messaging.
					</p>
					<p>
						This project is being host by Cornell University for the sole purpose of promoting interoperability in laboratories
					</p>
					<p>
						Please contact Scott Ross at Cornell University for more information
					</p>
					All Rights Reserved.
                </div>
            </div>
        </body>
    </html>
</stripes:layout-definition>
