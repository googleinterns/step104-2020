#!/usr/bin/env python3
from google.cloud import datastore
import os
import csv
import sys
from datetime import datetime as dt
from dateutil import parser

project_id = "google.com:sdk-dashboard-step-2020"
datastore_client = datastore.Client(project_id)

def createSDKReleases(fileName):
    pass

def createMetadatas(line, launchDate, releaseName):
    sdkRelease_key = client.key("SDKReleaseMetadata", 'android' + '_' + line[0] + '_' + releaseName)
    sdkRelease = datastore.Entity(key=sdkRelease_key)
    sdkRelease['libraryName'] = line[0]
    sdkRelease['releaseVersion'] = line[6]
    sdkRelease['oldVersion'] = line[5]
    sdkRelease['releaseName'] = releaseName
    sdkRelease['verifier'] = line[3]
    sdkRelease['platform'] = 'android'
    sdkRelease['additionalInfo'] = {}
    sdkRelease['additionalInfo']['arianeEntry'] = line[7]
    sdkRelease['additionalInfo']['releaseNoteCL'] = line[8]
    sdkRelease['additionalInfo']['javadocsCL'] = line[9]
    sdkRelease['additionalInfo']['changelogLink'] = line[10]
    sdkRelease['additionalInfo']['internalHotlist'] = line[11]
    sdkRelease['additionalInfo']['prebuiltsDropBug'] = line[12]
    sdkRelease['additionalInfo']['manualVerificationBug'] = line[13]
    sdkRelease['additionalInfo']['javadocBug'] = line[14]
        
def addSDKVersion(sdkVersion):
    sdk_key = client.key("SDK", sdkVersion['platform'] + "_" + sdkVersion['libraryName'])
    sdk_entity = datastore.Entity(sdk_key)

    versionHistory = sdk_entity['versionHistory']
    version_key = datastore_client.key("VersionMetadata", 'android' + '_' + sdkVersion['libraryName'] + '_' + sdkVersion['releaseVersion'])
    versionHistory.append(sdkVersion)
    sdkVersion['versionHistory'] = sorted(versionHistoru, key = lambda i: i['version'])
    return

def createReleases(fileName):
    with open(fileName) as f:
        print("Scraping: " + fileName)
        tsv = csv.reader(f, delimiter='\t')
        line = next(tsv)
        name = 'android' + "_" + fileName.split('/')[-1].split('.')[0]
        kind = "Release"
        release_key = datastore_client.key(kind, name)
        release = datastore.Entity(key=release_key)
        release['releaseName'] = fileName.split('/')[-1].split('.')[0]

        release['platform'] = 'android'
        release['buganizerHotlistLink'] = line[1]
        release['releaseManager'] = line[2].split(' ')[-1]
        release['codeFreezeTime'] = int(dt.timestamp(parser.parse(line[3].split('(')[0][len("Code Freeze "):])))
        line = next(tsv)
        try:
            release['launchCalDeadline'] = int(dt.timestamp(parser.parse(line[0][len("Launchcal Deadline "):])))
            line = next(tsv)
        except:
            release['launchCalDeadline'] = None

        release['launchDate'] = int(dt.timestamp(parser.parse(line[0].split('(')[0][len("Launch "):])))
        print(release)
        datastore_client.put(release)
        print('Saved {}: {}'.format(release.key.name, release['releaseName']))
        return


if __name__ == "__main__":
    files = []
    for filename in os.listdir("release_tsvs"):
        if filename[-3:] == "tsv":
            files.append(filename)

    files.sort()
    for f in files:
        # createReleases("release_tsvs/" + f)
        createSDKReleases("release_tsvs" + f)



