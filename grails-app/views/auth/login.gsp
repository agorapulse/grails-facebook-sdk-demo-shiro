<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="website" />
    <title>Login</title>
</head>
<body>

<div class="page-header">
    <h1>Authentication</h1>
</div>
<div class="row">
    <div class="span10">
        <g:if test="${!facebook.app.id}">
            <g:render template="/configError" />
        </g:if>
        <g:else>
            <ul class="authentication">
                <li>
                    <facebook:loginLink appPermissions="${facebook.app.permissions}" elementClass="large primary btn pull-right">Login</facebook:loginLink>
                    Log in via Facebook JavaScript SDK:<br />
                    (<i>with Facebook Grails SDK handling authorization code from cookie on reload</i>)
                </li>
            </ul>
        </g:else>
    </div>
</div>

</body>