Grails Facebook SDK - Shiro Demo
================================

Sample app to demo how to integrate [Grails Shiro plugin](https://github.com/pledbrook/grails-shiro) with [Grails Facebook SDK plugin](https://github.com/benorama/grails-facebook-sdk).

* To get more info on Apache Shiro : <http://shiro.apache.org/>

* To get more info on Shiro Grails Plugin : <https://github.com/pledbrook/grails-shiro>

* To get more info on Facebook SDK Grails Plugin : <https://github.com/benorama/grails-facebook-sdk>


## Getting started

**1- Download or clone the [plugin GitHub project](https://github.com/benorama/grails-facebook-sdk-demo-shiro).**

```groovy
git clone https://benorama@github.com/benorama/grails-facebook-sdk-demo-shiro.git
```

**2- Create a Facebook app on [Facebook Developers](https://developers.facebook.com/apps), in order to get your own app ID and app secret.**

Configure your Facebook app as below:

* *App name space* = my-app-name-space
* *App domain* = localhost
* *Website URL* = http://localhost:8080/facebook-sdk-demo-shiro/website/
* *sandbox mode* = enabled (in Advanced setting, to be able to use the app on Facebook without SSL certificate)

**3- Add your Facebook app parameters to _grails-app/conf/Config.groovy_.**

```groovy
grails.plugin.facebooksdk.app.id = {APP_ID}
grails.plugin.facebooksdk.app.permissions = {APP_PERMISSIONS}
grails.plugin.facebooksdk.app.secret = {APP_SECRET}
```

**4-Run the app from the project root.**

```groovy
grails run-app
```

Browse to <http://localhost:8080/facebook-sdk-demo-shiro/website/>.
