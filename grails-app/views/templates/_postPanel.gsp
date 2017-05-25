<div class="row" style="border-bottom: 1px solid #000;margin-top: 10px">
    <div class="col-md-2">
        <g:link controller="user" action="profile" params="[id: post.userID]">
            <ls:userImage userId="${post.userID}" class="img img-responsive img-thumbnail" height="75px"
                          width="75px"/>
        </g:link>
    </div>

    <div class="col-md-10">
        <div class="row">
            <div class="col-md-3">
                <span class="text-primary">${post.userFirstName} ${post.userLastName}</span>
            </div>

            <div class="col-md-2">
                <span class="text-muted">@${post.userName}</span>
            </div>

            <div class="col-md-3 col-md-offset-1">
                <span class="text-muted"><g:formatDate format="dd/MMM/yy hh:mm"
                                                       date="${post.postDate}"/></span>
            </div>

            <div class="col-md-2 col-md-offset-1 topicName">
                <g:link name="topicClickLnk" controller="topic" action="show"
                        params="[id: post.topicID]" data-toggle="tooltip" data-placement="right"
                        title="${post.topicName}">${post.topicName}</g:link>

            </div>
        </div>

        <div class="row" style="margin-top: 10px">

            <div class="col-md-12 text-justify">
                ${post.description}

            </div>
        </div>

        <div class="row">

            <div class="col-sm-8 pull-right">
                <ul class="list-inline text-right">
                    <li><ls:resourceType resourceID="${post.resourceID}" url="${post.url}"
                                         filePath="${post.filePath}"/></li>
                    <li><ls:markRead isRead="${post.isRead}" id="${post.resourceID}"/></li>
                    <li><g:link controller="resource" action="show" class="text-right" style="display: block"
                                params="[id: post.resourceID]">View Post</g:link></li>
                </ul>
            </div>
        </div>

    </div>
    %{--<g:link controller="resource" action="show" class="pull-right" params="[id: post.resourceID]">View Post</g:link>--}%


</div>
