import org.apache.shiro.SecurityUtils

class AuthController {

    def facebookAppService

    def index() { redirect(action: "login", params: params) }

    def login() {
        String loginUrl = facebookAppService.getLoginURL(scope:request.facebook.app.permissions)
        return [ loginUrl:loginUrl,
                 targetUri: params.targetUri ]
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
