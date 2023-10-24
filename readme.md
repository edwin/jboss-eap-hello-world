# JBoss EAP Simple Hello World with Servlet

A simple Java Hello World, deployed on JBoss EAP 7.4, Java 11, and Openshift 4.

## Available URLs
There are three available URLs for testing,
- /hello
- /do-something
- /whatever

## How to Deploy to Openshift 4
create an empty folder
```
$ mkdir -p jboss-eap/deployments
```

put `ROOT.war` under `deployments` folder
```
$ tree .
.
└── deployments
    └── ROOT.war
```

create new `build-config`
```
$ oc new-build --name=custom-eap-application --binary=true --image-stream=jboss-eap74-openjdk11-openshift:latest
```

start building our application using `start-build` command,
```
$ oc start-build custom-eap-application --from-dir . --follow
```

a successful build would display below logs,
```
Uploading directory "." as binary input for the build ...
.
Uploading finished
build.build.openshift.io/custom-eap-application-6 started
Adding cluster TLS certificate authority to trust store
Receiving source from STDIN as archive ...
Adding cluster TLS certificate authority to trust store
Adding cluster TLS certificate authority to trust store
time="2023-10-24T07:46:59Z" level=info msg="Not using native diff for overlay, this may cause degraded performance for building images: kernel has CONFIG_OVERLAY_FS_REDIRECT_DIR enabled"
I1024 07:46:59.410613       1 defaults.go:112] Defaulting to storage driver "overlay" with options [mountopt=metacopy=on].
Caching blobs under "/var/cache/blobs".
Trying to pull image-registry.openshift-image-registry.svc:5000/openshift/jboss-eap74-openjdk11-openshift@sha256:625b52d27a99cec0dde22150805b8de7a84117b046da75703dcd348f6dd2117c...
Getting image source signatures
Copying blob sha256:835ac13c5113f52d1cc037c72246bff398134e3c7c8547f54643aa1656006e24
Copying blob sha256:0fa65fe5c23e8b1745b1f39aa3735f2f3ce77cad9e470bfbb1468cb45a886bbe
Copying config sha256:420662e99bc960dac528347034ff77df4c304187557c44c850d02598b2a4a6c3
Writing manifest to image destination
Storing signatures
Generating dockerfile with builder image image-registry.openshift-image-registry.svc:5000/openshift/jboss-eap74-openjdk11-openshift@sha256:625b52d27a99cec0dde22150805b8de7a84117b046da75703dcd348f6dd2117c
Adding transient rw bind mount for /run/secrets/rhsm
STEP 1/9: FROM image-registry.openshift-image-registry.svc:5000/openshift/jboss-eap74-openjdk11-openshift@sha256:625b52d27a99cec0dde22150805b8de7a84117b046da75703dcd348f6dd2117c
STEP 2/9: LABEL "io.openshift.build.image"="image-registry.openshift-image-registry.svc:5000/openshift/jboss-eap74-openjdk11-openshift@sha256:625b52d27a99cec0dde22150805b8de7a84117b046da75703dcd348f6dd2117c"       "io.openshift.build.source-location"="/tmp/build/inputs"       "io.openshift.s2i.destination"="/tmp"
STEP 3/9: ENV OPENSHIFT_BUILD_NAME="custom-eap-application-6"     OPENSHIFT_BUILD_NAMESPACE="edwin-ns"
STEP 4/9: USER root
STEP 5/9: COPY upload/src /tmp/src
STEP 6/9: RUN chown -R 185:0 /tmp/src
STEP 7/9: USER 185
STEP 8/9: RUN /usr/local/s2i/assemble
INFO S2I source build with plain binaries detected
INFO Copying deployments from . to /deployments...
INFO Copying deployments from deployments to /deployments...
'/tmp/src/deployments/ROOT.war' -> '/deployments/ROOT.war'
INFO Cleaning up source directory (/tmp/src)
INFO Server not copied to /s2i-output, provisioned server is bound to local repository and can't be used in chained build. You can use galleon env variables to provision a server that can then be used in chained build.
STEP 9/9: CMD /usr/local/s2i/run
COMMIT temp.builder.openshift.io/edwin-ns/custom-eap-application-6:b7d61a9b
Getting image source signatures
Copying blob sha256:486dcc5a5ac3b9d7452d24fdd0c8e1d6c1d452a5827276febe9f194ba2cb2992
Copying blob sha256:b79820fddf771dc6cde626d5053b942c0498658b3fc0709eef2a5c64f6a88797
Copying blob sha256:0c908dcd41bd9b07ccde4a3eea15c06ff6f21c956efe91c2f5c33c2c06ec57e2
Copying config sha256:89fcefdab50a03fca475d17d21c4d43149a84fa39ba2398e11510c99ada18828
Writing manifest to image destination
Storing signatures
--> 89fcefdab50
Successfully tagged temp.builder.openshift.io/edwin-ns/custom-eap-application-6:b7d61a9b
89fcefdab50a03fca475d17d21c4d43149a84fa39ba2398e11510c99ada18828

Pushing image image-registry.openshift-image-registry.svc:5000/edwin-ns/custom-eap-application:latest ...
Getting image source signatures
Copying blob sha256:0c908dcd41bd9b07ccde4a3eea15c06ff6f21c956efe91c2f5c33c2c06ec57e2
Copying blob sha256:0fa65fe5c23e8b1745b1f39aa3735f2f3ce77cad9e470bfbb1468cb45a886bbe
Copying blob sha256:835ac13c5113f52d1cc037c72246bff398134e3c7c8547f54643aa1656006e24
Copying config sha256:89fcefdab50a03fca475d17d21c4d43149a84fa39ba2398e11510c99ada18828
Writing manifest to image destination
Storing signatures
Successfully pushed image-registry.openshift-image-registry.svc:5000/edwin-ns/custom-eap-application@sha256:4e6ddd5f738f98a38c6a0d7ab73804efed515d296799f2212736ada4d22d92ec
Push successful
```

and finally deploy our application directly by using `oc new-app` command,
```
$ oc new-app --name=custom-eap-application --image-stream=edwin-ns/custom-eap-application:latest
```