import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import "./global.css";

import Login from "./pages/Login";
import Swagger from "./pages/Swagger";

export default function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" element={Login} />
        <Route path="/swagger" element={<Swagger />} />
      </Switch>
    </BrowserRouter>
  );
}
