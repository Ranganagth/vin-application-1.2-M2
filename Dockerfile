FROM openjdk:8 
EXPOSE 8082
ADD target/M2_Service_v1_2.jar M2_Service_v1_2.jar
ENTRYPOINT [ "java","-jar","/M2_Service_v1_2.jar"] 