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

heiferVersion="0.0.7-SNAPSHOT"
group=
artifact=
name=
description=
package=
# 17, 11, 8
java=
# Cloud, Boot
architecture=

funStart() {
    clear
    funTitle
    funProjectMetadata
    loading
    funEchoProjectMetadata
}

funTitle() {
    echo
    echo "Welcome to heifer initializr. You don't have many choices here"
    echo "Heifer version: $heiferVersion"
    echo
    loading
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

    if [ -z "${description}" ]; then
        echo "description default: $artifact project for Heifer"
        read -p "Description: " description
        if [ -z "${description}" ]; then
            description="$artifact project for Heifer"
        fi
    fi

    if [ -z "${package}" ]; then
        echo "Package name default: $group.$artifact"
        read -p "Package name: " package
        if [ -z "${package}" ]; then
            package="$group.$artifact"
        fi
    fi

    #    echo "Java version Optional: 17* 11 8"
    #    read -p "Java version: " java
    #    if [ -z "${java}" ]; then
    #        java="17"
    #    fi

    if [ -z "${java}" ]; then
        PS3='Java version optional: '
        options=("Java 17 default" "Java 11" "Java 8")
        select opt in "${options[@]}"; do
            case $opt in
            "Java 17 default")
                java="17"
                break
                ;;
            "Java 11")
                echo "you chose choice 2"
                java="17"
                break
                ;;
            "Java 8")
                echo "you chose choice 3"
                java="8"
                break
                ;;
            *)
                java="17"
                break
                ;;
            esac
        done
    fi

    #    echo "Application architecture Optional: Cloud* Boot"
    #    read -p "Application architecture: " architecture
    #    if [ -z "${architecture}" ]; then
    #        architecture="Cloud"

    if [ -z "${architecture}" ]; then
        PS3='Application architecture optional: '
        options=("Cloud default" "Boot")
        select opt in "${options[@]}"; do
            case $opt in
            "Cloud default")
                architecture="Cloud"
                break
                ;;
            "Boot")
                architecture="Boot"
                break
                ;;
            *)
                architecture="Cloud"
                break
                ;;
            esac
        done
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
    echo "Description: $description"
    echo "Package name: $package"
    echo "Java version: $java"
    echo "Application architecture: $architecture"

}

loading() {
    mark=''
    for ((ratio = 0; ${ratio} <= 100; ratio += 5)); do
        sleep 0.1
        printf "loading: [%-40s]%d%%\r" "${mark}" "${ratio}"
        mark="##${mark}"
    done
}

funStart
