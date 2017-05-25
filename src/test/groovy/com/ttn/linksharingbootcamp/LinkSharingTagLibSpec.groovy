package com.ttn.linksharingbootcamp

import constants.Constants
import enums.Seriousness
import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(LinkSharingTagLib)
@Mock([Subscription])
class LinkSharingTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testsomething"() {
        expect:"fix me"
            true
    }

    void "showSubscribedTopics"(){

        setup:
        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)

        session.user=user

        Topic topic = new Topic(topicName: "Grails", visibility: Visibility.PRIVATE, createdBy: user)

        Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.CASUAL)

        List topics = user?.getSubscribedTopicsForTesting()

        when:
        String result = LinkSharingTagLib.showSubscribedTopics()

        then:
        topics.each {
            result.contains(it.topicName)
        }
    }
}
