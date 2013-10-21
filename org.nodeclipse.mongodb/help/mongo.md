

# MongoDB

From MongoDB README

```txt
	COMPONENTS
	
	  bin/mongod - The database process.
	  bin/mongos - Sharding controller.
	  bin/mongo  - The database shell (uses interactive javascript).
	
	UTILITIES
	
	  bin/mongodump         - MongoDB dump tool - for backups, snapshots, etc..
	  bin/mongorestore      - MongoDB restore a dump
	  bin/mongoexport       - Export a single collection to test (JSON, CSV)
	  bin/mongoimport       - Import from JSON or CSV
	  bin/mongofiles        - Utility for putting and getting files from MongoDB GridFS
	  bin/mongostat         - Show performance statistics
```
	
### Learning

There is great & simple online tutorial as Online Shell at <http://www.mongodb.org/>	
	
### MongoDB shell
	
```txt	
	D:\Progs\MongoDB\mongodb-win32-x86_64-2.4.6\bin>mongo.exe -h
	MongoDB shell version: 2.4.6
	usage: D:\Progs\MongoDB\mongodb-win32-x86_64-2.4.6\bin\mongo.exe [options] [db address] [file names (ending in .js)]
	db address can be:
	  foo                   foo database on local machine
	  192.169.0.5/foo       foo database on 192.168.0.5 machine
	  192.169.0.5:9999/foo  foo database on 192.168.0.5 machine on port 9999
	options:
	  --shell                               run the shell after executing files
	  --nodb                                don't connect to mongod on startup - no
	                                        'db address' arg expected
	  --norc                                will not run the ".mongorc.js" file on
	                                        start up
	  --quiet                               be less chatty
	  --port arg                            port to connect to
	  --host arg                            server to connect to
	  --eval arg                            evaluate javascript
	  -u [ --username ] arg                 username for authentication
	  -p [ --password ] arg                 password for authentication
	  --authenticationDatabase arg          user source (defaults to dbname)
	  --authenticationMechanism arg (=MONGODB-CR)
	                                        authentication mechanism
	  -h [ --help ]                         show this usage information
	  --version                             show version information
	  --verbose                             increase verbosity
	  --ipv6                                enable IPv6 support (disabled by
	                                        default)
	
	file names: a list of files to run. files have to end in .js and will exit after unless --shell is specified
```