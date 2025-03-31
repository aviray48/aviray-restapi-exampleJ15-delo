# **Information on Configuration for aviray-restapi-exampleJ15-delo**

This is only a brief summary of the information for configuring and running stuff. The most up-to-date and complete information can be found [here](https://github.com/aviray48/aviray-restapi-exampleJ15-delo).<br>

```
Eclipse Run/Debug Configurations:

	Maven Build:

		Name: aviray-restapi-exampleJ15-delo CLEAN
		Base Directory: ${project_loc:aviray-restapi-exampleJ15-delo}
		Goals: clean
		Profiles: N/A
		Skip Tests Checked
		Execution Environment: JavaSE-15

		Name: aviray-restapi-exampleJ15-delo CLEAN COMPILE BATCH
		Base Directory: ${project_loc:aviray-restapi-exampleJ15-delo}
		Goals: clean compile
		Profiles: batch
		Skip Tests Checked
		Execution Environment: JavaSE-15

		Name: aviray-restapi-exampleJ15-delo CLEAN COMPILE WEB
		Base Directory: ${project_loc:aviray-restapi-exampleJ15-delo}
		Goals: clean compile
		Profiles: web
		Skip Tests Checked
		Execution Environment: JavaSE-15

		Name: aviray-restapi-exampleJ15-delo CLEAN PACKAGE BATCH
		Base Directory: ${project_loc:aviray-restapi-exampleJ15-delo}
		Goals: clean package
		Profiles: batch
		Update Snapshots Checked
		Skip Tests Checked
		Execution Environment: JavaSE-15

		Name: aviray-restapi-exampleJ15-delo CLEAN PACKAGE WEB
		Base Directory: ${project_loc:aviray-restapi-exampleJ15-delo}
		Goals: clean package
		Profiles: web
		Update Snapshots Checked
		Skip Tests Checked
		Execution Environment: JavaSE-15

	Java Application:

		Name: ExampleAppBatchSimpleBatch_J15_Delo
		Project: aviray-restapi-exampleJ15-delo
		Main Class: ray.avi.example.batch.AppBatch
		Program arguments: N/A
		Program arguments:
			-Dserver.port=54321 -Dspring.application.name=example-app-batch -Dspring.profiles.active=batchExampleSimpleBatch -Dspring.cloud.consul.config.enabled=false -Dmanagement.health.consul.enabled=false -Dlogging.config=/relic/SourceControl/GIT_Local/aviray-restapi-exampleJ15-delo/config/logback-spring.xml -Dservice.log.location=/relic/ProgFiles/appLogs/example-app-batch -Dspring.config.location=config/application.yml,security/application.yml
		Execution Environment: JavaSE-15
		
		Name: ExampleAppServer_J15_Delo
		Project: aviray-restapi-exampleJ15-delo
		Main Class: ray.avi.example.AppServer
		Program arguments: N/A
		Program arguments:
			-Dserver.port=12070 -Dspring.application.name=example-app-web-delo -Dspring.profiles.active=web -Dspring.cloud.consul.config.enabled=false -Dmanagement.health.consul.enabled=false -Dlogging.config=/relic/SourceControl/GIT_Local/aviray-restapi-exampleJ15-delo/config/logback-spring.xml -Dservice.log.location=/relic/ProgFiles/appLogs/example-app-web-delo -Dspring.config.location=config/application.yml,security/application.yml
		Execution Environment: JavaSE-15
```

Any variables containing (somewhat) sensitive information (if it exists) that I don't want to just put in this file will have their values replaced with: "ZZZZZZZZ"
<br>
Sensitive Information Variables:

```
	N/A
```

Notes:<br>


<br><br><br>


Information on Markdown file Syntax can be found [here](https://www.markdownguide.org/).<br>
<br><br><br>
