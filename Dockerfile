FROM eclipse-temurin:11
LABEL maintainer="lfernando.rios@udea.edu.co"
VOLUME /main-app
ADD build/libs/apiDepositosFinancieros-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar","/app.jar"]