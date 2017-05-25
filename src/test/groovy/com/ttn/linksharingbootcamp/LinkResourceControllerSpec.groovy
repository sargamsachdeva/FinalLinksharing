package com.ttn.linksharingbootcamp

import co.LinkResourceCO
import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LinkResourceController)
@Mock([User,Topic,Resource,LinkResource,ResourceService,Subscription,ReadingItem])
class LinkResourceControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def save()
    {
        User user = new User(firstName: "Priyanka", lastName: "Naik", email: "priyanka.naik@tothenew.com",
                password: "123456",confirmPassword: "123456", userName: "priyanka.naik").save()

        Topic topic = new Topic(topicName:"Grails", visibility: Visibility.PRIVATE, createdBy: user).save()

        LinkResourceCO linkResourceCO = new LinkResourceCO(description: "description",
                topic: topic.id,createdBy:user ,url: "https://creately.com/")





        when:
        session['user']=user
        controller.save(linkResourceCO)

        then:
        response.redirectedUrl=='/user/index'
        flash.message=="com.ttn.linksharing.linkResource.save.link.resource.saved"
    }


}