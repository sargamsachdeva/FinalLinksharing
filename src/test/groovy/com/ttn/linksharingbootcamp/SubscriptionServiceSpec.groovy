package com.ttn.linksharingbootcamp

import constants.Constants
import enums.Seriousness
import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionService)
@Mock([User, Topic, Subscription])
class SubscriptionServiceSpec extends Specification {

    def cleanup() {
    }

    void "testsomething"() {
        expect:"fix me"
            true
    }

    def "CheckSaveSubscription"(){

        setup:
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)
        Topic topic = new Topic(createdBy: user,topicName: "topic11121",visibility: Visibility.PRIVATE)

        when:
        boolean result = service.saveSubscription(topic,user)

        then:
        result == true
    }

    def "CheckNotDeleteSubscription"(){

        setup:
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)

        Topic topic = new Topic(createdBy: user,topicName: "topic11121",visibility: Visibility.PRIVATE)

        when:
        boolean result = service.deleteSubscription(topic,user)

        then:
        result==false
    }

    def "CheckSavesubscriptionSuccess"(){

        setup:
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)

        Topic topic = new Topic(createdBy: user,topicName: "topic11121",visibility: Visibility.PRIVATE)

        Subscription subscription=new Subscription(user:user,topic:topic,seriousness: Seriousness.CASUAL)

        when:

        service.saveSubscription(subscription,user)

        then:
        subscription.save(flush:true)
    }
}
