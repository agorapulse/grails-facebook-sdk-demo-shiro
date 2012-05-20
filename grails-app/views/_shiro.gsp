<shiro:notAuthenticated>
    <p>
        You are not authenticated.
    </p>
</shiro:notAuthenticated>
<shiro:authenticated>
    <p>
        You are authenticated with facebookId <b><shiro:principal /></b>.
    </p>
    <p>
        <facebook:logoutLink elementClass="primary btn" nextUrl="${createLink(action:'logout', controller:'auth')}">Logout</facebook:logoutLink>
    </p>
</shiro:authenticated>