#!/bin/bash
# Exit immediately if a command exits with a non-zero status
set -e
# Treat unset variables as an error when substituting
set -u

function create_databases() {
    database=$1
    echo "Creating database '$database'"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
      CREATE DATABASE $database;
      GRANT ALL PRIVILEGES ON DATABASE $database TO admin;
EOSQL
}

function create_races_table() {
    database=$1
    echo "Creating 'TRAIL_RACES' table in database '$database'"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$database" <<-EOSQL
      CREATE TABLE TRAIL_RACES (
        uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        firstName VARCHAR(255) NOT NULL,
        lastName VARCHAR(255) NOT NULL,
        club VARCHAR(255),
        distance VARCHAR(20) CHECK (distance IN ('5k', '10k', 'half-marathon', 'marathon')) NOT NULL
      );
EOSQL
}

function create_user_info_table() {
    database=$1
    echo "Creating table 'USER_INFO' in database '$database'"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$database" <<-EOSQL
      CREATE TABLE USER_INFO (
        id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        name VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        roles VARCHAR(255) NOT NULL
      );
EOSQL
}

# Create "users" database
create_databases "users"

# Create "races" database
create_databases "races"

# Create "trail_races" table in "races" database
create_races_table "races"

# Create "user_info" table in "users" database
create_user_info_table "users"

# Import CSV data to the users database table user_info
psql --username admin --dbname users -c "\copy user_info (email, name, password, roles) FROM '/home/user_info.csv' WITH (FORMAT CSV, DELIMITER ',')"

echo "Databases and tables created!"
echo "User data imported!"
