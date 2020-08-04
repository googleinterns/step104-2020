from google.cloud import datastore

project_id = "google.com:sdk-dashboard-step-2020"
datastore_client = datastore.Client(project_id)

kind = "SDK"
name = "sampleSdk1"
sdk_key = datastore_client.key(kind, name)

sdk = datastore.Entity(key=sdk_key)
sdk['description'] = 'Buy Milk'

datastore_client.put(sdk);

print('Saved {}: {}'.format(sdk.key.name, sdk['description']))

