# Mobiquity Package Challenge

Mobiquity Package Challenge is a cross platform library that provides the best solution for maximum benefit by establishing a relationship 
between the weight and price of the items to be put in the package.

The purpose of this application is to determine the items that should be put in the package in each line with the information in the given file.

## Introduction

Application accepts only file path that contains several lines. Each line is one test case and each line is a problem that will be solved.
Each line contains the weight that the package can take (before the colon) and the list of items you need to choose. Each item is enclosed in parentheses where the 1st number is a item’s index number, the 2nd is its weight and the 3rd is its cost.(i.e "5,63.69,€52")

Application has to determine which things to put into the package so that the total weight is less than or equal to the package limit and the total cost is as large as possible.

## Implementation

Mobiquity Package Challenge application reads each lines and convert into the Pack Model with string parsing operations. If there will be problem with parsing,casting or another unexpected situation, application throws APIException which design and created for this assignment. 

Given problem is actually same as **0-1 KnapSack Problem**. KnapSack problem basically has recursion and dynamic programming solution. **Memoziation technique** an extension of recursive approach is used to solve this problem. 

With This method is basically an extension to the recursive approach so that we can overcome the problem of calculating redundant cases and thus increased complexity. But adding some features and changing some places like not creating 2-D matrix for holding maximum total cost. So reducing space complexity with this change.

###### Sample Input
```

81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)

8 : (1,15.3,€34)

75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)

56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
```

###### Sample Output
```
4

_

2,7

8,9
```

###### Constraints

1. Packer class has pack method that accepts the absolute  path to a test file as a String. 
2. The test file will be in UTF-8 format. 
3. The pack method returns the solution as a String. 
4. If any constraints are not met then application throw APIException which belongs to application.

###### Additional Constraints

1. Max weight that a package can take is ≤ 100
2. There might be up to 15 items you need to choose from
3. Max weight and cost of an item is ≤ 100

## Usage

Clone repository from [Mobiquity Package Challenge](https://github.com/emircankilinc/MobiquityPackageChallenge.git).

Adding this application's dependency to another project's maven or gradle like below statements.

**Maven**
```
<dependency>
	<groupId>com.mobiquity</groupId>
	<artifactId>implementation</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```

**Gradle**
```
dependencies{
	implementation group: 'com.mobiquity', name: 'implementation', version: '1.0-SNAPSHOT'
  }
```

After adding dependency, to accessing Packer class, need to add below statement to related class:
```
import com.mobiquity.packer.Packer
```

After this process, Packer.pack operation can be call like below statement: (Need to change path file!)
```
Packer.pack("example_input.txt")
```


###### Building Source Code

**With Maven**

After cloning project, go to project directory and run mvn commands.

```
mvn clean
mvn compile
mvn install
```


**With Gradle**

```
gradle clean
gradle build
gradle run
```

Project has junit and apache commons dependeny that given below maven and gradle dependencies.

###### Dependencies

**Maven Dependencies**

```
<dependencies>
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.13.2</version>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>org.apache.commons</groupId>
		<artifactId>commons-lang3</artifactId>
		<version>3.12.0</version>
	</dependency>
</dependencies>
```

**Gradle Dependencies**

```
dependencies{
	implementation group: 'org.apache.commons', name: 'commons-lang3', version: '3.12.0'
	testImplementation group: 'junit', name: 'junit', version: '4.13.2'
}
```

###### Built With

-Java 11

-Maven/Gradle

-JUnit 4

