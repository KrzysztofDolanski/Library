FROM postgres:14.1-alpine

RUN useradd -U -l -d /lib lib

COPY --chown=lib:lib data.sql /data.sql

RUN psql -U postgres -f data.sql

USER lib