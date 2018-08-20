
# Ephemeral chat service
----
### Technologies/tool used
- Java 1.8
- Maven
- SpringBoot (spring-web, spring-redis-data, spring-jpa, H2 database)
- Redis

### Instructions to get this app up and running
##### Running Redis server on your machine
You can install the redis server on windows from [here](https://github.com/MicrosoftArchive/redis/releases)

If you have a linux machine you can install it from the [official download page](https://redis.io/download)

Once done start the redis server. Note the port number. By default it should be 6379. If it is something else, please update the project's application properties with the port number displayed.

##### Getting project running
Clone the git repo into your local machine. Either use a git tool or you can use command line options.

```sh
git clone -b master <repo-url>
```
If you have eclipse, IntelliJ IDEA, Spring tool suite or any java compatible IDE installed, import the project into a fresh or existing workspace. 

In your preferred IDE, run the project and provide spring-boot:run in the goals section and run the project.

If you do not have an IDE compatible for java, then install [maven](https://maven.apache.org/download.cgi) on your machine. Once completed, run the following command on your terminal or command prompt.
```sh
mvn spring-boot:run
```

The project should start without any errors. That's it, you are ready to test the service using any client that can make rest api calls. 

Request body for post should be of application/json format. So ensure that use request header Content-type : application/json while testing.

If any errors are encountered during API calls please look at application logs for detailed stack trace. They are not propagated to the API response. You only see an abstract response of why it failed.

##### Decisions made for this solution  


I used spring boot since it is very easy to bootstrap a project and get up and running. It also has good integration with redis.

For cold storage, I had to choose something in-memory without needing any configuration settings, since I didn't know what database you folks have already configured in your machines. So I chose H2 for this which comes bootstrapped with the deployable jar and doesn't need any independent/cloud database installation.

For hot storage, I chose Redis since it is the fastest open source, **in-memory** database with very complex and robust functionalities available accessible via many different languages. There are other options like Memcache and ehcache which can be used similarly. I did think of using ehcache which comes packaged directly in jar, but that would tightly couple the cache with the app and horizontal scalability wouldn't work since one instance of the app wouldn't know about the data stored on the other instance.

Regarding implementation of service methods

**Post /chat** : Stores in redis and parallelly stores in cold storage database
**Get /chat/:id** : Gets data only from cold storage since most databases have good performance on querying single row on an id. Be it NoSQL or relational.
**Get /chats/:username** : gets data from redis and expires the messages as per requirement. There is no requirement to touch the cold storage

  - Type some Markdown on the left
  - See HTML in the right
  - Magic


##### Limitations of implementation  


Since I used H2 in memory database that starts and dies with the application, when you create messages the auto-generated id would refresh from 0 after server start whereas the redis cache will still contain the ids from the previous run. So to compensate for the limitation you will need to restart redis before restarting app.

I haven't configured the redis memory settings. If millions of messages are sent at the same time, the memory can get consumed and performance degradation and/or service issues might occur. For that I would need volume information to set the appropriate memory and set an appropriate policy.

##### What would I do if you had more time/How you would scale it in the future?

    Write test cases;)
    Containerize with something like Docker so I don't have to manually install Redis cache. 
    Package the app into a Docker image
    Upload the image to a registry
    Create a container cluster like Kubernetes Engine cluster
    Deploy image to the cluster
    Expose app to the Internet
    Scale up deployment
