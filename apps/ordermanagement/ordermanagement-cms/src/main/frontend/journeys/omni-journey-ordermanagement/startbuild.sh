#!/usr/bin/env bash
craco build
#Replace complete url for a relative url for the delivrables path
sed -i 's/http\:\/\/localhost\:4502\/content\/training\/foundation\/v3\/deliverables/\/content\/training\/foundation\/v3\/deliverables/g' build/index.html
