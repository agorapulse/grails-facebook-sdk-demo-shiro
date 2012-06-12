<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="website" />
    <title>Secured page</title>
</head>
<body>

<div class="row">
	<div class="span10">
<g:if test="${!facebook.app.id}">
    <g:render template="/configError" />
</g:if>
<g:else>
    <g:render template="/nav" />
    <h2 class="tab">Secured page</h2>
    <h3>Authentication required</h3>
    <p>
        Shiro authentication is required, with <i>user</i> role (default).
    </p>
</g:else>
</div>
<div class="span4">
    <g:render template="/shiro" />
    <g:render template="/links" />
</div>
</div>

</body>
</html>