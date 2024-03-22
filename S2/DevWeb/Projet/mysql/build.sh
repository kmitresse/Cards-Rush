#!/bin/bash

databases=("db_test" "db_prod")

template="template.sql"
output="init/init.sql"

> "$output"
for database in "${databases[@]}"; do
  sed "s/\${database}/$database/g" "$template" >> "$output"
  echo "" >> "$output"
done