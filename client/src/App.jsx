import "./App.scss";
import React from "react";
import { Routes, Route } from "react-router-dom";
import SignUp from "./pages/SignUp";
import SignIn from "./pages/SignIn";
import Account from "./pages/Account";
import Tasks from "./pages/Tasks";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Tasks />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/sign-in" element={<SignIn />} />
        <Route path="/account" element={<Account />} />
      </Routes>
    </div>
  );
}

export default App;
