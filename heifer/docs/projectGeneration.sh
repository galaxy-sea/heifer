#!/bin/bash
#
# Copyright 2021-2022 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

group=
artifact=
name=
package=
description=
# 17, 11, 1.8
java=
# Cloud, Boot
archetype=

# ============
heiferVersion="0.0.7-SNAPSHOT"
BootVersion="0.0.7-SNAPSHOT"
CloudVersion="0.0.7-SNAPSHOT"
# ============


funStart() {
    clear
    funTitle
    funProjectMetadata
    funLoading
    funEchoProjectMetadata
    funGenerateProject
}

funTitle() {
    echo
    echo "Welcome to heifer initializr. You don't have many choices here"
    echo "Heifer version: $heiferVersion"
    echo
    funLoading
    echo "Please enter project metadata"
    echo
}
funProjectMetadata() {
    if [ -z "${group}" ]; then
        echo "Group default: com.example"
        read -p "Group: " group
        if [ -z "${group}" ]; then
            group="com.example"
        fi
    fi

    if [ -z "${artifact}" ]; then
        echo "Artifact default: demo"
        read -p "Artifact: " artifact
        if [ -z "${artifact}" ]; then
            artifact="demo"
        fi
    fi

    if [ -z "${name}" ]; then
        echo "Name default: $artifact"
        read -p "Name: " name
        if [ -z "${name}" ]; then
            name=$artifact
        fi
    fi

    if [ -z "${package}" ]; then
        echo "Package name default: $group.$artifact"
        read -p "Package name: " package
        if [ -z "${package}" ]; then
            package="$group.$artifact"
        fi
    fi

    if [ -z "${description}" ]; then
        echo "description default: $artifact project for Heifer"
        read -p "Description: " description
        if [ -z "${description}" ]; then
            description="$artifact project for Heifer"
        fi
    fi

    if [ -z "${java}" ]; then
        echo "Java version Optional: 17* 11 8"
        read -p "Java version: " java
        case $java in
        11) java="11" ;;
        8) java="1.8" ;;
        *) java="17" ;;
        esac
    fi

    if [ -z "${archetype}" ]; then
        echo "Application archetype Optional: Cloud* Boot"
        read -p "Application archetype: " archetype
        case $archetype in
        "Boot") archetype="Boot" ;;
        *) archetype="Cloud" ;;
        esac
    fi
}

funEchoProjectMetadata() {

    sleep 0.1
    echo
    echo "Please confirm project metadata info"
    echo

    echo "Group: $group"
    echo "Artifact: $artifact"
    echo "Name: $name"
    echo "Package name: $package"
    echo "Description: $description"
    echo "Java version: $java"
    echo "Application archetype: $archetype"

}

funLoading() {
    mark=''
    for ((ratio = 0; ${ratio} <= 100; ratio += 5)); do
        sleep 0.1
        printf "loading: [%-40s]%d%%\r" "${mark}" "${ratio}"
        mark="##${mark}"
    done
}

funGenerateProject() {
    nameDirectory=${package//.//}
    if [ "$archetype" = "Boot" ]; then
        funCreateBootDirectory
        funCreateBootFile
    else
        funCreateCloudDirectory
        funCreateCloudFile
    fi
}

funCreateBootDirectory() {
    mkdir -vp $name/src/{main,test}/{java/$nameDirectory,resources}
}

funCreateCloudDirectory() {
    mkdir -vp $name/{$name-api,$name-web,$name-start}/src/{main,test}/{java/$nameDirectory,resources}
}
funCreateBootFile(){

}
funCreateCloudFile(){

}

funStart
