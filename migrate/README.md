# Migrating Data from Spreadsheets

This directory contains scripts that automatically transfer data from tsv files representing releases into our production cloud datastore database.

## Prerequisites

The release_tsv directory must be populated with tsv files containing information about a specific release and must be named what the release is named. 

They also must follow the format of the most recent spreadsheets as of August 6, 2020 (ie M77). At M52 the format slightly changes and the script will have to be changed to accommodate for that as well as any other change in the format of the spreadsheets

## Usage
First navigate to the migrate directory

```bash
cd migrate/
```
Then to run the migrateSdks script:
```bash
./migrateSdks.py release_tsvs/name_of_release.tsv
```

To run migrateReleases:
```bash
./migrateReleases.py
```

## Important Notes
Running migrateSdks.py will overwrite any pre-existing sdk entities in the datastore which results in losing that sdk's version history. If that ends up happening, it is best to delete all the SDKReleaseMetadata entities in the datastore and then running the migrateReleases.py script in order to repopulate.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
