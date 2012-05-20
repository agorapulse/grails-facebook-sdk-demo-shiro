class WebsiteController {
	
	def facebookAppService

	def beforeInterceptor = [action:this.&before]
	def afterInterceptor = [action:this.&after]

	def before() {
		log.info("START ${actionUri} with params=${params}")
        request.logoutUrl = facebookAppService.getLogoutURL(next:createLink(action:'logout', absolute:true))
	}

	def after(model) {
		log.info("END ${actionUri}")
	}

    def index() {

    }

	def secured() {

	}

}
