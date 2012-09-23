
import grails.plugin.facebooksdk.FacebookContext
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authz.Permission
import org.apache.shiro.authz.permission.WildcardPermissionResolver
import org.codehaus.groovy.grails.commons.GrailsApplication

class FacebookRealm {
    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken

    // DEACTIVATED Service injection in realm generates a bug in resource plugin...
    //FacebookContext facebookContext
    //UserService userService
    GrailsApplication grailsApplication
    WildcardPermissionResolver shiroPermissionResolver

    SimpleAccount authenticate(authToken) {
        log.info "Attempting to authenticate ${authToken.username} in Facebook realm..."
        SimpleAccount account
        User user
        if (authToken.username == 'facebook') {
            // Facebook authentication
            // WORKAROUND for service injection bug
            FacebookContext facebookContext = grailsApplication.mainContext.getBean('facebookContext') as FacebookContext
            long facebookId = facebookContext.user.id
            if (!facebookId) {
                log.info "Invalid credentials (Facebook realm)"
                throw new IncorrectCredentialsException("Invalid signed request")
            }
            account = new SimpleAccount(facebookId, authToken.credentials, 'FacebookRealm')
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

    boolean hasRole(principal, roleName) {
        int roleCount = User.createCriteria().count() {
            roles {
                eq('name', roleName)
            }
            eq('facebookId', principal)
        }

        return roleCount > 0
    }

    boolean hasAllRoles(principal, roles) {
        int roleCount = User.createCriteria().count() {
            roles {
                'in'('name', roles)
            }
            eq('facebookId', principal)
        }

        return roleCount == roles.size()
    }

    boolean isPermitted(principal, requiredPermission) {
        // First find all the permissions that the user has that match
        // the required permission's type and project code.
        User user = User.findByFacebookId(principal)
        
        // Try each of the permissions found and see whether any of
        // them confer the required permission.
        String foundPermission = user.permissions?.find { permString ->
            Permission perm = shiroPermissionResolver.resolvePermission(permString)
            return perm.implies(requiredPermission)
        }

        if (foundPermission != null) {
            // Found a matching permission!
            return true
        }

        // If not, does he gain it through a role?
        //
        // Get the permissions from the roles that the user does have.
        List results = User.executeQuery("SELECT DISTINCT p FROM User AS user JOIN user.roles AS role JOIN role.permissions AS p WHERE user.facebookId = :facebookId", [facebookId:principal])

        // There may be some duplicate entries in the results, but at this stage it is not worth trying to remove them. Now, create a real permission from each result and check it against the required one.
        foundPermission = results.find { permString ->
            Permission perm = shiroPermissionResolver.resolvePermission(permString)
            return perm.implies(requiredPermission)
        }

        return foundPermission != null
    }
}