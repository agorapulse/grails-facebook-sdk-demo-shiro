import grails.plugin.facebooksdk.FacebookAppService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.web.util.WebUtils

class SecurityFilters {

    FacebookAppService facebookAppService

    def filters = {
        all(uri: "/**") {
            before = {
                // Sign in if required
                def subject = SecurityUtils.subject
                def facebookId = facebookAppService.userId
                log.info "Check facebook authentication facebookId=$facebookId."
                if (!subject.authenticated && facebookId) {
                    // Login user
                    def authToken = new UsernamePasswordToken('facebook', '')
                    //try {
                        // Perform the actual login.
                        subject.login(authToken)
                        log.info "User logged in"
                    //} catch (AuthenticationException exception) {
                        // Authentication failed.
                        //log.info "Authentication failure."
                    //}
                } else if (subject.authenticated && !facebookId) {
                    // Logout user
                    facebookAppService.invalidateUser()
                    subject.logout()
                } else if (subject.authenticated) {
                    log.info "User authenticated"
                } else {
                    log.info "User not authenticated"
                }

                if (subject.authenticated && controllerName == 'auth' && actionName == 'login')  {
                    def targetUri = params.targetUri ?: "/"
                    // Handle requests saved by Shiro filters.
                    def savedRequest = WebUtils.getSavedRequest(request)
                    if (savedRequest) {
                        targetUri = savedRequest.requestURI - request.contextPath
                        if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
                    }
                    redirect(uri: targetUri)
                }

                // Ignore direct views (e.g. the default main index page).
                if (!controllerName || (controllerName == 'website' && actionName == 'index')) return true

                // Access control by convention.
                accessControl()
            }
        }
    }
}
