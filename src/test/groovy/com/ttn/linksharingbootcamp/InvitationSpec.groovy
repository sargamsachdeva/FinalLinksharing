package com.ttn.linksharingbootcamp

import constants.Constants
import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Invitation)
@Mock([User,Topic])
class InvitationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testsomething"() {
        expect:"fix me"
            true
    }

    @Unroll
    void "testInvitationValidations"() {

        setup:
        User user1 = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)
        user1.save(flush: true)
        Topic topic1 = new Topic(topicName: "Grails", visibility: Visibility.PRIVATE, createdBy: user1)
        topic1.save()
        Invitation invitation = new Invitation(invited: invited,invitee:user1,urlHash: hash,topic: topic1)

        when:
        Boolean result = invitation.validate()

        then:
        result == valid

        where:
        sno|invited   |hash             |   valid

        1  |"sargamm"|"%5BB%4042bbdbcd" | true
        2  |"ankita" |""                | false
    }
}
