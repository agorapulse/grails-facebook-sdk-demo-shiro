grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    inherits 'global'
    log 'error'
    checksums true

    repositories {
        inherits true
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
    }
    dependencies {
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.0"
        runtime ":resources:1.1.6"
        runtime ":fbootstrapp:0.1.1"

        runtime ':facebook-sdk:0.4.1'
        runtime ":shiro:1.1.4"

        build ":tomcat:$grailsVersion"
    }
}

//grails.plugin.location.facebookSdk = "../grails-facebook-sdk"
//grails.plugin.location.fbootstrapp = "../fbootstrapp-grails-plugin"