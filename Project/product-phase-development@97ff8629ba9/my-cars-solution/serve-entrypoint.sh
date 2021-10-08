#!/bin/sh
WWW_DIR=/app
INJECT_FILE_SRC="${WWW_DIR}/inject.template.js"
INJECT_FILE_DST="${WWW_DIR}/inject.js"
envsubst < "${INJECT_FILE_SRC}" > "${INJECT_FILE_DST}"
[ -z "$@" ] && serve -l 80 -s /app || $@