# BotPlatform

[![Build Status](https://travis-ci.org/riguron/BotPlatform.svg?branch=master)](https://travis-ci.org/riguron/BotPlatform)
[![codecov](https://codecov.io/gh/riguron/BotPlatform/branch/master/graph/badge.svg)](https://codecov.io/gh/riguron/BotPlatform)
[![HitCount](http://hits.dwyl.io/riguron/BotPlatform.svg)](http://hits.dwyl.io/riguron/BotPlatform)

Simple facade or abstraction for various chat bot platforms that allows for creating 
platform-independent chat bots and choosing an appropriate platform at the runtime.

#### Currently supported platforms

- VK
- Telegram

# Features

- Attaching the media (photos, videos, audios, documents) to the message.
- Command framework.
- Inline keyboards, convenient builder.
- Accessing the media of the incoming messages.
- Sending stickers.
- Switching bot platform transparently, without any code modifications.

# Technologies used

- Spring Boot
- PF4J
- VK Java SDK
- Java Telegram Bot API
- JUnit / Mockito
- Lombok
- Maven
