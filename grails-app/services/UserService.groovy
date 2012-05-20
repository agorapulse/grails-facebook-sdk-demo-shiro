class UserService {

    void createFacebookUser(Long facebookId) {
        User user = new User(facebookId:facebookId)
        // user.addToRoles(Role.findByName('ROLE_ADMIN'))
        user.addToRoles(Role.findByName('ROLE_USER'))
        user.save(flush: true, failOnError: true)
    }
}
