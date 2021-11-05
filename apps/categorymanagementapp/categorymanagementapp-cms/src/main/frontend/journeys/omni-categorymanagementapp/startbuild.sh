#!/usr/bin/env bash
craco build
#Replace complete url for a relative url for the delivrables path
sed -i 's/http\:\/\/localhost\:4502\/content\/digitaljourney\/foundation\/v3\/deliverables/\/content\/digitaljourney\/foundation\/v3\/deliverables/g' build/index.html
