<ul class="pills">
    <li <g:if test="${params.controller == 'website' && params.action == 'index'}">class="active"</g:if>><g:link controller="website">Home</g:link></li>
    <li <g:if test="${params.controller == 'website' && params.action == 'secured'}">class="active"</g:if>><g:link action="secured" controller="website">Secured</g:link></li>
    <li <g:if test="${params.controller == 'admin'}">class="active"</g:if>><g:link controller="admin">Admin</g:link></li>
</ul>