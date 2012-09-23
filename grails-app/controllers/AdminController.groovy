import grails.plugin.facebooksdk.FacebookContext

class AdminController {

    FacebookContext facebookContext

    def beforeInterceptor = {
        log.info "START ${actionUri} with params=${params}"
    }
    def afterInterceptor = { model ->
        log.info("END ${actionUri}")
    }

    def index() {
        [facebookContext: facebookContext]
    }

}
