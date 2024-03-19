FROM mysql:8.0.3

COPY authentication-service/src/main/resources/init-auth-service-db.sql /docker-entrypoint-initdb.d/init-auth-service-db.sql
COPY contribute-service/src/main/resources/init-contribute-service-db.sql /docker-entrypoint-initdb.d/init-contribute-service-db.sql
COPY user-profile-service/src/main/resources/init-user-profile-db.sql /docker-entrypoint-initdb.d/init-user-profile-db.sql
