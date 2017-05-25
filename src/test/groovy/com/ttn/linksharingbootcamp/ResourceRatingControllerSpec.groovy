package com.ttn.linksharingbootcamp

import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRatingController)
@Mock([User, Topic, Subscription,Resource, ResourceRating,LinkResource,ResourceService])
class ResourceRatingControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "CheckSave"()
    {
        setup:
        User user = new User(firstname: "sargam", lastname: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: "123456",confirmPassword: '123456', username: "sar123").save()

        Topic topic = new Topic(topicname:"Grails", visibility: Visibility.PRIVATE, createdBy: user).save()

        LinkResource linkResource = new LinkResource(url: 'http://www.tothenew.com/', description:"description", createdBy:
                user, topic: topic)
        linkResource.save()


        when:
        session['user']=user
        controller.save(linkResource.id,4)

        then:
        response.redirectedUrl=='/resourceRating/save'


    }

}
