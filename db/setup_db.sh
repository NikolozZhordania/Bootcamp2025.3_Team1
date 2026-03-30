#!/bin/bash

DB_NAME=tbc_map.db

rm -f $DB_NAME

sqlite3 tbc_map.db < db/schema.sql
sqlite3 tbc_map.db < db/seed.sql

echo "SQLite database tbc_map.db created in project root."