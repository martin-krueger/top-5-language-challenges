# Typescript - The Coffee Machine

This is a simple coffee machine implementation. You can order a coffee of different types. <br/>
If there is not enough water or if there are not enough beans you have to refill them.

## Build and run with Docker

You can build a Docker image and run it using these commands:

```shell script
docker build -t coffee-machine .
docker run -it -p 3000:3000 coffee-machine
```

Then open [http://localhost:3000](http://localhost:3000) in your browser.

## Build and run in the project

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br />
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br />
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br />
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.