
# /bin/sh
fileName=$1
# shellcheck disable=SC2086
cp /opt/temp/$fileName /usr/share/nginx/html
cd /usr/share/nginx/html || exit
# shellcheck disable=SC2086
unzip $fileName
