import grails.plugin.facebooksdk.FacebookContext

class WebsiteController {
	
	FacebookContext facebookContext

	def beforeInterceptor = {
        log.info "START ${actionUri} with params=${params}"
    }
	def afterInterceptor = { model ->
        log.info "END ${actionUri}"
    }

    def index() {
        [facebookContext: facebookContext]
    }

	def secured() {
        [facebookContext: facebookContext]
	}

}
