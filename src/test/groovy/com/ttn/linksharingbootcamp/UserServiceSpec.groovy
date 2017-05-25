package com.ttn.linksharingbootcamp

import co.UpdatePasswordCO
import co.UpdateProfileCO
import co.UserCO
import constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User])
class UserServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testsomething"() {
        expect:"fix me"
            true
    }

    def "CheckSaveUser"(){

        setup:
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)

        when:
        service.saveUser(user)

        then:
        user.save(flush:true)
    }

    def "toggleActiveStatus"() {

        setup:
        User admin = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)

        User normal = new User(userName: "sar123" ,firstName: "priyanka", lastName: "naik", email: "pri.naik@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: false)

        when:
        service.toggleActiveStatus(admin,normal)

        then:
        service.saveUser(normal) !=null
    }

    def "UpdatePassword"() {

        setup:
        UpdatePasswordCO updatePasswordCO=new UpdatePasswordCO(oldPassword: Constants.PASSWORD,password: "sar123",
                                           confirmPassword: "sar123")
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)
        user.save(flush:true)
        updatePasswordCO.id=user.id

        when:
        service.updatePassword(updatePasswordCO)

        then:
        service.saveUser(user)
    }

    def "RegisterUser"(){

        setup:

        UserCO userco = new UserCO(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD,isAdmin: true)

        MultipartFile sourceFile=new MockMultipartFile("/home/sargam/boot1.jpg")

        when:
        service.registerUser(userco,sourceFile)

        then:
        assert 1 == User.countByEmail(userco.email)
    }

    def "UpdateProfile"(){

        setup:
        MultipartFile file=new MockMultipartFile("/home/sargam/boot1.jpg")
        UpdateProfileCO updateProfileCO = new UpdateProfileCO(file: file,firstName: "sargam",lastName: "sachdeva",
                                            userName: "sargam123")

        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)

        user.save(flush:true)
        updateProfileCO.id=user.id
        when:
        service.updateProfile(updateProfileCO)

        then:
        service.saveUser(user)
    }
}
