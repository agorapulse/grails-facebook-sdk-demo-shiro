<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="website" />
    <title>Welcome public page</title>
</head>
<body>

<g:render template="/header" model="${[facebookContext: facebookContext]}" />

<div class="row">
	<div class="span10">
		<g:if test="${!facebookContext.app.id}">
			<g:render template="/configError" />
		</g:if>
        <g:render template="/nav" />
        <h2 class="tab">Public page</h2>
		<h3>No authentication required</h3>
        <p>
            This page is public and does not required any authentication.
        </p>
        <p>
            You can access secured page (authentication required with <i>user</i> role) or an admin controller (authentication required with <i>admin</i> role).
        </p>
	</div>
	<div class="span4">
        <g:render template="/shiro" />
        <g:render template="/links" />
	</div>
</div>

</body>
</html>