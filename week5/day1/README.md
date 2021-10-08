# ASDE Training

## TOC for Week 5 Day 1:

-   Security basics
-   Authentication/Authorization
-   Token based Authentication
-   JWT
-   Handling JWT in React

![](concepts.dio.png)

## Tasks for the day

1. Makes sure to store encrypted password in the DB (use bcrypt)
1. In the UI, display `Welcome <name of the logged in person>`, instead of email
1. When you reload the page, the user is logged off automatically. Fix this one.
1. Currently, there is no user registration option. Please provide one.
1. Provide paginated list of contacts, for the logged in user

## A Quicker Create-React-App

If you want to create a React project, you probably reach for a tool like Create-React-App.

While Create-React-App remains an amazing tool and allows you to create a React project by running a single command, there is a new competitor to it that you should know about called Vite.

Create-React-App uses Webpack under the hood to build our React code for development. But build tools have emerged that compete with Webpack in speed.

> **Vite** is one such build tool that uses a faster bundler called esbuild. In short, it makes use of the browser's native ES modules to make for a quicker development experience.

How much faster is Vite? See for yourself!

Here's a quick comparison of starting up a Vite project (right) versus a Create-React-App project (left).

<a href='https://reedbarger.nyc3.digitaloceanspaces.com/vite-react.mp4'>

![vite](./vite.png 'vite')

</a>
Vite is many times faster than Create-React-App when running React in development.

If you're annoyed at times with how long Create-React-App can take to start up, definitely check out Vite.

On top of that, I'd highly recommend the tool Create-Next-App.

This allows us to create a next JS project very rapidly. And yes, Next.js is a framework, but it is a React framework that needs drastically fewer dependencies. In fact, it just needs the dependencies React, React DOM, and Next.

Be sure to check out Vite and Create Next App when creating your next React project.

To create a new react project using vite, issue the following command:

```shell
$ npm init @vitejs/app my-new-react-app --template react
$ cd my-new-react-app
$ npm install
$ npm run dev
```
