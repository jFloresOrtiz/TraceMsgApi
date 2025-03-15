INDICACIONES PARA PODER EJECUTAR ESTE PROYECTO EN LOCAL.
1. MongoDB se esta usando en la nube de MongoDB Atlas Cloud, por eso que la cadena de coneccion esta declarada en el archivo de build.gradle.
2. Se esta usando la version de JAVA 17 y SpringBoot 3.4.3, Gradle, spring Data Reactive Mongo, Spring Webflux, SpringActuator y Prometheus.
3. No se esta usando el logging log4j2 por problemas de compatibilidad con el log determinado que viene con webFlux (spring-boot-starter-logging).
4. Y por ultimo tambien estoy adjuntando una coleccion de postman con las 2 apis solicitadas.
GRACIAS. 
