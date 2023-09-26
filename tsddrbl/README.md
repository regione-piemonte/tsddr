# Product
TSDDR components TSDDRWCL and TSDDRBL

# Component Description
This folder represents an "installation unit" that encapsulates two distinct "logical" components, namely TSDDRWCL and TSDDRBL.

TSDDRBL
is a web application following the "Single Page Application (SPA)" paradigm, exposing REST services to the TSDDRWCL component (Angular 11), and connecting to the DB (TSDDRDB) for CRUD operations. It integrates with the authentication service (SHIBBOLETH in the context of Regione Piemonte) and other services of the Regional Fiscal Information System (DOQUI-ACTA). External services are accessed via HTTP (secured with a "basic authentication" scheme) or HTTPS. This component connects to the database (PostgreSQL DBMS, see tsddrdb component) using the DataSource defined at the container level (WildFly).

# Initial Configurations
From a general perspective, in the initial phase, you need to adapt the properties files in the buildfiles directory to your own configuration. One of the main things to configure is the datasource with the DB references you intend to use (JNDI name).

# Getting Started
Once you have retrieved the component from the repository ("git clone"), proceed with the modification of configuration files based on your deployment environment, and then proceed with the build process.

# System Prerequisites
Firstly, you need to prepare the DB Schema used by this component and populate it with initial data: therefore, you should have completed the installation and configuration of the comonldb component.

You also need to make the necessary replacements for the external services called.

For the "build," Apache Ant is used. Before proceeding with the build of the TSDDRBL component, you need to follow the necessary steps defined in the README.md file of the TSDDRWCL component. 
This is because the two logical components will be encapsulated into a single installation unit. The TSDDRBL build process involves retrieving the compilation output of TSDDRWCL and including it in the project path of the TSDDRBL component (src/web/tsddr/rest/). 
After copying the files, you can proceed with the build (e.g. ant -Dtarget=dev-rp-01 -lib apache-ivy-2.0.0.jar).

The resulting installation unit from the TSDDRBL build will be a file named tsddrbl-MAJOR.MINOR.PATCH.tar, containing a file tsddrbl.ear structured as follows:

-lib
-META-INF
-appl-tsddrbl-rest.war

# Installation - Deployment

Install the generated "ear" file on your WildFly environment.

# Running Tests

This component has undergone a vulnerability assessment.

# Versioning

Semantic Versioning is used for software versioning (http://semver.org).

# Copyrights

© Copyright Regione Piemonte – 2022
© Copyright CSI-Piemonte – 2022

This same list of software holders is also provided in Copyrights.txt.

# License
The software product is subject to the EUPL-1.2 or later license.
SPDX-License-Identifier: EUPL-1.2-or-later
