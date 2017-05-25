<div class="row" style="border-bottom: 1px solid #000;margin-top: 10px">

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
                ${post.topicName}

            </div>
        </div>

        <div class="row" style="margin-top: 10px">

            <div class="col-md-12 text-justify">
                ${post.description}

            </div>
            <g:link controller="resource" action="show" params="[id: post.resourceID]" absolute="true" base="http://localhost:8080">Click here</g:link>
        </div>

    </div>
</div>

