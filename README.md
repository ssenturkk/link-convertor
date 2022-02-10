# Link Converter
Web service that helps others to convert Trendyol.com links between mobile and web applications. Web applicatons use URLs and mobile applications use deeplinks. Both applications use links to redirect specific locations inside applications. When you want to redirect across applications, you should convert URLs to deeplinks or deeplinks to URLs
## Running with Docker
 - run `./run.sh file`</br>
 - Application will start at port 8080</br>
## Thechnical Info 
Redis technology was used in this project. When a request is posted to convert the link, Firstly it is checked whether the converted link exists in redis.        
 - If the converted value exists in redis -> return redis cache converted link</br>
 - Else if -> the conversion process starts.
 - The conversion is completed and saved in the database.
 - Then redis is updated and the converted link value is returned
 
 Here, it is aimed to speed up the application by using redis. In this way, both the database is not occupied unnecessarily and the resources are not consumed too much.
