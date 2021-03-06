package co

import com.ttn.linksharingbootcamp.User
import grails.validation.Validateable

class UpdateProfileCO implements Validateable{

    Long id
    String firstName
    String lastName
    String userName
    def file

    static constraints = {
        id( blank: false)
        firstName(nullable: true, blank: false)
        lastName(nullable: true, blank: false)
        userName(nullable: true, blank: true)
        file(nullable: true, blank: true)
    }

    User getUser() {
        return User.get(this.id)
    }
}