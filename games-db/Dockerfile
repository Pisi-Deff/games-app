FROM postgres

ENV POSTGRES_DB games
ENV POSTGRES_USER gameuser
ENV POSTGRES_PASSWORD gamepass

COPY install-extensions.sh /docker-entrypoint-initdb.d/

EXPOSE 5432/tcp
