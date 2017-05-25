package com.ttn.linksharingbootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResetPassword)
class ResetPasswordSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testsomething"() {
        expect:"fix me"
            true
    }

    void "CheckResetPasswordvalidations"() {

        setup:
        ResetPassword resetPassword = new ResetPassword(email: email , urlHash: hash)

        when:
        Boolean result = resetPassword.validate()

        then:
        result == valid

        where:
        sno|email                       |hash             |   valid

        1  |"sargam.sachdeva@gmail.com" |"%5BB%4042bbdbcd" | true
        2  |"ankita.arora@gmail.com"    |""                | false
    }
}
