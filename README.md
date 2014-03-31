IssueTracker
============

A Java swing application to view all the open issues on a given github repo. 

The swing application presents a scrollable list of all open issues sorted by updated time. Users can also click on a given issue to look at all the comments posted on the issue.

Core Components:

ApplicationModel: GiHub API requests that return multiple items are paginated to 30 items by default. One could specify further pages with the ?page parameter. This model class contains accessors to retrive the list of issues by page. 

ApplicationView: Contains the view definition to display the list of issues. 

ApplicationController: ApplicationController glues the ApplicationModel and ApplicationView. In order to keep the UI responsive as the number of issues can be high, the controller fetches the first 30 results on the foreground and the remaining in the background.

CommunicationService: Utility class for HttpRequests/Responses.
              NOTE: Please change the Auth_Token in this class to avoid auth errors.
              
Parser: Utility class to parse JSON responses and convert them into internal objects.

