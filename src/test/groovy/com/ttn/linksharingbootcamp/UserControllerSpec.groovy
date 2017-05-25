package com.ttn.linksharingbootcamp

import co.ResourceSearchCO
import co.SearchCO
import co.UpdatePasswordCO
import co.UpdateProfileCO
import co.UserSearchCO
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.control.io.ReaderSource
import org.springframework.mock.web.MockMultipartFile
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock([User,Resource,ReadingItem,UserService,Subscription, Topic])
class UserControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "ProfileTest"() {
        given:
        ResourceSearchCO resourceSearchCO = new ResourceSearchCO()
        resourceSearchCO.max=5
        resourceSearchCO.offset=0
        User user = new User(firstName: "Priyanka", lastName: "Naik", email: "priyanka.naik@tothenew.com",
                password: "123456",confirmPassword: '123456', userName: "priyanka.naik").save()
        session['user']=user
        resourceSearchCO.id=user.id

        when:
        controller.profile(resourceSearchCO)

        then:
        view=='/user/profile'
    }

    def "ToggleACtiveStatus"()
    {
        given:
        User user1= new User(firstname: "Priyanka",lastname: "Naik",email: "priyanka.naik@gmail.com",admin:true,active: true,
                password: '123456', confirmPassword: '123456',username: 'priya').save()
        User user2= new User(firstname: "Sargam",lastname: "Naik",email: "sargam.naik@gmail.com",admin:false,active: true,
                password: '123456', confirmPassword: '123456',username: 'sargam').save()

        session['user']=user1

        when:
        controller.updateUserActiveStatus(user2.id)

        then:
        flash.message == "com.ttn.linksharing.user.update.User.Active.Status.active.status.toggled"
        response.redirectedUrl=='/user/index'

    }

    def "ToggleNotActiveStatus"()
    { given:
    User user1= new User(firstname: "Priyanka",lastname: "Naik",email: "priyanka.naik@gmail.com",admin:false,active: true,
            password: '123456', confirmPassword: '123456',username: 'priya').save()
    User user2= new User(firstname: "Sargam",lastname: "Naik",email: "sargam.naik@gmail.com",admin:false,active: true,
            password: '123456', confirmPassword: '123456',username: 'sargam').save()

    session['user']=user1

        when:
        controller.updateUserActiveStatus(user2.id)

        then:
        flash.error == "com.ttn.linksharing.user.update.User.Active.Status.active.status.not.toggled"
        response.redirectedUrl=='/user/index'

    }

    @Unroll
    def "CheckUpdatePassword"()
    {
        given:
        User user = new User(firstname: "Sargam",lastname: "Naik",email: "sargam.naik@gmail.com",admin:false,active: true,
                password: '123456', confirmPassword: '123456',username: 'sargam').save()
        UpdatePasswordCO updatePasswordCO = new UpdatePasswordCO(id:user.id,oldPassword:'123456',password: '67890'
                ,confirmPassword:'67890' )

        when:
        session['user']=user
        controller.updatePassword(updatePasswordCO)

        then:
        flash.message == "com.ttn.linksharing.user.save.password.updated"
        response.redirectedUrl=='/user/index'


    }

    def "CheckNotUpdatedPassword"()
    {


        given:
        User user1 = new User(firstname: "Sargam",lastname: "Naik",email: "sargam.naik@gmail.com",admin:false,active: true,
                password: '123456', confirmPassword: '123456',username: 'sargam').save()
        UpdatePasswordCO updatePasswordCO1= new UpdatePasswordCO(id:user1.id,oldPassword:'123456',password: '6789'
                ,confirmPassword:'67890' )

        when:
        session['user']=user1
        controller.updatePassword(updatePasswordCO1)

        then:
        flash.message=="com.ttn.linksharing.userCO.invalid"
        response.redirectedUrl=='/user/index'

    }

    def "CheckShowEditProfile"()
    {
        given:
        session['user']= new User()

        when:
        controller.showEditProfile()

        then:
        view=='/user/edit'
    }


    def "CheckSave"()
    {

        setup:
        grailsApplication.config.grails.app.photoPath="/home/sargam/grails_basic/grails-app/assets/images/grails_user_image/"
        params.file=new MockMultipartFile(" ")
        UpdateProfileCO updateProfileCO = new UpdateProfileCO(userName: "Sargam",lastName: "Sachdeva",
                firstName: "Sargam")
        User user = new User(userName: "Priyanka",firstName: "Priyanka",lastName: "Naik",
                password: "123456",confirmPassword: "123456",email: "priyanka@gmail.com").save()

        when:
        session['user']=user
        controller.save(updateProfileCO)

        then:
        flash.message=="com.ttn.linksharing.user.save.profile.updated"
        view=='/user/edit'

    }


    def "CheckSaveWithFile"()
    {

        setup:
        grailsApplication.config.grails.app.photoPath="/home/sargam/grails_basic/grails-app/assets/images/grails_user_image/"
        params.file=new MockMultipartFile("/home/sargam/boot1.png")

        UpdateProfileCO updateProfileCO = new UpdateProfileCO(userName: "Sargam",lastName: "Sachdeva",
                firstName: "Sargam")
        User user = new User(userName: "Priyanka",firstName: "Priyanka",lastName: "Naik",
                password: "123456",confirmPassword: "123456",email: "priyanka@gmail.com").save()

        when:
        session['user']=user
        controller.save(updateProfileCO)

        then:
        flash.message=="com.ttn.linksharing.user.save.profile.updated"
        view=='/user/edit'

    }
    def "TopicSearchTest"()
    {
        given:
        User user = new User(userName: "Priyanka",firstName: "Priyanka",lastName: "Naik",
                password: "123456",confirmPassword: "123456",email: "priyanka@gmail.com",active:true,admin: true).save()

        when:
        views['/topic/_list.gsp']='mock template contents'
        session['user']=user
        controller.topics(user.id)

        then:
        response.text=='mock template contents'

    }

    def "SubscriptionSearchTest"()
    {
        given:
        User user = new User(userName: "Priyanka",firstName: "Priyanka",lastName: "Naik",
                password: "123456",confirmPassword: "123456",email: "priyanka@gmail.com",active:true,admin: true).save()

        when:
        views['/topic/_list.gsp']='mock template contents'
        session['user']=user
        controller.subscriptions(user.id)

        then:
        response.text=='mock template contents'

    }
    def "CheckRegisteredUser"()
    { given:
    User user = new User(userName: "Priyanka",firstName: "Priyanka",lastName: "Naik",
            password: "123456",confirmPassword: "123456",email: "priyanka@gmail.com",active:true,admin: true).save()
    UserSearchCO userSearchCO = new UserSearchCO(max: 5,offset: 0,order: 'asc')

        when:
        session['user']=user
        controller.registeredUsers(userSearchCO)

        then:
        response.redirectedUrl=='/user/index'


    }





}