FROM postgres:14.1-alpine

RUN useradd -U -l -d /lib lib

COPY --chown=lib:lib db_fill.sql /db_fill.sql

RUN psql -U postgres -f db_fill.sql

USER lib