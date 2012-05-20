import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.SimpleAccount
import grails.plugins.facebooksdk.FacebookAppService

class DbRealm {
    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken

    // DEACTIVATED Service injection in realm generates a bug in resource plugin...
    //FacebookAppService facebookAppService
    //UserService userService
    def grailsApplication
    def shiroPermissionResolver

    def authenticate(authToken) {
        log.info "Attempting to authenticate ${authToken.username} in DB realm..."
        def account
        def user
        if (authToken.username == 'facebook') {
            // Facebook authentication
            // WORKAROUND for service injection bug
            FacebookAppService facebookAppService = grailsApplication.mainContext.getBean('facebookAppService')
            Long facebookId = facebookAppService.userId
            if (!facebookId) {
                log.info "Invalid credentials (Facebook realm)"
                throw new IncorrectCredentialsException("Invalid signed request")
            }
            account = new SimpleAccount(facebookId, authToken.credentials, 'DbRealm')
            user = User.findByFacebookId(facebookId)
            if (!user) {
                // Add custom code to create domain user
                // WORKAROUND for service injection bug
                UserService userService = grailsApplication.mainContext.getBean('userService')
                userService.createFacebookUser(facebookId)
            }
        }
        return account
    }

    def hasRole(principal, roleName) {
        def roles = User.withCriteria {
            roles {
                eq("name", roleName)
            }
            eq("username", principal)
        }

        return roles.size() > 0
    }

    def hasAllRoles(principal, roles) {
        def r = User.withCriteria {
            roles {
                'in'("name", roles)
            }
            eq("username", principal)
        }

        return r.size() == roles.size()
    }

    def isPermitted(principal, requiredPermission) {
        // First find all the permissions that the user has that match
        // the required permission's type and project code.
        def user = User.findByFacebookId(principal)
        def permissions = user.permissions

        // Try each of the permissions found and see whether any of
        // them confer the required permission.
        def retval = permissions?.find { permString ->
            // Create a real permission instance from the database
            // permission.
            def perm = shiroPermissionResolver.resolvePermission(permString)

            // Now check whether this permission implies the required
            // one.
            if (perm.implies(requiredPermission)) {
                // User has the permission!
                return true
            }
            else {
                return false
            }
        }

        if (retval != null) {
            // Found a matching permission!
            return true
        }

        // If not, does he gain it through a role?
        //
        // Get the permissions from the roles that the user does have.
        def results = User.executeQuery("SELECT DISTINCT p FROM User AS user JOIN user.roles AS role JOIN role.permissions AS p WHERE user.facebookId = :facebookId", [facebookId:principal])

        // There may be some duplicate entries in the results, but
        // at this stage it is not worth trying to remove them. Now,
        // create a real permission from each result and check it
        // against the required one.
        retval = results.find { permString ->
            // Create a real permission instance from the database
            // permission.
            def perm = shiroPermissionResolver.resolvePermission(permString)

            // Now check whether this permission implies the required
            // one.
            if (perm.implies(requiredPermission)) {
                // User has the permission!
                return true
            }
            else {
                return false
            }
        }

        if (retval != null) {
            // Found a matching permission!
            return true
        }
        else {
            return false
        }
    }
}