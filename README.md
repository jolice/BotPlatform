# BotPlatform

[![Build Status](https://travis-ci.org/riguron/BotPlatform.svg?branch=master)](https://travis-ci.org/riguron/BotPlatform)
[![codecov](https://codecov.io/gh/riguron/BotPlatform/branch/master/graph/badge.svg)](https://codecov.io/gh/riguron/BotPlatform)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/901ff3127c234201abc4586ef83faf7b)](https://www.codacy.com/manual/riguron/BotPlatform?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=riguron/BotPlatform&amp;utm_campaign=Badge_Grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=riguron_BotPlatform&metric=alert_status)](https://sonarcloud.io/dashboard?id=riguron_BotPlatform)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=riguron_BotPlatform&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=riguron_BotPlatform)

Simple facade or abstraction for various chat bot platforms that allows for creating 
platform-independent chat bots and choosing an appropriate platform at the runtime.

#### Currently supported platforms

- VK
- Telegram

## Features

- Attaching the media (photos, videos, audios, documents) to the message.
- Command framework.
- Inline keyboards, convenient builder.
- Accessing the media of the incoming messages.
- Sending stickers.
- Switching bot platform transparently, without any code modifications.

## Technologies used

- Spring Boot
- VK Java SDK
- Java Telegram Bot API
- JUnit / Mockito
- Lombok
- Maven

## Creating a bot

A bot to be run on the BotPlatform is represented by a plugin packaged in a JAR file. Such an architecture allows for decoupling the platform from the actual bot. 

### Building platform

To build a platform application locally, run

```bash
git clone git@github.com:riguron/BotPlatform.git
cd BotPlatform 
mvn clean install
mvn package spring-boot:repackage -pl Bootstrap
```

Platform's executable JAR file with all neccesary dependencies will be built under ``` Bootstrap/target/BotPlatform.jar``` 

### Maven project

As stated above, a bot is represented by a separate JAR file (plugin). Create a new Maven project and add the following dependency with provided scope:

```xml
 <dependency>
      <groupId>io.riguron.bot</groupId>
      <artifactId>api</artifactId>
      <version>1.0</version>
      <scope>provided</scope> 
</dependency>
``` 

### Implementing a bot

BotPlatform introduces two basic concepts: ApplicationMessageHandler and Command.

#### Command

A command is any message that starts with slash ('/').

An appropriate interface represents a command handled by a bot. Basic implementation looks as follows:

```java
@Component
public class EchoCommand implements Command {

    @Override
    public void execute(CommandExecution commandExecution) {
        MessageEvent event = commandExecution.getMessageEvent();
        event.newMessage(commandExecution.getChatId())
             .text("Argument - " + commandExecution.getArguments().first())
             .send();
    }

    @Override
    public List<String> aliases() {
        return Collections.singletonList("echo");
    }
}
```       

To create a command for your bot, implement ```Command``` interface and mark it with ```@Component``` annotation. The
implementation will be automatically detected by Spring framework.

The response for the command execution with body "/echo 123" will be "Argument - 123".

#### ApplicationMessageHandler

This interface represents a handler of non-command messages, i.e any messages that do not start with a slash. Basic implementation is as follows:

```java
@Component
public class EchoMessageHandler implements ApplicationMessageHandler {

    @Override
    public void handleMessage(MessageEvent event) {
        event.getMessageContext()
             .newMessage(event.getChatId()) 
             .text("Echo - " + event.getText()).send();
    }
}
```

Bot's answer for message "123" will be "Echo - 123". 
Application message handlers are registered in a same manner with commands (by adding ```@Component``` annotation) to the handler implementation.

### Running a bot

To build a bot, run

```bash
mvn package
``` 

Then put plugin's archive under the same directory with the platform's executable jar and run:

```bash
java -cp BotPlatform.jar \
-Dloader.path=. \
-Dplugin.package=your.plugin.base.package org.springframework.boot.loader.PropertiesLauncher \
PlatformName
```

Arguments:

- ```PlatformName``` is an identifier of messaging platform. Specify either of currenty supported ones (see [currently supported platforms](#currently-supported-platforms)). Name is case sensitive.
- ```-Dloader.path=.``` informs Spring Boot to load your bot's JAR from the current directory. Any other directory may be specified, for example, ```-Dloader.path=plugins/```. In this case, your bot's JAR must be put under ```plugins``` directory.
- ```-Dplugin.package=your.plugin.base.package``` defines a package inside your jar that contains your bot's components. It's highly recommended to specify the package that contains all bot's classes and subpackages. Components outside the specified package will be ignored by the platform.

The latter option may be specified in the ```application.properties``` file that should reside in the same directory with platform's executable JAR.

## Frequently asked questions

A: Do I need to know Spring framework to use BotPlatform?<br/>
Q: BotPlatform utilizes Spring to register your bot's components dynamically. To define them properly, basic grasp of Spring IoC is required. 

A: May I run multiple bots within the same BotPlatform application instance?<br/>
Q: It's not currently possible to run multiple bots within one application. BotPlatform is capable of handling messages from only one corresponding message platform bot. However, you may define unlimited amount of message handlers and commands (in distinct JAR archives as well). 

A: BotPlatform crashes after the startup.<br/>
Q: Most likely you've missed a mandatory platform's case sensitive platform identifier. See [running a bot](#running-a-bot) section. If the problem persists, open an issue.

A: An application starts, but bot doesn't handle the commands.<br/>
Q: Make sure that your bot's JAR resides in the directory specified in ```Dloader.path``` argument and your command or message handler resides in the package (or subpackages of package) specified in ```Dplugin.package```. Also check whether the the class of the handler is annotated with ```@Component```. Otherwise Spring is unable to detect the component.
