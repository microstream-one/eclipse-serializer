# Prepare code drop for Eclipse Serializer

Within this repo, the code donation of the MicroStream Serializer is prepared.

General rules

- No functional or architectural changes. Cde will be donated as is. 
- Only code required for the Serialization functionality will be included (all parts related to the StorageManager concept will be part of Eclipse Stream - or whatever the name will be)
- Moving classes to new artifacts can be necessary to make sure the Serializer parts (like Serializer Foundation and specific Storer) are not leaking into the Eclipse Stream when they depend on these artifacts.
- Addition of high level integration tests to make sure the serializer works (and is compatible with binary format of MicroStream) is required.
- Addition of User guide in asciidoc is needed so that developers know what to do and how features can be used.

See also https://microstream.atlassian.net/wiki/spaces/MSG/pages/2113568769/Initial+donation and https://microstream.atlassian.net/wiki/spaces/MSG/pages/2118975491/Code+drop+and+changes



## Good to know

Run only the integration tests

```
mvn clean verify -f tests/pom.xml
```