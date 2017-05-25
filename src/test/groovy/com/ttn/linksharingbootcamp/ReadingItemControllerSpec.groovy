package com.ttn.linksharingbootcamp

import constants.Constants
import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItemController)
@Mock([User,Resource,DocumentResource,ReadingItem, Topic, Subscription,LinkResource])
class ReadingItemControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testsomething"() {
        expect:"fix me"
            true
    }

    def "CheckToggleIsRead"(){

        setup:

        User user = new User(userName: "sargam1" ,firstName: "sargam", lastName: "sachdeva", email: "sargam.sachdeva@tothenew.com",
                password: Constants.PASSWORD,confirmPassword: Constants.PASSWORD, active: true,admin: true)
        user.save(flush: true)

        session.user=user

        Topic topic = new Topic(topicName: "Grails", visibility: Visibility.PRIVATE, createdBy: user)
        topic.save()

        Resource resource = new DocumentResource(filePath: '/home/sargam', description: 'description',
                createdBy: user, topic: topic)
        resource.save(flush:true)

        ReadingItem readingItem = new ReadingItem(user:user,resource: resource,isRead: true)
        readingItem.save(flush:true)

        when:
        readingItem.isRead = !readingItem.isRead
        controller.toggleIsRead(resource.id)

        then:
        flash.message=="Status updated"
    }

    def "CheckToggleIsReadFalse"()
    {

        setup:
        User user = new User(firstname: "Priya", lastname: "sharma", email: "pri.sharma@tothenew.com",
                password: "123456",confirmPassword: '123456', username: "pri123").save()

        Topic topic = new Topic(topicName: "Grails", visibility: Visibility.PRIVATE, createdBy: user).save()

        LinkResource linkResource = new LinkResource(url: 'http://www.tothenew.com/', description:"description", createdBy:
                user, topic: topic)
        linkResource.save()

        ReadingItem readingItem = new ReadingItem(resource: linkResource,user:user,isRead: false).save(flush:true)

        when:
        session['user']=user
        controller.toggleIsRead(linkResource.id)

        then:
        response.redirectedUrl=='/readingItem/index'

    }

}
