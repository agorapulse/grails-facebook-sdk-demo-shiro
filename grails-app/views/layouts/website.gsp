<!DOCTYPE html>
<html xmlns:fb="http://ogp.me/ns/fb#">
<head>
	<title><g:layoutTitle default="Facebook Grails SDK - Shiro Demo" /></title>
	<g:layoutHead />
	<r:require module="website"/>
	<r:layoutResources />
	<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
	<![endif]-->
  </head>

<body>

    <div class="topbar">
		<div class="fill">
			<div class="container">
				<g:link controller="website" class="brand">Facebook Grails SDK - Shiro Demo</g:link>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="content">
			<g:layoutBody />
		</div>
		<g:render template="/footer" />
	</div>
	<r:layoutResources />
</body>
</html>