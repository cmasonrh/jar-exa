#NOTE: maybe replace this image with a TSSC openjdk image if such a thing ever needs to exist
FROM registry.redhat.io/ubi8/openjdk-17

USER 0

##############################
# vulenerability remediation #
##############################
RUN microdnf update -y && \
    microdnf clean all

# NOTE / WARNING / IMPORTANT:
#   work around for https://bugzilla.redhat.com/show_bug.cgi?id=1798685
RUN rm -f /var/log/lastlog

###############
# install app #
###############
COPY --chown=185 target/quarkus-app/ /deployments/
EXPOSE 8080

###########
# run app #
###########
USER 1001
ENTRYPOINT ["java", "-jar"]
CMD ["/deployments/quarkus-run.jar"]
