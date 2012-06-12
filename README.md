Facebook SDK Grails - Shiro Demo
================================

Sample app to demo how to integrate [Shiro Grails plugin](https://github.com/pledbrook/grails-shiro) with [Facebook SDK Grails plugin](https://github.com/benorama/facebook-sdk-grails-plugin).

* To get more info on Apache Shiro : <http://shiro.apache.org/>

* To get more info on Shiro Grails Plugin : <https://github.com/pledbrook/grails-shiro>

* To get more info on Facebook SDK Grails Plugin : <https://github.com/benorama/facebook-sdk-grails-plugin>


## Getting started

**1- Download or clone the [plugin GitHub project](https://github.com/benorama/facebook-sdk-grails-demo-shiro).**

```groovy
git clone https://benorama@github.com/benorama/facebook-sdk-grails-demo-shiro.git
```

**2- Create a Facebook app on [Facebook Developers](https://developers.facebook.com/apps), in order to get your own app ID and app secret.**

Configure your Facebook app as below:

* *App name space* = my-app-name-space
* *App domain* = localhost
* *Website URL* = http://localhost:8080/facebook-sdk-demo-shiro/website/
* *sandbox mode* = enabled (in Advanced setting, to be able to use the app on Facebook without SSL certificate)

**3- Add your Facebook app parameters to _grails-app/conf/Config.groovy_.**

```groovy
grails.plugin.facebooksdk.appId = {APP_ID}
grails.plugin.facebooksdk.appPermissions = {APP_PERMISSIONS}
grails.plugin.facebooksdk.appSecret = {APP_SECRET}
```

**4-Run the app from the project root.**

```groovy
grails run-app
```

Browse to <http://localhost:8080/facebook-sdk-demo-shiro/website/>.
