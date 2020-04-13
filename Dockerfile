From openjdk:11
copy ./target/financial-transaction-proxy-0.0.1-SNAPSHOT.war financial-transaction-proxy-0.0.1-SNAPSHOT.war
CMD ["java","-jar","financial-transaction-proxy-0.0.1-SNAPSHOT.war"]
