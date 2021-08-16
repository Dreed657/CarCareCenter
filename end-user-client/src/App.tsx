import { ThemeProvider } from "@material-ui/styles";
import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import CarDetailsPage from "./pages/car-details";
import ErrorPage from "./pages/error";

import HomePage from "./pages/home";
import StatsPage from "./pages/stats";

import Themes from './themes';

function App() {
  return (
    <ThemeProvider theme={Themes.default}>
      <BrowserRouter>
        <Switch>
          <Route path="/" exact component={HomePage}></Route>
          <Route path="/stats" exact component={StatsPage}></Route>
          <Route path="/car/:id" exact component={CarDetailsPage}></Route>
          <Route component={ErrorPage}></Route>
        </Switch>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;
