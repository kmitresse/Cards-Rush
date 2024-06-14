#!/bin/bash

databases=("db_test" "db_prod")

template="./mysql/template.sql"
output="./mysql/init/init.sql"

> "$output"
for database in "${databases[@]}"; do
  sed "s/\${database}/$database/g" "$template" >> "$output"
  echo "" >> "$output"
done