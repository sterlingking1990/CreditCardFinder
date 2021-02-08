# CreditCardFinder

## Introduction

#### This is a test project from 
![alt text](https://pbs.twimg.com/profile_images/1259087992951648256/hGj8Hr5s_400x400.png "MintBank")

#### It is a Mini Android Application developed by [me](https://www.github.com/sterlingking1990) which displays some information such as Credit Card Type, Brand, Country, Bank when the user input above 4 digits credit card number.

## Language Used
1. Android/Kotlin

## External Resource
1. BinList Api - [here](https://www.binlist.net)

## Architure Component 
 1. **MVVM**
 2. **DI- Dependency Injection**
 3. **Coroutine**


## Launch
To run this project, first copy the project [repo](https://github.com/sterlingking1990/CreditCardFinder) then
1. Open Android Studio
2. click on File
3. Select New
4. Click on Project from Version Control
5. Enter the repo `https://github.com/sterlingking1990/CreditCardFinder` and click ok

This will clone the repo, so you can click on run button to run

## API Prerequisites
 1. It starts picking up when digits are above 4 
 2. its max is 8 in length
 3. some of the numbers entered could return null as response; hence no card info found for that number- for this scenerio we display:
 `Country` as Country, `Bank` as Bank name, `brand` as Brand, `card type` as Card type , `Credit Card Number ` as 00000000 
 
 ## Test Covered
 1. View model test as Unit Test; Fake Repository was set up to perform this test
 2. Fragment Test as Instrumented Test: Due to DI, Custom Activity Needed to be created in other to attach android entry point to it and then place fragment scenerio which made it possible to test the fragment
 
 
 
