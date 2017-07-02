#!/bin/bash

if [ "$#" -ne 5 ]; then
        echo "Usage: createdb <mysql|postgresql> <database name> <db User> <dbPassword> <init script location>"
        exit 1
elif [[  "$1" != "mysql"  &&  "$1" != "postgresql"  ]]; then
        echo "Usage: createdb <mysql|postgresql> <database name> <db User> <dbPassword> <init script location>"
        exit 1
#elif [ ! -f "$5" ]; then
#        echo "File $5 does not exist"
#        exit 1
fi

if [ "$1" = "postgresql" ]; then
    #echo "postgresql database selected."
    if psql -lqt | cut -d \| -f 1 | grep -qw "$2"; then
        echo "database $2 already exists."
    else
        #echo "database $2 does not exist. creating database"
        createdb "$2"
        if [ "$?" -eq 0 ]; then
                echo "$2 database created successfully"
                #psql $2 < $5
        else
                echo "Failed to create $2 database"
        fi
    fi
else
    #echo "mysql database selected."
    RESULT=`mysqlshow --user=$3 --password=$3 $2| grep "Database: $2"`
    echo $RESULT
    if [ "$RESULT" == "Database: $2" ]; then
        echo "database $2 already exists."
    else
        echo "database $2 does not exists."
        mysql --user=$3 --password=$4 -e "create database $2"
        #mysql --user=$3 --password=$4 $2 < $5
    fi
fi