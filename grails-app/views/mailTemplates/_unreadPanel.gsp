<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-3">
                ${user} your unread Items
            </div>
        </div>
    </div>

    <div class="panel-body" style="background-color: #4cae4c">
        <g:each in="${unreadResources}" var="readingItem">
            <g:render template="/mailTemplates/unreadResources" model="[post: readingItem]"/>
        </g:each>

    </div>

    <div class="panel-footer" style="margin-left: 350px">
        <g:link controller="user" action="index" absolute="true" base="http://localhost:8080">View More</g:link>

    </div>
</div>
</body>