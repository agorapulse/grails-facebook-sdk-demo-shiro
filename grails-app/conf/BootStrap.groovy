class BootStrap {

    def init = { servletContext ->
        // Create the admin role
        def adminRole = Role.findByName('ROLE_ADMIN')
        if (!adminRole) {
            adminRole = new Role(name: 'ROLE_ADMIN')
            adminRole.addToPermissions('admin:*')
            adminRole.save(flush: true, failOnError: true)
        }

        // Create the user role
        def userRole = Role.findByName('ROLE_USER')
        if (!userRole) {
            userRole = new Role(name: 'ROLE_USER')
            userRole.addToPermissions('website:*')
            userRole.save(flush: true, failOnError: true)
        }
    }
    def destroy = {
    }
}
