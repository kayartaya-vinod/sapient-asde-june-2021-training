# SAPIENT ASDE - JUNE, 2021

### TOC for day 5:

-   BDD overview
-   TDD
-   TDD in a BDD way
-   BDD specifications
-   BDD tools
-   Cucumber
-   Demo using Java + Cucumber

## Assignments:

For each of the assignments given below, implement the logic for each of the functions given and use the BDD (Cucumber) to test the functionalities. Test with at least 6 example inputs/expectations.

### Assignment 1

```java
boolean isValidDate(int year, int month, int day)
```

The function should check if the parameter values constitute a valid calendar date or not. Accordingly return true or false.

For example,

1. year=2018, month=13, day=1 is an invalid date as the possible values for month is 1 to 12.
2. year=2018, month=2, day=29 is an invalid date as the maximum days in February is 28 in the year 2018
3. year=2016, month=2, day=29 is a valid date.

### Assignment 2

```java
boolean isPrimeNumber(int num)
```

The function should check and return true only if the number passed as argument is a prime number.

Write a Java program to call the above function multiple times with different values.

### Assignment 3

```java
int sumOfPrimes(int from, int to)
```

The function takes two integers as input and returns the sum of all the prime numbers between the same.

### Assignment 4

In mathematics, the Fibonacci numbers are the numbers in the following integer sequence, characterized by the fact that every number after the first two numbers is the sum of the two preceding ones:

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...

```java
int fibonacci(int index)
```

The function takes index as paramter and returns the fibonacci number at that index.

### Assignment 5

In trignometry, the Sine of an angle is represented by the series below:

![](https://wikimedia.org/api/rest_v1/media/math/render/svg/3d9689077ce39529e3198213901261ebcf48197d)

```java
double sine(int angle)
```

The function accepts angle in degrees and returns the `sine` of the given angle.

### Assignment 6

```java
String inWords(int num)
```

The function takes a number between 1 and 99,99,99,999 and returns a String representing the input number in words.

For example,

```java
inWords(12345);
// should return "twelve thousand three hundred forty five"
inWords(10203040);
// should return "one crore two lakh three thousand forty"
inWords(101);
// should return "one hundred one"
```
