import org.apache.shiro.SecurityUtils
import grails.plugin.facebooksdk.FacebookContext

class AuthController {

    FacebookContext facebookContext

    def index() {
        redirect(action: "login", params: params)
    }

    def login() {
        [
                facebookContext: facebookContext,
                targetUri: params.targetUri
        ]
    }

    def logout() {
        // Log the user out of the application.
        SecurityUtils.subject?.logout()

        // For now, redirect back to the home page.
        redirect(uri: "/")
    }

    def unauthorized() {
        render "You do not have permission to access this page."
    }
}
