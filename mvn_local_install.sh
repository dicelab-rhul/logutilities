#!/bin/bash

source common.sh

if [ ! -f ${MAVEN_DEP_JAR} ]; then
	echo "${MAVEN_DEP_JAR} does not exist. Cannot install it."
	echo "Aborting..."
	exit 1
fi

if [ -d ${DEP_DIR} ]; then
	rm -rf ${DEP_DIR}/*
else
	mkdir -p ${DEP_DIR}
fi

mvn install:install-file -Dfile=${JAR_FULL_PATH} -DgroupId=${GROUP_ID} -DartifactId=${ARTIFACT_ID} -Dversion=${VERSION} -Dpackaging=jar

if [ ! -f ${MAVEN_INSTALLED_JAR_PATH} ]; then
	echo "Installation failed."
	
	exit 1
else
	echo "Installation success."
fi