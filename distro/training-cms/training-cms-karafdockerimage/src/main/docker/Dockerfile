FROM @docker.from@:@parsedToolingVersion.majorVersion@.@parsedToolingVersion.minorVersion@.@parsedToolingVersion.incrementalVersion@
MAINTAINER @project.organization.name@  <@mailingLists.default.post@>
# Environment variables
ENV KARAF_VERSION="@grp.karaf.version@"
# Copy commands
COPY --chown=@karafassembly.user@:@karafassembly.group@ maven /
USER 0
# Copy commands
USER runit
