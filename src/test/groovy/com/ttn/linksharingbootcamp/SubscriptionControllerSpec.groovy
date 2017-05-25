package com.ttn.linksharingbootcamp

import constants.Constants
import enums.Seriousness
import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([User, Topic, Subscription,SubscriptionService])
class SubscriptionControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    def "SubscriptionDeleteTest"() {
        setup:
        User user1 = new User(username: "aksah").save(validate: false)

        User user2 = new User(username: "preeti").save(validate: false)

        Topic topic = new Topic(createdBy: user1,topicname: 'some_topic',visibility: Constants.VISIBILITY).save(validate: false)

        Subscription subscription = new Subscription(user: user2, topic: topic, seriousness: Seriousness.CASUAL)


        subscription.save(validate: false)

        when:
        session['user']=user1
        controller.delete(user2.id,topic.id)

        then:
        flash.message=="com.ttn.linksharing.subscription.delete.subscription.deleted"
        response.redirectedUrl=='/subscription/index'
    }

    def "SubscriptionNotDeletedTest"()
    {

        setup:
        User user1 = new User(username: "aksah").save(validate: false)

        User user2 = new User(username: "preeti").save(validate: false)

        Topic topic = new Topic(createdBy: user1,topicname: 'some_topic',visibility: Constants.VISIBILITY).save(validate: false)


        when:
        session['user']=user2
        controller.delete(user2.id,topic.id)

        then:
        flash.error=="com.ttn.linksharing.subscription.delete.subscription.not.deleted"
        response.redirectedUrl=='/subscription/index'
    }

    def "SubscriptionSaveTest"() {
        setup:
        User user1 = new User(username: 'preeti_sisodhia').save(validate:false)



        User user2 = new User(username: 'kanishka_sikka').save(validate:false)

        Topic topic = new Topic(topicname:'topic_save', createdBy: user1, visibility: Visibility.PUBLIC).save(validate:false)






        when:
        session['user'] = user2
        controller.save(user2.id,topic.id)


        then:
        flash.message=="com.ttn.linksharing.subscription.save.subscription.saved"
        response.redirectedUrl=='/subscription/index'

    }

    def "SubscriptionNotSaveTest"() {
        setup:


        User user2 = new User(username: 'kanishka_sikka').save(validate:false)

        Topic topic = new Topic(topicname:'topic_save', createdBy: user2, visibility: Visibility.PRIVATE).save(validate:false)


        when:
        session['user'] = user2
        controller.save(user2.id,topic.id)


        then:
        response.redirectedUrl=='/subscription/index'

    }

    @Unroll
    def "SubscriptionNotUpdateTest"() {
        setup:
        User user = new User().save(validate: false)

        Topic topic = new Topic().save(validate: false)

        Subscription subscription = new Subscription(user: user, topic: topic,seriousness:Seriousness.CASUAL)

        session['user']=user
        subscription.save(validate: false)


        when:
        controller.updateSeriousness(id, seriousness)

        then:
        flash.error =="Subscription not found or already ${seriousness}"
        response.redirectedUrl=='/subscription/index'

        where:
        id | seriousness
        1  | "casual"
    }

    def "SubscriptionUpdateTest"() {
        setup:
        User user = new User().save(validate: false)

        Topic topic = new Topic().save(validate: false)

        Subscription subscription = new Subscription(user: user, topic: topic,seriousness:Seriousness.CASUAL)

        session['user']=user
        subscription.save(validate: false)

        when:
        controller.updateSeriousness(topic.id, "SERIOUS")

        then:
        response.redirectedUrl=='/subscription/index'
        flash.message=="Seriousness updated"

    }

}