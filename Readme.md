# Coffee Machine Kata

The [original subject](https://simcap.github.io/coffeemachine/) is provided by simcap.
I haven't seen changes for years, but still internet is evolving.
So I use a text copy of his subject as of November 26th 2019.

## Introduction

This mini project is used to learn Test Driven Development.

Of course, in your career, you have implemented more complicated stuff than a simple coffee machine that takes orders. But to make this mini project more interesting here are simple rules you must follow:

All production code is written to make a failing test pass
Do the simplest thing that could work for the current iteration
## Project
In this coffee Machine Project, your task is to implement the logic (starting from a simple class) that translates orders from customers of the coffee machine to the drink maker. Your code will use the drink maker protocol to send commands to the drink maker.

![coffee machine internal component schema]("src/spec/resources/coffee-800x700.png")

### Important !
**You do not have to implement the coffee machine customer interface.** For instance, your code could consume a simple POJO that would represent an order from a customer.

**You do not have to implement the drink maker.** It is only a imaginary engine that will receive messages according to the protocol. Your job is to build those messages.

### Iterations
This project starts simple and will grow in added features through the iterations.

1. First iteration: Making Drinks (~30 minutes)
1. Second iteration: Going into business (~20 minutes)
1. Third iteration: Extra hot (~20 minutes)
1. Fourth iteration: Making money (~20 minutes)
1. Fifth iteration: Running out (~20 minutes)

## Ready ?
### Requirements
Your favorite IDE or text editor
A testing framework (junit, rspec, ...)
A mocking framework (mockito, ...)
A passion for tested code ;)
Are you ready? So start [the first iteration](iteration_01.md). Your product owner is waiting for you!