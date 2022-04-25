FROM openjdk:11
LABEL maintainer="auditmanagement.checklist"
ADD target/audit-management-checklist-0.0.1-SNAPSHOT.jar audit-management-checklist.jar
ENTRYPOINT ["java","-jar","audit-management-checklist.jar"]