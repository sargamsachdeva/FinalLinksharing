package com.ttn.linksharingbootcamp

import constants.Constants
import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TopicService)
@Mock([Topic,Subscription])
class TopicServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testsomething"() {
        expect:"fix me"
            true
    }

    void "checkSaveTopic"(){

        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)

        Topic topic = new Topic(createdBy: user,topicName: "topic1",visibility: Visibility.PRIVATE)

        when:
        service.saveTopic(topic)

        then:
        topic.save(flush:true)
    }
}
