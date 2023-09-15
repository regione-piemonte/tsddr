# Product

TRISPE - Special Waste Deposit Fee

# Product Description
The TRISPE system consists of two applications.

TRISPE-FO is for operators of companies that manage waste treatment and disposal facilities and are required to comply with current regulations. It includes the following functionalities:

- Accreditation allows you to submit a request for access to the system.

- Annual declaration allows you to submit the annual declaration of quantities deposited in the year and payments made. The application allows data input, submission of the declaration with simultaneous PDF generation, and protocol recording. Additionally, there is a functionality to search for declarations in progress and those already sent.

- Declarations with MR objectives allow you to submit the annual declaration of actual achievement of recovery percentages or not exceeding waste percentage thresholds. The application allows data input, submission of the declaration with simultaneous PDF generation, and protocol recording. For filling out, you can use the data from a request for reduced-rate payment submitted with TRISPE. There is also a functionality to search for declarations in progress and those already sent.

- MR admission requests allow you to submit an application for the payment of the fee at a reduced rate. The application allows data input, submission of the request with simultaneous PDF generation, and protocol recording. Additionally, there is a functionality to search for requests in progress and those already sent.

TRISPE-BO is for public administration users responsible for control. It includes the following functionalities:

- Accreditation management allows for the consultation and processing of accreditation requests submitted by company operators to access functionalities aimed at businesses.
- Annual declaration management allows for the consultation of submitted declarations.
- MR declaration management allows for the consultation of declarations regarding objective achievement.
- Management of requests for admission at a reduced fee allows for the consultation of requests submitted for reduced-rate fee payment.

The product is structured into the following specific components:
- [tsddrdb](https://github.com/regione-piemonte/tsddr/tree/main/tsddrdb): Script for creating the database (PostgreSQL instance).
- [tsddrwcl](https://github.com/regione-piemonte/tsddr/tree/main/tsddrwcl): Web Client (Angular 11), application front-end.
- [tsddrbl](https://github.com/regione-piemonte/tsddr/tree/main/tsddrbl): Service exposure component (REST API) towards its own front-end tsddrwcl.

The tsddrwcl component is "logically" separate from tsddrbl but included in the same installation unit (EAR file).

Each product component listed above corresponds to a sub-directory named tsddr-<component_name>. Each of these component folders contains further specific information, including the component's BOM (Bill of Materials).

# System Prerequisites

A PostgreSQL instance (version 12 recommended) with a user account having privileges for creating tables and other DB objects (using DDL instructions provided in the comonldb component), and a separate user account not owning the schema for executing DML instructions for Create, Read, Update, and Delete operations on data.

A J2EE application server instance, WildFly 23 recommended (https://www.wildfly.org/downloads/).
A web server instance, Apache web server recommended (https://httpd.apache.org/).
For building, the use of Apache Ant Ivy (https://ant.apache.org/ivy/) is recommended.

The TSDDR product is integrated into the services of the Regione Piemonte information system "Fiscalità." Some of its functionalities are closely linked to the ability to access services exposed by other components of the Regione Piemonte "Fiscalità" ecosystem.

Lastly, regarding user authentication and profiling, TSDDR is integrated with cross-cutting services of the regional information system ("Shibboleth"). Consequently, for use in another context, similar services must be available or appropriate modules must be integrated to perform similar functions.

# Installation

Create the database schema using the scripts from the tsddrdb component.

Configure the datasource in the application server used in tsddrbl.

Configure the web servers and define the appropriate Virtual Hosts and "location" - to use the HTTPS protocol, you must have suitable SSL certificates.

If you want to use email sending features, configure a mail server as well.

# Deployment

After following the installation instructions, you can proceed with building the packages and deploying them on the application server (WildFly).

# Versioning

Git is used for source code management, but there are no constraints on the use of similar tools. Semantic Versioning (http://semver.org) is used for software versioning.

# Copyrights

© Copyright Regione Piemonte – 2023
© Copyright CSI-Piemonte – 2023

# License

SPDX-License-Identifier: EUPL-1.2-or-later.
This software is distributed under the EUPL-1.2 license. Please consult the LICENSE.txt file for license details.
