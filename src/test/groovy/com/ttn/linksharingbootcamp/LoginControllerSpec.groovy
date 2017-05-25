package com.ttn.linksharingbootcamp

import co.UserCO
import constants.Constants
import grails.plugins.mail.MailService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.beans.propertyeditors.ReaderEditor
import spock.lang.Specification
import util.EncryptUtils

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock([User,UserService,ResetPassword])
class LoginControllerSpec extends Specification {

    def "CheckIndexActionIfUser'sSessionIsSetThenRequestIsForwardToUserIndexAction"() {
        setup:
        session.user = new User()

        when:
        controller.index()

        then:
        response.forwardedUrl == "/user/index"
    }


    def "CheckLogoutUser'sSessionIsInvalidatedThenRedirectToLoginIndexAction"() {
        when:
        controller.logout()

        then:
        session.user == null
        response.redirectedUrl == "/login/index"
    }


    def "CheckLoginHandlerUserIsAbleToLogin"() {
        setup:
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,active: true,admin: true)
        user.save(flush: true)

        when:
        controller.login(user.userName, user.password)

        then:
        response.redirectedUrl == '/login/index'
    }

    def "CheckRegistration"() {
        setup:
        params.file=""
        UserCO user = new UserCO(userName: userName, firstName: firstName, lastName: lastName, email: email, password: password,
                confirmPassword: confirmPassword)
        when:
        controller.registration(user)

        then:
        response.redirectedUrl == '/login/index'

        where:
        userName          | firstName | lastName   | email                          | password | confirmPassword | result
        "sargam.sachdeva" | "sargam"  | "sachdeva" | "sargam.sachdeva@tothenew.com" | "123abc" | "123abc"        | "user.registered.successfully"
    }

    def "CheckforgotPassword"(){

        when:
        controller.forgotPassword()

        then:
        view=="/auth/forgotPassword"
    }

    def "CheckReset"(){

        setup:
        String code="%5BB%4042bbdbcd"

        when:
        controller.reset(code)

        then:
        view=="/index"

    }

    def "checkForResetWhenUserExists"(){
        setup:
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)
        user.save(flush: true)

        String email = user.email
        String hashed = EncryptUtils.encryptSHA256("${email}${Constants.SALT}" as String)
        ResetPassword rpass = new ResetPassword(email: email, urlHash: hashed)
        rpass.save()

        when:
        controller.reset(hashed)

        then:
        view=="/auth/resetPassword"

    }
}