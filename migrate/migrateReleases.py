#!/usr/bin/env python3
# TODO: Replace commented print statements with logging statements
from google.cloud import datastore
import os
import csv
import sys
from datetime import datetime as dt
from dateutil import parser

projectId = "google.com:sdk-dashboard-step-2020"
datastoreClient = datastore.Client(projectId)

def createSDKReleases(fileName):
    with open(fileName) as f:
        print("############# Scraping: " + fileName + " #############")
        tsv = csv.reader(f, delimiter='\t')
        line = next(tsv)
        # print(line)
        releaseName = fileName.split('/')[-1].split('.')[0]
        line = next(tsv)
        if fileName != 'release_tsvs/Fiam_hotfix_04_22_20.tsv': # This file does not contain 
        # a LaunchCal Deadline and so the Launch date is on the second line instead of third 
        # for others.
            line = next(tsv)
        launchDate = int(dt.timestamp(parser.parse(line[0].split('(')[0][len("Launch "):])))
        for _ in range(8):
            line = next(tsv)

        # print(releaseName, launchDate)
        for line in tsv:
            # print(line[0])
            # print(line)
            if line[6] and line[4][:3] == "Yes":
                # print(line[6])
                sdkVersion, sdkRelease = createMetadatas(line, launchDate, releaseName)
                # print(sdkVersion)
                # print(sdkRelease)
                addSDKVersion(sdkVersion)
                # sys.exit()
                datastoreClient.put(sdkRelease)
                print('Saved {}: {}'.format(sdkRelease.key.name, sdkRelease['libraryName']))
        return

def createMetadatas(line, launchDate, releaseName):
    sdkReleaseKey = datastoreClient.key("SDKReleaseMetadata", 'android' + '_' + line[0] + '_' + releaseName)
    sdkRelease = datastore.Entity(key=sdkReleaseKey)
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

    # Create the versionMetadata embedded entity
    sdkVersion = {}
    sdkVersion['libraryName'] = line[0]
    sdkVersion['version'] = line[6]
    sdkVersion['releaseName'] = releaseName
    sdkVersion['platform'] = 'android'
    sdkVersion['launchDate'] = launchDate

    return sdkVersion, sdkRelease
        
def addSDKVersion(sdkVersion):
    if sdkVersion['libraryName'] == 'firebase-transport-encoders':
        sdkVersion['libraryName'] = 'firebase-encoders-json';
    elif sdkVersion['libraryName'][-3:] == "ktx":
        return
    sdkKey = datastoreClient.key("SDK", sdkVersion['platform'] + "_" + sdkVersion['libraryName'])
    sdkEntity = datastoreClient.get(sdkKey)
    # print(sdkVersion['libraryName'])
    # print(sdkEntity)

    versionHistory = sdkEntity['versionHistory']
    versionKey = datastoreClient.key("VersionMetadata", 'android' + '_' + sdkVersion['libraryName'] + '_' + sdkVersion['version'])
    versionHistory.append(sdkVersion)
    sdkEntity['versionHistory'] = sorted(versionHistory, key = lambda i: i['version'])
    # print(sdkEntity)
    datastoreClient.put(sdkEntity)
    return

def createReleases(fileName):
    with open(fileName) as f:
        print("Scraping: " + fileName)
        tsv = csv.reader(f, delimiter='\t')
        line = next(tsv)
        name = 'android' + "_" + fileName.split('/')[-1].split('.')[0]
        kind = "Release"
        releaseKey = datastoreClient.key(kind, name)
        release = datastore.Entity(key=releaseKey)
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
        datastoreClient.put(release)
        print('Saved {}: {}'.format(release.key.name, release['releaseName']))
        return


if __name__ == "__main__":
    files = []
    for filename in os.listdir("release_tsvs"):
        if filename[-3:] == "tsv":
            files.append(filename)

    files.sort()
    count = 0
    for f in files:
        # createReleases("release_tsvs/" + f)
        createSDKReleases("release_tsvs/" + f)
        count += 1
        # if count == 3:
            # sys.exit()



