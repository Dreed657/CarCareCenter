import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { ThemeProvider } from "@material-ui/styles";

import HomePage from "./pages/home";
import StatsPage from "./pages/stats";
import CarDetailsPage from "./pages/car-details";
import ErrorPage from "./pages/error";

import Themes from './themes';

function App() {
  return (
    <ThemeProvider theme={Themes.default}>
      <BrowserRouter>
        <Switch>
          <Route path="/" exact component={HomePage}/>
          <Route path="/stats" exact component={StatsPage}/>
          <Route path="/car/:id" exact component={CarDetailsPage}/>
          <Route component={ErrorPage}/>
        </Switch>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;
