#!/usr/bin/env python3
from google.cloud import datastore

project_id = "google.com:sdk-dashboard-step-2020"
datastore_client = datastore.Client(project_id)


with open("./release_tsvs/M87.tsv") as f:
  for i in range(11):
    print(f.readline())

  row = f.readline()
  while row:
    line = row.split('\t')
    name = 'android' + "_" + line[0]
    kind = "SDK"
    sdk_key = datastore_client.key(kind, name)
    sdk = datastore.Entity(key=sdk_key)
    sdk['libraryName'] = line[0]
    sdk['externalName'] = line[1]
    sdk['platform'] = "ANDROID"
    sdk['libraryGroup'] = line[2]
    sdk['fireEscapeName'] = line[-1]
    sdk['owner'] = line[3]
    sdk['versionHistory'] = []
    datastore_client.put(sdk)
    print('Saved {}: {}'.format(sdk.key.name, sdk['libraryName']))
    row = f.readline()

