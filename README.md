[logo]: https://cdn.xy.company/img/brand/XY_Logo_GitHub.png

![logo]

# sdk-core-kotlin

[![](https://travis-ci.org/XYOracleNetwork/sdk-core-kotlin.svg?branch=master)](https://travis-ci.org/XYOracleNetwork/sdk-core-kotlin) [![BCH compliance](https://bettercodehub.com/edge/badge/XYOracleNetwork/sdk-core-kotlin?branch=master)](https://bettercodehub.com/) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/2fb2eb69c1db455299ffce57b0216aa6)](https://www.codacy.com/app/XYOracleNetwork/sdk-core-kotlin?utm_source=github.com&utm_medium=referral&utm_content=XYOracleNetwork/sdk-core-kotlin&utm_campaign=Badge_Grade) [![Maintainability](https://api.codeclimate.com/v1/badges/af641257b27ecea22a9f/maintainability)](https://codeclimate.com/github/XYOracleNetwork/sdk-core-kotlin/maintainability)

[![](https://jitpack.io/v/XYOracleNetwork/sdk-core-kotlin.svg)](https://jitpack.io/#XYOracleNetwork/sdk-core-kotlin) [![](https://img.shields.io/gitter/room/XYOracleNetwork/Stardust.svg)](https://gitter.im/XYOracleNetwork/Dev)

## Table of Contents

-   [Title](#sdk-core-kotlin)
-   [Description](#description)
-   [XYO Origin Block Protocol](#xyo-origin-block-protocol)
-   [Core Object Model](#core-object-model)
-   [Install](#install)
-   [Building and Testing with Gradle](#building-and-testing-with-gradle)
-   [Maintainers](#maintainers)
-   [Contributing](#contributing)
-   [License](#license)
-   [Credits](#credits)

## Description

Library to preform all core XYO Network functions which includes

-   Creating an origin chain
-   Maintaining an origin chain
-   Negotiations for talking to other nodes
-   Other basic functionality

The library has heavily abstracted modules so that all operations will work with any crypto, storage, networking, ect.

## XYO Origin-Block Protocol

The XYO protocol for creating origin-blocks is specified in the [XYO Yellow Paper](https://docs.xyo.network/XYO-Yellow-Paper.pdf). In it, it describes the behavior of how a node on the XYO network should create Bound Witnesses. Note, the behavior is not coupled with any particular technology constraints around transport layers, cryptographic algorithms, or hashing algorithms.

## Core Object Model

[Here](https://github.com/XYOracleNetwork/spec-coreobjectmodel-tex) is a link to the core object model that contains an index of major/minor values and their respective objects.

## Install

You can add sdk-core-kotlin to your existing app by cloning the project and manually adding it to your build.gradle or by using JitPack.

### Build From Source

1) Clone from github

    git clone git@github.com:XYOracleNetwork/sdk-core-kotlin.git

2) Add project to settings.gradle

    include ':sdk-core-kotlin'
    project(':sdk-core-kotlin').projectDir = new File('../sdk-core-kotlin')

3) Include in project

    implementation project (':sdk-core-kotlin')

### Using JitPack

#### With Gradle

1.  Point maven to `https://jitpack.io`

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2.  Inlucde sdk-core-kotlin in dependencies

```gradle
dependencies {
	implementation 'com.github.XYOracleNetwork:sdk-core-kotlin:v0.1.1-beta.0'
}
```

### With Maven

1.  Point maven to `https://jitpack.io`

```maven
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

2.  Inlucde sdk-core-kotlin in dependencies

```maven
<dependency>
    <groupId>com.github.XYOracleNetwork</groupId>
    <artifactId>sdk-core-kotlin</artifactId>
    <version>Tag</version>
</dependency>
```

## Building and Testing with Gradle

**Building**
Source ius located in /src/main/\*

    gradle build

**Testing**
All tests can be found in /src/test/\*

    gradle test

## Maintainers

-   Carter Harrison

## License

See the [LICENSE.md](LICENSE) file for license details.

## Credits

Made with 🔥and ❄️ by [XY - The Persistent Company](https://www.xy.company)
