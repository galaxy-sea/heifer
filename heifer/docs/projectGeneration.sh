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
bootVersion="0.0.7-SNAPSHOT"
cloudVersion="0.0.7-SNAPSHOT"
# ============

funStart() {
    clear
    funTitle
    funLoading
    echo
    sleep 0.2
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
}
funProjectMetadata() {

    echo "Please enter project metadata"
    echo

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
    echo
    echo
}

funEchoProjectMetadata() {

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
    applicationName=`echo $name | perl -pe 's/(^|-|_)(\w)/\U\2/g'`

    if [ "$archetype" = "Boot" ]; then
        funCreateBootDirectory
        funCreateBootFile
    else
        funCreateCloudDirectory
        #        funCreateCloudFile
    fi
}

funCreateBootDirectory() {
    mkdir -p $name/src/{main,test}/{java/$nameDirectory,resources}
}

funCreateCloudDirectory() {
    mkdir -p $name/{$name-api,$name-web,$name-start}/src/{main,test}/{java/$nameDirectory,resources}
}
funCreateBootFile() {
    string="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHByb2plY3QgeG1sbnM9Imh0dHA6Ly9tYXZlbi5hcGFjaGUub3JnL1BPTS80LjAuMCIgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIKICAgIHhzaTpzY2hlbWFMb2NhdGlvbj0iaHR0cDovL21hdmVuLmFwYWNoZS5vcmcvUE9NLzQuMC4wIGh0dHBzOi8vbWF2ZW4uYXBhY2hlLm9yZy94c2QvbWF2ZW4tNC4wLjAueHNkIj4KICAgIDxtb2RlbFZlcnNpb24+NC4wLjA8L21vZGVsVmVyc2lvbj4KICAgIDxncm91cElkPiRncm91cElkPC9ncm91cElkPgogICAgPGFydGlmYWN0SWQ+JGFydGlmYWN0SWQ8L2FydGlmYWN0SWQ+CiAgICA8dmVyc2lvbj4wLjAuMS1TTkFQU0hPVDwvdmVyc2lvbj4KICAgIDxuYW1lPiRuYW1lPC9uYW1lPgogICAgPGRlc2NyaXB0aW9uPiRkZXNjcmlwdGlvbjwvZGVzY3JpcHRpb24+CiAgICA8cHJvcGVydGllcz4KICAgICAgICA8amF2YS52ZXJzaW9uPiRqYXZhPC9qYXZhLnZlcnNpb24+CiAgICAgICAgPG1hdmVuLmNvbXBpbGVyLnNvdXJjZT4ke2phdmEudmVyc2lvbn08L21hdmVuLmNvbXBpbGVyLnNvdXJjZT4KICAgICAgICA8bWF2ZW4uY29tcGlsZXIudGFyZ2V0PiR7amF2YS52ZXJzaW9ufTwvbWF2ZW4uY29tcGlsZXIudGFyZ2V0PgogICAgICAgIDxwcm9qZWN0LmJ1aWxkLnNvdXJjZUVuY29kaW5nPlVURi04PC9wcm9qZWN0LmJ1aWxkLnNvdXJjZUVuY29kaW5nPgogICAgICAgIDxwcm9qZWN0LnJlcG9ydGluZy5vdXRwdXRFbmNvZGluZz5VVEYtODwvcHJvamVjdC5yZXBvcnRpbmcub3V0cHV0RW5jb2Rpbmc+CiAgICAgICAgPHNwcmluZy1ib290LnZlcnNpb24+Mi4zLjcuUkVMRUFTRTwvc3ByaW5nLWJvb3QudmVyc2lvbj4KICAgIDwvcHJvcGVydGllcz4KICAgIDxkZXBlbmRlbmNpZXM+CiAgICAgICAgPGRlcGVuZGVuY3k+CiAgICAgICAgICAgIDxncm91cElkPnBsdXMud2NqPC9ncm91cElkPgogICAgICAgICAgICA8YXJ0aWZhY3RJZD5oZWlmZXItYm9vdDwvYXJ0aWZhY3RJZD4KICAgICAgICA8L2RlcGVuZGVuY3k+CiAgICAgICAgPGRlcGVuZGVuY3k+CiAgICAgICAgICAgIDxncm91cElkPm9yZy5zcHJpbmdmcmFtZXdvcmsuYm9vdDwvZ3JvdXBJZD4KICAgICAgICAgICAgPGFydGlmYWN0SWQ+c3ByaW5nLWJvb3Qtc3RhcnRlci10ZXN0PC9hcnRpZmFjdElkPgogICAgICAgICAgICA8c2NvcGU+dGVzdDwvc2NvcGU+CiAgICAgICAgICAgIDxleGNsdXNpb25zPgogICAgICAgICAgICAgICAgPGV4Y2x1c2lvbj4KICAgICAgICAgICAgICAgICAgICA8Z3JvdXBJZD5vcmcuanVuaXQudmludGFnZTwvZ3JvdXBJZD4KICAgICAgICAgICAgICAgICAgICA8YXJ0aWZhY3RJZD5qdW5pdC12aW50YWdlLWVuZ2luZTwvYXJ0aWZhY3RJZD4KICAgICAgICAgICAgICAgIDwvZXhjbHVzaW9uPgogICAgICAgICAgICA8L2V4Y2x1c2lvbnM+CiAgICAgICAgPC9kZXBlbmRlbmN5PgogICAgPC9kZXBlbmRlbmNpZXM+CiAgICA8ZGVwZW5kZW5jeU1hbmFnZW1lbnQ+CiAgICAgICAgPGRlcGVuZGVuY2llcz4KICAgICAgICAgICAgPGRlcGVuZGVuY3k+CiAgICAgICAgICAgICAgICA8Z3JvdXBJZD5wbHVzLndjajwvZ3JvdXBJZD4KICAgICAgICAgICAgICAgIDxhcnRpZmFjdElkPmhlaWZlci1kZXBlbmRlbmNpZXM8L2FydGlmYWN0SWQ+CiAgICAgICAgICAgICAgICA8dmVyc2lvbj4kaGVpZmVyVmVyc2lvbjwvdmVyc2lvbj4KICAgICAgICAgICAgICAgIDx0eXBlPnBvbTwvdHlwZT4KICAgICAgICAgICAgICAgIDxzY29wZT5pbXBvcnQ8L3Njb3BlPgogICAgICAgICAgICA8L2RlcGVuZGVuY3k+CiAgICAgICAgPC9kZXBlbmRlbmNpZXM+CiAgICA8L2RlcGVuZGVuY3lNYW5hZ2VtZW50PgogICAgPGJ1aWxkPgogICAgICAgIDxwbHVnaW5zPgogICAgICAgICAgICA8cGx1Z2luPgogICAgICAgICAgICAgICAgPGdyb3VwSWQ+b3JnLmFwYWNoZS5tYXZlbi5wbHVnaW5zPC9ncm91cElkPgogICAgICAgICAgICAgICAgPGFydGlmYWN0SWQ+bWF2ZW4tY29tcGlsZXItcGx1Z2luPC9hcnRpZmFjdElkPgogICAgICAgICAgICAgICAgPHZlcnNpb24+My44LjE8L3ZlcnNpb24+CiAgICAgICAgICAgICAgICA8Y29uZmlndXJhdGlvbj4KICAgICAgICAgICAgICAgICAgICA8c291cmNlPjEuODwvc291cmNlPgogICAgICAgICAgICAgICAgICAgIDx0YXJnZXQ+MS44PC90YXJnZXQ+CiAgICAgICAgICAgICAgICAgICAgPGVuY29kaW5nPlVURi04PC9lbmNvZGluZz4KICAgICAgICAgICAgICAgIDwvY29uZmlndXJhdGlvbj4KICAgICAgICAgICAgPC9wbHVnaW4+CiAgICAgICAgICAgIDxwbHVnaW4+CiAgICAgICAgICAgICAgICA8Z3JvdXBJZD5vcmcuc3ByaW5nZnJhbWV3b3JrLmJvb3Q8L2dyb3VwSWQ+CiAgICAgICAgICAgICAgICA8YXJ0aWZhY3RJZD5zcHJpbmctYm9vdC1tYXZlbi1wbHVnaW48L2FydGlmYWN0SWQ+CiAgICAgICAgICAgICAgICA8dmVyc2lvbj4kYm9vdFZlcnNpb248L3ZlcnNpb24+CiAgICAgICAgICAgICAgICA8Y29uZmlndXJhdGlvbj4KICAgICAgICAgICAgICAgICAgICA8bWFpbkNsYXNzPiRwYWNrYWdlLiRBcHBsaWNhdGlvbk5hbWVBcHBsaWNhdGlvbjwvbWFpbkNsYXNzPgogICAgICAgICAgICAgICAgPC9jb25maWd1cmF0aW9uPgogICAgICAgICAgICAgICAgPGV4ZWN1dGlvbnM+CiAgICAgICAgICAgICAgICAgICAgPGV4ZWN1dGlvbj4KICAgICAgICAgICAgICAgICAgICAgICAgPGlkPnJlcGFja2FnZTwvaWQ+CiAgICAgICAgICAgICAgICAgICAgICAgIDxnb2Fscz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxnb2FsPnJlcGFja2FnZTwvZ29hbD4KICAgICAgICAgICAgICAgICAgICAgICAgPC9nb2Fscz4KICAgICAgICAgICAgICAgICAgICA8L2V4ZWN1dGlvbj4KICAgICAgICAgICAgICAgIDwvZXhlY3V0aW9ucz4KICAgICAgICAgICAgPC9wbHVnaW4+CiAgICAgICAgPC9wbHVnaW5zPgogICAgPC9idWlsZD4KPC9wcm9qZWN0Pg=="
    echo $string | base64 -d > pom.xml
    sed -i '' "s/\$groupId/$group/g" pom.xml
    sed -i '' "s/\$artifactId/$artifact/g" pom.xml
    sed -i '' "s/\$name/$name/g" pom.xml
    sed -i '' "s/\$description/$description/g" pom.xml
    sed -i '' "s/\$java/$java/g" pom.xml
    sed -i '' "s/\$heiferVersion/$heiferVersion/g" pom.xml
    sed -i '' "s/\$bootVersion/$bootVersion/g" pom.xml
    sed -i '' "s/\$package/$package/g" pom.xml
    sed -i '' "s/\$ApplicationName/$applicationName/g" pom.xml
}
#funCreateCloudFile(){
#
#}

funStart
