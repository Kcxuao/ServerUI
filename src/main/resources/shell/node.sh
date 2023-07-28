
# /bin/sh
fileName=$1
echo fileName;
cd /opt/server/node || exit
unzip "$fileName"

# shellcheck disable=SC2206
array=(${fileName//./ })
cd "${array[0]}" || exit
node index.js
