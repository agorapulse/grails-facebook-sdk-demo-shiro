class User {
    Long facebookId

    static hasMany = [ roles: Role, permissions: String ]

    static constraints = {
        facebookId nullable:true
    }
}