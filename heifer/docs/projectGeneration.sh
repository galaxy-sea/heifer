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

group=plus.wcj
artifact=heifer-test
name=heifer-test
package=plus.wcj.heifer.test
description=heifer
# 17, 11, 1.8
java=17
# Cloud, Boot
archetype=Cloud

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
        funCreateFile $bootPomXml $name/pom.xml
        funCreateFile $javaMain $name/src/main/java/$nameDirectory/${applicationName}Application.java
        funCreateFile $appYml $name/src/main/resources/application.yml
    else
        funCreateCloudDirectory
        funCreateFile $cloudPomXml $name/pom.xml
        funCreateFile $startPomXml $name/$name-start/pom.xml
    fi
}

funCreateBootDirectory() {
    mkdir -p $name/src/{main,test}/{java/$nameDirectory,resources}
}

funCreateCloudDirectory() {
    mkdir -p $name/{$name-api,$name-web,$name-start}/src/{main,test}/{java/$nameDirectory,resources}
}
funCreateFile() {
    echo $1 | base64 -d > $2
    sed -i '' "s/\$groupId/$group/g" $2
    sed -i '' "s/\$artifactId/$artifact/g" $2
    sed -i '' "s/\$name/$name/g" $2
    sed -i '' "s/\$description/$description/g" $2
    sed -i '' "s/\$java/$java/g" $2
    sed -i '' "s/\$heiferVersion/$heiferVersion/g" $2
    sed -i '' "s/\$bootVersion/$bootVersion/g" $2
    sed -i '' "s/\$package/$package/g" $2
    sed -i '' "s/\$ApplicationName/$applicationName/g" $2
}

bootPomXml="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHByb2plY3QgeG1sbnM9Imh0dHA6Ly9tYXZlbi5hcGFjaGUub3JnL1BPTS80LjAuMCIgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIKICAgIHhzaTpzY2hlbWFMb2NhdGlvbj0iaHR0cDovL21hdmVuLmFwYWNoZS5vcmcvUE9NLzQuMC4wIGh0dHBzOi8vbWF2ZW4uYXBhY2hlLm9yZy94c2QvbWF2ZW4tNC4wLjAueHNkIj4KICAgIDxtb2RlbFZlcnNpb24+NC4wLjA8L21vZGVsVmVyc2lvbj4KICAgIDxncm91cElkPiRncm91cElkPC9ncm91cElkPgogICAgPGFydGlmYWN0SWQ+JGFydGlmYWN0SWQ8L2FydGlmYWN0SWQ+CiAgICA8dmVyc2lvbj4wLjAuMS1TTkFQU0hPVDwvdmVyc2lvbj4KICAgIDxuYW1lPiRuYW1lPC9uYW1lPgogICAgPGRlc2NyaXB0aW9uPiRkZXNjcmlwdGlvbjwvZGVzY3JpcHRpb24+CiAgICA8cHJvcGVydGllcz4KICAgICAgICA8amF2YS52ZXJzaW9uPiRqYXZhPC9qYXZhLnZlcnNpb24+CiAgICAgICAgPG1hdmVuLmNvbXBpbGVyLnNvdXJjZT4ke2phdmEudmVyc2lvbn08L21hdmVuLmNvbXBpbGVyLnNvdXJjZT4KICAgICAgICA8bWF2ZW4uY29tcGlsZXIudGFyZ2V0PiR7amF2YS52ZXJzaW9ufTwvbWF2ZW4uY29tcGlsZXIudGFyZ2V0PgogICAgICAgIDxwcm9qZWN0LmJ1aWxkLnNvdXJjZUVuY29kaW5nPlVURi04PC9wcm9qZWN0LmJ1aWxkLnNvdXJjZUVuY29kaW5nPgogICAgICAgIDxwcm9qZWN0LnJlcG9ydGluZy5vdXRwdXRFbmNvZGluZz5VVEYtODwvcHJvamVjdC5yZXBvcnRpbmcub3V0cHV0RW5jb2Rpbmc+CiAgICAgICAgPHNwcmluZy1ib290LnZlcnNpb24+JGJvb3RWZXJzaW9uPC9zcHJpbmctYm9vdC52ZXJzaW9uPgogICAgPC9wcm9wZXJ0aWVzPgogICAgPGRlcGVuZGVuY2llcz4KICAgICAgICA8ZGVwZW5kZW5jeT4KICAgICAgICAgICAgPGdyb3VwSWQ+cGx1cy53Y2o8L2dyb3VwSWQ+CiAgICAgICAgICAgIDxhcnRpZmFjdElkPmhlaWZlci1ib290PC9hcnRpZmFjdElkPgogICAgICAgIDwvZGVwZW5kZW5jeT4KICAgICAgICA8ZGVwZW5kZW5jeT4KICAgICAgICAgICAgPGdyb3VwSWQ+b3JnLnNwcmluZ2ZyYW1ld29yay5ib290PC9ncm91cElkPgogICAgICAgICAgICA8YXJ0aWZhY3RJZD5zcHJpbmctYm9vdC1zdGFydGVyLXRlc3Q8L2FydGlmYWN0SWQ+CiAgICAgICAgICAgIDxzY29wZT50ZXN0PC9zY29wZT4KICAgICAgICAgICAgPGV4Y2x1c2lvbnM+CiAgICAgICAgICAgICAgICA8ZXhjbHVzaW9uPgogICAgICAgICAgICAgICAgICAgIDxncm91cElkPm9yZy5qdW5pdC52aW50YWdlPC9ncm91cElkPgogICAgICAgICAgICAgICAgICAgIDxhcnRpZmFjdElkPmp1bml0LXZpbnRhZ2UtZW5naW5lPC9hcnRpZmFjdElkPgogICAgICAgICAgICAgICAgPC9leGNsdXNpb24+CiAgICAgICAgICAgIDwvZXhjbHVzaW9ucz4KICAgICAgICA8L2RlcGVuZGVuY3k+CiAgICA8L2RlcGVuZGVuY2llcz4KICAgIDxkZXBlbmRlbmN5TWFuYWdlbWVudD4KICAgICAgICA8ZGVwZW5kZW5jaWVzPgogICAgICAgICAgICA8ZGVwZW5kZW5jeT4KICAgICAgICAgICAgICAgIDxncm91cElkPnBsdXMud2NqPC9ncm91cElkPgogICAgICAgICAgICAgICAgPGFydGlmYWN0SWQ+aGVpZmVyLWRlcGVuZGVuY2llczwvYXJ0aWZhY3RJZD4KICAgICAgICAgICAgICAgIDx2ZXJzaW9uPiRoZWlmZXJWZXJzaW9uPC92ZXJzaW9uPgogICAgICAgICAgICAgICAgPHR5cGU+cG9tPC90eXBlPgogICAgICAgICAgICAgICAgPHNjb3BlPmltcG9ydDwvc2NvcGU+CiAgICAgICAgICAgIDwvZGVwZW5kZW5jeT4KICAgICAgICA8L2RlcGVuZGVuY2llcz4KICAgIDwvZGVwZW5kZW5jeU1hbmFnZW1lbnQ+CiAgICA8YnVpbGQ+CiAgICAgICAgPHBsdWdpbnM+CiAgICAgICAgICAgIDxwbHVnaW4+CiAgICAgICAgICAgICAgICA8Z3JvdXBJZD5vcmcuYXBhY2hlLm1hdmVuLnBsdWdpbnM8L2dyb3VwSWQ+CiAgICAgICAgICAgICAgICA8YXJ0aWZhY3RJZD5tYXZlbi1jb21waWxlci1wbHVnaW48L2FydGlmYWN0SWQ+CiAgICAgICAgICAgICAgICA8dmVyc2lvbj4zLjguMTwvdmVyc2lvbj4KICAgICAgICAgICAgPC9wbHVnaW4+CiAgICAgICAgICAgIDxwbHVnaW4+CiAgICAgICAgICAgICAgICA8Z3JvdXBJZD5vcmcuc3ByaW5nZnJhbWV3b3JrLmJvb3Q8L2dyb3VwSWQ+CiAgICAgICAgICAgICAgICA8YXJ0aWZhY3RJZD5zcHJpbmctYm9vdC1tYXZlbi1wbHVnaW48L2FydGlmYWN0SWQ+CiAgICAgICAgICAgICAgICA8dmVyc2lvbj4ke3NwcmluZy1ib290LnZlcnNpb308L3ZlcnNpb24+CiAgICAgICAgICAgICAgICA8Y29uZmlndXJhdGlvbj4KICAgICAgICAgICAgICAgICAgICA8bWFpbkNsYXNzPiRwYWNrYWdlLiRBcHBsaWNhdGlvbk5hbWVBcHBsaWNhdGlvbjwvbWFpbkNsYXNzPgogICAgICAgICAgICAgICAgPC9jb25maWd1cmF0aW9uPgogICAgICAgICAgICAgICAgPGV4ZWN1dGlvbnM+CiAgICAgICAgICAgICAgICAgICAgPGV4ZWN1dGlvbj4KICAgICAgICAgICAgICAgICAgICAgICAgPGlkPnJlcGFja2FnZTwvaWQ+CiAgICAgICAgICAgICAgICAgICAgICAgIDxnb2Fscz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxnb2FsPnJlcGFja2FnZTwvZ29hbD4KICAgICAgICAgICAgICAgICAgICAgICAgPC9nb2Fscz4KICAgICAgICAgICAgICAgICAgICA8L2V4ZWN1dGlvbj4KICAgICAgICAgICAgICAgIDwvZXhlY3V0aW9ucz4KICAgICAgICAgICAgPC9wbHVnaW4+CiAgICAgICAgPC9wbHVnaW5zPgogICAgPC9idWlsZD4KPC9wcm9qZWN0Pg=="
javaMain="cGFja2FnZSAkcGFja2FnZTsKCmltcG9ydCBvcmcuc3ByaW5nZnJhbWV3b3JrLmJvb3QuU3ByaW5nQXBwbGljYXRpb247CmltcG9ydCBvcmcuc3ByaW5nZnJhbWV3b3JrLmJvb3QuYXV0b2NvbmZpZ3VyZS5TcHJpbmdCb290QXBwbGljYXRpb247CgpAU3ByaW5nQm9vdEFwcGxpY2F0aW9uCnB1YmxpYyBjbGFzcyAkQXBwbGljYXRpb25OYW1lQXBwbGljYXRpb24gewoKICAgIHB1YmxpYyBzdGF0aWMgdm9pZCBtYWluKFN0cmluZ1tdIGFyZ3MpIHsKICAgICAgICBTcHJpbmdBcHBsaWNhdGlvbi5ydW4oJEFwcGxpY2F0aW9uTmFtZUFwcGxpY2F0aW9uLmNsYXNzLCBhcmdzKTsKICAgIH0KCn0="
appYml="c3ByaW5nOgogIGFwcGxpY2F0aW9uOgogICAgbmFtZTogJG5hbWUKc2VydmVyOgogIHBvcnQ6IDA="
cloudPomXml="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHByb2plY3QgeG1sbnM9Imh0dHA6Ly9tYXZlbi5hcGFjaGUub3JnL1BPTS80LjAuMCIgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIKICAgIHhzaTpzY2hlbWFMb2NhdGlvbj0iaHR0cDovL21hdmVuLmFwYWNoZS5vcmcvUE9NLzQuMC4wIGh0dHBzOi8vbWF2ZW4uYXBhY2hlLm9yZy94c2QvbWF2ZW4tNC4wLjAueHNkIj4KICAgIDxtb2RlbFZlcnNpb24+NC4wLjA8L21vZGVsVmVyc2lvbj4KICAgIDxncm91cElkPiRncm91cElkPC9ncm91cElkPgogICAgPGFydGlmYWN0SWQ+JGFydGlmYWN0SWQ8L2FydGlmYWN0SWQ+CiAgICA8dmVyc2lvbj4wLjAuMS1TTkFQU0hPVDwvdmVyc2lvbj4KICAgIDxwYWNrYWdpbmc+cG9tPC9wYWNrYWdpbmc+CiAgICA8bmFtZT4kbmFtZTwvbmFtZT4KICAgIDxkZXNjcmlwdGlvbj4kZGVzY3JpcHRpb248L2Rlc2NyaXB0aW9uPgogICAgPHByb3BlcnRpZXM+CiAgICAgICAgPGphdmEudmVyc2lvbj4kamF2YTwvamF2YS52ZXJzaW9uPgogICAgICAgIDxtYXZlbi5jb21waWxlci5zb3VyY2U+JHtqYXZhLnZlcnNpb259PC9tYXZlbi5jb21waWxlci5zb3VyY2U+CiAgICAgICAgPG1hdmVuLmNvbXBpbGVyLnRhcmdldD4ke2phdmEudmVyc2lvbn08L21hdmVuLmNvbXBpbGVyLnRhcmdldD4KICAgICAgICA8cHJvamVjdC5idWlsZC5zb3VyY2VFbmNvZGluZz5VVEYtODwvcHJvamVjdC5idWlsZC5zb3VyY2VFbmNvZGluZz4KICAgICAgICA8cHJvamVjdC5yZXBvcnRpbmcub3V0cHV0RW5jb2Rpbmc+VVRGLTg8L3Byb2plY3QucmVwb3J0aW5nLm91dHB1dEVuY29kaW5nPgogICAgICAgIDxzcHJpbmctYm9vdC52ZXJzaW9uPiRib290VmVyc2lvbjwvc3ByaW5nLWJvb3QudmVyc2lvbj4KICAgIDwvcHJvcGVydGllcz4KICAgIDxtb2R1bGVzPgogICAgICAgIDxtb2R1bGU+JG5hbWUtYXBpPC9tb2R1bGU+CiAgICAgICAgPG1vZHVsZT4kbmFtZS13ZWI8L21vZHVsZT4KICAgICAgICA8bW9kdWxlPiRuYW1lLXN0YXJ0PC9tb2R1bGU+CiAgICA8L21vZHVsZXM+CiAgICA8ZGVwZW5kZW5jeU1hbmFnZW1lbnQ+CiAgICAgICAgPGRlcGVuZGVuY2llcz4KICAgICAgICAgICAgPGRlcGVuZGVuY3k+CiAgICAgICAgICAgICAgICA8Z3JvdXBJZD5wbHVzLndjajwvZ3JvdXBJZD4KICAgICAgICAgICAgICAgIDxhcnRpZmFjdElkPmhlaWZlci1kZXBlbmRlbmNpZXM8L2FydGlmYWN0SWQ+CiAgICAgICAgICAgICAgICA8dmVyc2lvbj4kaGVpZmVyVmVyc2lvbjwvdmVyc2lvbj4KICAgICAgICAgICAgICAgIDx0eXBlPnBvbTwvdHlwZT4KICAgICAgICAgICAgICAgIDxzY29wZT5pbXBvcnQ8L3Njb3BlPgogICAgICAgICAgICA8L2RlcGVuZGVuY3k+CiAgICAgICAgPC9kZXBlbmRlbmNpZXM+CiAgICA8L2RlcGVuZGVuY3lNYW5hZ2VtZW50PgogICAgPGJ1aWxkPgogICAgICAgIDxwbHVnaW5zPgogICAgICAgICAgICA8cGx1Z2luPgogICAgICAgICAgICAgICAgPGdyb3VwSWQ+b3JnLmFwYWNoZS5tYXZlbi5wbHVnaW5zPC9ncm91cElkPgogICAgICAgICAgICAgICAgPGFydGlmYWN0SWQ+bWF2ZW4tY29tcGlsZXItcGx1Z2luPC9hcnRpZmFjdElkPgogICAgICAgICAgICAgICAgPHZlcnNpb24+My44LjE8L3ZlcnNpb24+CiAgICAgICAgICAgIDwvcGx1Z2luPgogICAgICAgIDwvcGx1Z2lucz4KICAgIDwvYnVpbGQ+CjwvcHJvamVjdD4="
startPomXml="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHByb2plY3QgeG1sbnM9Imh0dHA6Ly9tYXZlbi5hcGFjaGUub3JnL1BPTS80LjAuMCIgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIKICAgIHhzaTpzY2hlbWFMb2NhdGlvbj0iaHR0cDovL21hdmVuLmFwYWNoZS5vcmcvUE9NLzQuMC4wIGh0dHBzOi8vbWF2ZW4uYXBhY2hlLm9yZy94c2QvbWF2ZW4tNC4wLjAueHNkIj4KICAgIDxtb2RlbFZlcnNpb24+NC4wLjA8L21vZGVsVmVyc2lvbj4KICAgIDxwYXJlbnQ+CiAgICAgICAgPGdyb3VwSWQ+JGdyb3VwSWQ8L2dyb3VwSWQ+CiAgICAgICAgPGFydGlmYWN0SWQ+JGFydGlmYWN0SWQ8L2FydGlmYWN0SWQ+CiAgICAgICAgPHZlcnNpb24+MC4wLjEtU05BUFNIT1Q8L3ZlcnNpb24+CiAgICA8L3BhcmVudD4KICAgIDxhcnRpZmFjdElkPiRuYW1lLXN0YXJ0PC9hcnRpZmFjdElkPgoKICAgIDxkZXBlbmRlbmNpZXM+CiAgICAgICAgPGRlcGVuZGVuY3k+CiAgICAgICAgICAgIDxncm91cElkPiRncm91cElkPC9ncm91cElkPgogICAgICAgICAgICA8YXJ0aWZhY3RJZD4kbmFtZS13ZWI8L2FydGlmYWN0SWQ+CiAgICAgICAgICAgIDx2ZXJzaW9uPiR7cHJvamVjdC52ZXJzaW9ufTwvdmVyc2lvbj4KICAgICAgICA8L2RlcGVuZGVuY3k+CiAgICAgICAgPGRlcGVuZGVuY3k+CiAgICAgICAgICAgIDxncm91cElkPm9yZy5zcHJpbmdmcmFtZXdvcmsuYm9vdDwvZ3JvdXBJZD4KICAgICAgICAgICAgPGFydGlmYWN0SWQ+c3ByaW5nLWJvb3Qtc3RhcnRlci10ZXN0PC9hcnRpZmFjdElkPgogICAgICAgICAgICA8c2NvcGU+dGVzdDwvc2NvcGU+CiAgICAgICAgICAgIDxleGNsdXNpb25zPgogICAgICAgICAgICAgICAgPGV4Y2x1c2lvbj4KICAgICAgICAgICAgICAgICAgICA8Z3JvdXBJZD5vcmcuanVuaXQudmludGFnZTwvZ3JvdXBJZD4KICAgICAgICAgICAgICAgICAgICA8YXJ0aWZhY3RJZD5qdW5pdC12aW50YWdlLWVuZ2luZTwvYXJ0aWZhY3RJZD4KICAgICAgICAgICAgICAgIDwvZXhjbHVzaW9uPgogICAgICAgICAgICA8L2V4Y2x1c2lvbnM+CiAgICAgICAgPC9kZXBlbmRlbmN5PgogICAgPC9kZXBlbmRlbmNpZXM+CiAgICAKICAgIDxidWlsZD4KICAgICAgICA8cGx1Z2lucz4KICAgICAgICAgICAgPHBsdWdpbj4KICAgICAgICAgICAgICAgIDxncm91cElkPm9yZy5hcGFjaGUubWF2ZW4ucGx1Z2luczwvZ3JvdXBJZD4KICAgICAgICAgICAgICAgIDxhcnRpZmFjdElkPm1hdmVuLWNvbXBpbGVyLXBsdWdpbjwvYXJ0aWZhY3RJZD4KICAgICAgICAgICAgPC9wbHVnaW4+CiAgICAgICAgICAgIDxwbHVnaW4+CiAgICAgICAgICAgICAgICA8Z3JvdXBJZD5vcmcuc3ByaW5nZnJhbWV3b3JrLmJvb3Q8L2dyb3VwSWQ+CiAgICAgICAgICAgICAgICA8YXJ0aWZhY3RJZD5zcHJpbmctYm9vdC1tYXZlbi1wbHVnaW48L2FydGlmYWN0SWQ+CiAgICAgICAgICAgICAgICA8dmVyc2lvbj4ke3NwcmluZy1ib290LnZlcnNpb259PC92ZXJzaW9uPgogICAgICAgICAgICAgICAgPGNvbmZpZ3VyYXRpb24+CiAgICAgICAgICAgICAgICAgICAgPG1haW5DbGFzcz4kcGFja2FnZS4kQXBwbGljYXRpb25OYW1lQXBwbGljYXRpb248L21haW5DbGFzcz4KICAgICAgICAgICAgICAgICAgICA8c2tpcD5mYWxzZTwvc2tpcD4KICAgICAgICAgICAgICAgIDwvY29uZmlndXJhdGlvbj4KICAgICAgICAgICAgICAgIDxleGVjdXRpb25zPgogICAgICAgICAgICAgICAgICAgIDxleGVjdXRpb24+CiAgICAgICAgICAgICAgICAgICAgICAgIDxpZD5yZXBhY2thZ2U8L2lkPgogICAgICAgICAgICAgICAgICAgICAgICA8Z29hbHM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8Z29hbD5yZXBhY2thZ2U8L2dvYWw+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvZ29hbHM+CiAgICAgICAgICAgICAgICAgICAgPC9leGVjdXRpb24+CiAgICAgICAgICAgICAgICA8L2V4ZWN1dGlvbnM+CiAgICAgICAgICAgIDwvcGx1Z2luPgogICAgICAgIDwvcGx1Z2lucz4KICAgIDwvYnVpbGQ+CjwvcHJvamVjdD4="

funStart
